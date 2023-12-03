package qa.guru.rococo.page;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import qa.guru.rococo.config.Config;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RegisterPage {

    private final String urlRegisterPage = Config.getInstance().rococoAuthUrl() + "register";

    @Step("open Register page")
    public RegisterPage openPage(){
        open(urlRegisterPage);

        return this;
    }

    @Step("input name")
    public RegisterPage inputName(String name){
        $("#username").setValue(name);

        return this;
    }

    @Step("input password")
    public RegisterPage inputPassword(String password){
        $("#password").setValue(password);

        return this;
    }

    @Step("confirm password")
    public RegisterPage confirmPassword(String password){
        $("#passwordSubmit").setValue(password);

        return this;
    }

    @Step("submit register form")
    public RegisterPage submitRegisterForm(){
        $("button[type ='submit']").click();

        return this;
    }

    @Step("check error register with existing user name")
    public RegisterPage checkErrorMessageExistingUserName(String userName) {
        $(".form__error").shouldHave(Condition.text("Username `" + userName + "` already exists"));

        return this;
    }
}
