package qa.guru.rococo.page;

import io.qameta.allure.Step;
import qa.guru.rococo.config.Config;
import qa.guru.rococo.page.component.HeaderComponent;
import qa.guru.rococo.page.component.ProfileComponent;

import static com.codeborne.selenide.Selenide.open;

public class MainPage {
    private final String urlMainPage = Config.getInstance().rococoFrontUrl();
    private final HeaderComponent headerComponent = new HeaderComponent();
    private final ProfileComponent profileComponent = new ProfileComponent();

    @Step("open Login page")
    public MainPage openPage() {
        open(urlMainPage);

        return this;
    }

    public LoginPage clickLogin() {
        headerComponent.clickLogin();

        return new LoginPage();
    }

    public MainPage avatarIsEnable() {
        headerComponent.avatarIsEnable();

        return this;
    }

    public MainPage clickByAvatar() {
        headerComponent.clickByAvatar();

        return this;
    }

    public MainPage loginButtonIsEnable() {
        headerComponent.loginButtonIsEnable();

        return this;
    }

    public MainPage clickLogout() {
        profileComponent.clickLogout();

        return this;
    }

    public MainPage inputFirstName(String name) {
        profileComponent.inputFirstName(name);

        return this;
    }

    public MainPage inputLastName(String name) {
        profileComponent.inputLastName(name);

        return this;
    }

    public MainPage submitProfile() {
        profileComponent.submitProfileForm();

        return this;
    }
}
