package qa.guru.rococo.page;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import qa.guru.rococo.config.Config;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverConditions.url;

public class LoginPage {

    private final String urlLoginPage = Config.getInstance().rococoAuthUrl() + "login";

    @Step("open Login page")
    public LoginPage openPage() {
        open(urlLoginPage);

        return this;
    }

    @Step("check url login page")
    public LoginPage checkUrlLoginPage() {
        Selenide.webdriver().shouldHave(url(urlLoginPage));

        return this;
    }

    @Step("input name")
    public LoginPage inputName(String name) {
        $("[name = 'username']").setValue(name);

        return this;
    }

    @Step("input password")
    public LoginPage inputPassword(String password) {
        $("[name = 'password']").setValue(password);

        return this;
    }

    @Step("submit login form")
    public MainPage submitLoginForm() {
        $("[type = 'submit']").click();

        return new MainPage();
    }
}
