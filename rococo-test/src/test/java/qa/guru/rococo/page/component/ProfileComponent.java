package qa.guru.rococo.page.component;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class ProfileComponent {

    private final SelenideElement modalWindow = $("[data-testid = 'modal-component']");

    @Step("click logout")
    public ProfileComponent clickLogout() {
        modalWindow.$((".variant-ghost")).click();

        return this;
    }

    @Step("input first name")
    public ProfileComponent inputFirstName(String name) {
        modalWindow.$(("[name = 'firstname']")).setValue(name);

        return this;
    }

    @Step("input surname")
    public ProfileComponent inputLastName(String name) {
        modalWindow.$(("[name = 'surname']")).setValue(name);

        return this;
    }

    @Step("submit profile form")
    public ProfileComponent submitProfileForm() {
        modalWindow.$(("[type = 'submit']")).click();

        return this;
    }
}
