package qa.guru.rococo.test.web;

import io.qameta.allure.AllureId;
import org.junit.jupiter.api.Test;
import qa.guru.rococo.jupiter.annotation.CreateUser;
import qa.guru.rococo.jupiter.annotation.DeleteUser;
import qa.guru.rococo.jupiter.annotation.WebTest;
import qa.guru.rococo.model.UserJson;
import qa.guru.rococo.page.LoginPage;
import qa.guru.rococo.page.MainPage;
import qa.guru.rococo.page.RegisterPage;

@WebTest
public class LoginWebTest {

    @DeleteUser(
            userName = "Pavlik",
            password = "12345"
    )
    @Test
    @AllureId("login_01")
    void registerNewUserAndLogin(UserJson user) {
        new RegisterPage()
                .openPage()
                .inputName(user.getUsername())
                .inputPassword(user.getPassword())
                .confirmPassword(user.getPassword())
                .submitRegisterForm()
                .inputName(user.getUsername())
                .inputPassword(user.getPassword());
        new LoginPage()
                .submitLoginForm()
                .avatarIsEnable();
    }

    @CreateUser(
            userName = "Denis",
            password = "12345"
    )
    @Test
    @AllureId("login_02")
    void login(UserJson user) {
        new MainPage()
                .openPage()
                .clickLogin()
                .inputName(user.getUsername())
                .inputPassword(user.getPassword())
                .submitLoginForm()
                .avatarIsEnable();
    }

    @CreateUser(
            userName = "Elephant",
            password = "12345"
    )
    @Test
    @AllureId("login_03")
    void registerWithExistingUserName(UserJson user) {
        new RegisterPage()
                .openPage()
                .inputName(user.getUsername())
                .inputPassword(user.getPassword())
                .confirmPassword(user.getPassword())
                .submitRegisterForm()
                .checkErrorMessageExistingUserName(user.getUsername());
    }

    @Test
    @AllureId("login_04")
    void logout() {
        new MainPage()
                .openPage()
                .clickLogin()
                .inputName("Name")
                .inputPassword("pass")
                .submitLoginForm()
                .avatarIsEnable()
                .clickByAvatar()
                .clickLogout()
                .loginButtonIsEnable();

        //todo сделать екстеншион который будет создавать, удалять юзера и авторизовываться под ним
    }

    @Test
    @AllureId("login_05")
    void addNameAndLastNameToProfile() {
        new MainPage()
                .openPage()
                .clickLogin()
                .inputName("Name")
                .inputPassword("pass")
                .submitLoginForm()
                .avatarIsEnable()
                .clickByAvatar()
                .inputFirstName("firstName")
                .inputLastName("lastName")
                .submitProfile()
                .clickByAvatar()
//                .checkProfileData(userWithProfile)
        ;

        //todo сделать екстеншион который будет создавать, удалять юзера и авторизовываться под ним
    }

}
