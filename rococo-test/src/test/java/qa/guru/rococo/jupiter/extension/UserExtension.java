package qa.guru.rococo.jupiter.extension;

import org.junit.jupiter.api.extension.*;
import qa.guru.rococo.db.dao.UserDao;
import qa.guru.rococo.jupiter.annotation.CreateUser;
import qa.guru.rococo.model.UserJson;

import java.util.UUID;

import static qa.guru.rococo.api.ApiHelper.registerUserByApi;
import static qa.guru.rococo.jupiter.extension.Utils.getAllureId;

public class UserExtension implements BeforeEachCallback, ParameterResolver, AfterTestExecutionCallback {

    public static ExtensionContext.Namespace NAMESPACE = ExtensionContext.Namespace.create(UserExtension.class);

    @Override
    public void beforeEach(ExtensionContext context) {
        CreateUser annotation = context.getRequiredTestMethod().getAnnotation(CreateUser.class);
        UserJson user = new UserJson();
        user.setUsername(annotation.userName());
        user.setPassword(annotation.password());
        user.setPasswordSubmit(annotation.password());
        registerUserByApi(user);
        context.getStore(NAMESPACE).put(getAllureId(context), user);
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext
                .getParameter()
                .getType()
                .isAssignableFrom(UserJson.class);
    }

    @Override
    public UserJson resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return extensionContext.getStore(NAMESPACE).get(getAllureId(extensionContext), UserJson.class);
    }

    @Override
    public void afterTestExecution(ExtensionContext context) {
        UserJson userFromTest = context.getStore(NAMESPACE).get(getAllureId(context), UserJson.class);
        UserDao userDao = new UserDao();
        UUID userId = userDao.getUserIdByName(userFromTest.getUsername());
        userDao.deleteUserAuthByUserId(userId);
        userDao.deleteUserDataByName(userFromTest.getUsername());
    }
}
