package qa.guru.rococo.jupiter.extension;

import org.junit.jupiter.api.extension.*;
import qa.guru.rococo.api.context.CookieContext;
import qa.guru.rococo.api.context.LocalStorageContext;
import qa.guru.rococo.jupiter.annotation.ApiLogin;
import qa.guru.rococo.model.UserJson;

import static qa.guru.rococo.api.ApiHelper.authorizationByApi;
import static qa.guru.rococo.jupiter.extension.Utils.getAllureId;

public class ApiLoginExtension implements BeforeEachCallback, AfterEachCallback, ParameterResolver {

    public static ExtensionContext.Namespace NAMESPACE = ExtensionContext.Namespace.create(UserExtension.class);

    @Override
    public void beforeEach(ExtensionContext context) {
        ApiLogin apiLoginAnnotation = context.getRequiredTestMethod().getAnnotation(ApiLogin.class);
        UserJson user = new UserJson();
        user.setUsername(apiLoginAnnotation.username());
        user.setPassword(apiLoginAnnotation.password());
        if(apiLoginAnnotation.user().handleAnnotation()){
            context.getStore(NAMESPACE).put(getAllureId(context), user);
        }
        authorizationByApi(user);
        LocalStorageContext localStorageContext = LocalStorageContext.getInstance();
        CookieContext cookieContext = CookieContext.getInstance();




        //todo проверить как работает вложенная анатация, хэндл анатации тру/фолс
        //todo доделать авторизацию в браузере


    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return false;
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return null;
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {

    }

}
