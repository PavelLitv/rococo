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

@Component
public class UserDataService {

    private static final Logger LOG = LoggerFactory.getLogger(UserDataService.class);

    private final UserRepository userRepository;

    @Autowired
    public UserDataService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public @Nonnull
    UserJson create(@Nonnull UserJson userJson) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userJson.getUsername());

        return UserJson.fromEntity(userRepository.save(userEntity));
    }

    public @Nonnull
    UserJson update(@Nonnull UserJson userJson) {
        UserEntity userEntity = getRequiredUser(userJson.getUsername());
        userEntity.setFirstname(userJson.getFirstname());
        userEntity.setLastname(userJson.getLastname());
        userEntity.setAvatar(userJson.getAvatar() != null ? userJson.getAvatar().getBytes(StandardCharsets.UTF_8) : null);

        return UserJson.fromEntity(userRepository.save(userEntity));
    }

    public @Nonnull
    UserJson getCurrentUser(@Nonnull String username) {
        UserEntity userEntity = getRequiredUser(username);

        return UserJson.fromEntity(userEntity);
    }

    private @Nonnull UserEntity getRequiredUser(@Nonnull String username) {
        UserEntity user = userRepository.findByUsername(username);
        if (user == null) {
            throw new NotFoundException("Can`t find user by username: " + username);
        }
        return user;
    }
}
