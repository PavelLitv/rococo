package qa.guru.rococo.page;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class WelcomePage {
    @Step("go to login page")
    public LoginPage gotoLoginPage() {
        $(".form__submit").click();

        return new LoginPage();
    }

    @Step("check welcome message")
    public WelcomePage checkWelcomeMessage() {
        $(".form__subheader")
                .shouldHave(Condition.text("Добро пожаловать в Rococo"));

        return this;
    }
}
