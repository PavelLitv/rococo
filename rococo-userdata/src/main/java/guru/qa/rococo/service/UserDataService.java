package guru.qa.rococo.service;

import guru.qa.rococo.data.UserEntity;
import guru.qa.rococo.data.repository.UserRepository;
import guru.qa.rococo.ex.NotFoundException;
import guru.qa.rococo.model.UserJson;
import jakarta.annotation.Nonnull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
public class UserDataService {

    private static final Logger LOG = LoggerFactory.getLogger(UserDataService.class);

    private final UserRepository userRepository;

    @Autowired
    public UserDataService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//    @KafkaListener(topics = "users", groupId = "userdata")
//    public void listener(@Payload UserJson user, ConsumerRecord<String, UserJson> cr) {
//        LOG.info("### Kafka topic [users] received message: " + user.getUsername());
//        LOG.info("### Kafka consumer record: " + cr.toString());
//        UserEntity userDataEntity = new UserEntity();
//        userDataEntity.setUsername(user.getUsername());
//        userDataEntity.setCurrency(DEFAULT_USER_CURRENCY);
//        UserEntity userEntity = userRepository.save(userDataEntity);
//        LOG.info(String.format(
//                "### User '%s' successfully saved to database with id: %s",
//                user.getUsername(),
//                userEntity.getId()
//        ));
//    }

    public @Nonnull
    UserJson create(@Nonnull UserJson username){
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(username.getUsername());
        UserEntity createdUser = userRepository.save(userEntity);
        return UserJson.fromEntity(createdUser);
    }


    public @Nonnull
    UserJson update(@Nonnull UserJson user) {
        UserEntity userEntity = getRequiredUser(user.getUsername());
        userEntity.setFirstname(user.getFirstname());
        userEntity.setLastname(user.getLastname());
        userEntity.setAvatar(user.getAvatar() != null ? user.getAvatar().getBytes(StandardCharsets.UTF_8) : null);
        UserEntity saved = userRepository.save(userEntity);
        return UserJson.fromEntity(saved);
    }

    public @Nonnull
    UserJson getCurrentUser(@Nonnull String username) {
        return UserJson.fromEntity(getRequiredUser(username));
    }

    public @Nonnull
    List<UserJson> allUsers() {
        List<UserEntity> userEntities = userRepository.findAll();
        List<UserJson> userJsons = userEntities
                .stream()
                .map(UserJson::fromEntity)
                .toList();

        return userJsons;
    }

    private @Nonnull UserEntity getRequiredUser(@Nonnull String username) {
        UserEntity user = userRepository.findByUsername(username);
        if (user == null) {
            throw new NotFoundException("Can`t find user by username: " + username);
        }
        return user;
    }
}
