package qa.guru.rococo.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.impl.client.SystemDefaultHttpClient;
import qa.guru.rococo.api.context.CookieContext;
import qa.guru.rococo.api.context.LocalStorageContext;
import qa.guru.rococo.api.interceptor.AddCodeInterceptor;
import qa.guru.rococo.api.interceptor.AddCookieInterceptor;
import qa.guru.rococo.config.Config;
import qa.guru.rococo.model.SessionJson;
import qa.guru.rococo.model.UserJson;

import java.util.Base64;

import static io.restassured.RestAssured.given;
import static io.restassured.config.HttpClientConfig.httpClientConfig;
import static io.restassured.config.RedirectConfig.redirectConfig;
import static java.nio.charset.StandardCharsets.UTF_8;

public class ApiHelper {

    public static void authorizationByApi(UserJson user) {
        LocalStorageContext localStorageContext = LocalStorageContext.getInstance();
        localStorageContext.init();
        CookieContext cookieContext = CookieContext.getInstance();

        RestAssured.config = RestAssured.config().httpClient(httpClientConfig().httpClientFactory(
                () -> {
                    SystemDefaultHttpClient client = new SystemDefaultHttpClient();
                    client.addResponseInterceptor(
                            new AddCookieInterceptor()
                    );
                    client.addRequestInterceptor(
                            new AddCodeInterceptor()
                    );
                    return client;
                }));

        given()
                .config(RestAssured.config()
                        .redirect(redirectConfig()
                                .followRedirects(true)))
                .urlEncodingEnabled(false)
                .param("response_type", "code")
                .param("client_id", "client")
                .param("scope", "openid")
                .param("redirect_uri", Config.getInstance().rococoFrontUrl() + "authorized")
                .param("code_challenge", localStorageContext.getCodeChallenge())
                .param("code_challenge_method", "S256")
                .get(Config.getInstance().rococoAuthUrl() + "oauth2/authorize");

        Response response = given()
                .log().all()
                .cookie("JSESSIONID", cookieContext.getJSessionIdCookieValue())
                .cookie("XSRF-TOKEN", cookieContext.getXsrfTokenCookieValue())
                .contentType(ContentType.URLENC)
                .formParam("_csrf", cookieContext.getXsrfTokenCookieValue())
                .formParam("username", user.getUsername())
                .formParam("password", user.getPassword())
                .post(Config.getInstance().rococoAuthUrl() + "login")
                .then().log().all().extract().response();

        given()
                .log().all()
                .cookie("JSESSIONID", cookieContext.getJSessionIdCookieValue())
                .get(response.header("Location"))
                .then().log().all();

        String token = given()
                .urlEncodingEnabled(false)
                .log().all()
                .header("Authorization", "Basic " +
                        new String(Base64.getEncoder().encode("client:secret".getBytes(UTF_8))))
                .cookie("JSESSIONID", cookieContext.getJSessionIdCookieValue())
                .queryParam("client_id", "client")
                .queryParam("redirect_uri", Config.getInstance().rococoFrontUrl() + "authorized")
                .queryParam("grant_type", "authorization_code")
                .queryParam("code", localStorageContext.getCode())
                .queryParam("code_verifier", localStorageContext.getCodeVerifier())
                .post(Config.getInstance().rococoAuthUrl() + "oauth2/token")
                .getBody()
                .as(SessionJson.class)
                .getToken();

        localStorageContext.setToken(token);
    }

    public static void registerUserByApi(UserJson user) {
        Response responseFromRegisterPage = getDataFromPageByPath("register");
        given()
                .cookie("XSRF-TOKEN", responseFromRegisterPage.header("X-XSRF-TOKEN"))
                .contentType(ContentType.URLENC)
                .formParam("_csrf", responseFromRegisterPage.header("X-XSRF-TOKEN"))
                .formParam("username", user.getUsername())
                .formParam("password", user.getPassword())
                .formParam("passwordSubmit", user.getPassword())
                .post(Config.getInstance().rococoAuthUrl() + "register");
    }

    private static Response getDataFromPageByPath(String path) {
        return given()
                .get(Config.getInstance().rococoAuthUrl() + path);
    }
}
