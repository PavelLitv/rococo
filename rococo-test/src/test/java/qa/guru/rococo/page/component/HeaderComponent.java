package qa.guru.rococo.page.component;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class HeaderComponent {

    private final SelenideElement header = $("#shell-header");

    @Step("click login in the header")
    public HeaderComponent clickLogin() {
        header.$(".variant-filled-primary").click();

        return this;
    }

    @Step("login button is enable")
    public HeaderComponent loginButtonIsEnable() {
        header.$(".variant-filled-primary").shouldBe(Condition.enabled);

        return this;
    }

    @Step("check avatar is enable")
    public HeaderComponent avatarIsEnable() {
        header.$(".avatar-initials").shouldBe(Condition.enabled);

        return this;
    }

    @Step("click by avatar")
    public ProfileComponent clickByAvatar() {
        header.$(".avatar-initials").click();

        return new ProfileComponent();
    }
}
