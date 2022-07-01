package com.udacity.jwdnd.course1.cloudstorage;

import PageObject.HomePage;
import PageObject.LoginPage;
import PageObject.SignupPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;


import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class loginPageTest {

    private static WebDriver driver;
    @LocalServerPort
    int port;


    @BeforeAll
    public static void setup(){
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void beforeEach(){
        driver = new ChromeDriver();
    }

    @AfterEach
    public void afterEach() {
        if (this.driver != null) {
            driver.quit();
        }
    }

    @Test
    public void unAuthorizedUserIsRedirectedToLogin(){
        driver.get("http://localhost:"+port+"/home");
        LoginPage loginPage = new LoginPage(driver);
        assertTrue(loginPage.isLoginPageDisplayed());
    }

    @Test
    public void unAuthorizedUserCanAccessSignupPage(){
        driver.get("http://localhost:"+port+"/signup");
        SignupPage signupPage = new SignupPage(driver);
        assertTrue(signupPage.isSignUpPageDisplayed());
    }

    @Test
    public void unAuthorizedUserCanAccessLoginPage(){
        driver.get("http://localhost:"+port+"/login");
        LoginPage loginPage = new LoginPage(driver);
        assertTrue(loginPage.isLoginPageDisplayed());
    }

    @Test
    public void verifyUserSignUpAndLogin()  {
        driver.get("http://localhost:"+port+"/signup");
        SignupPage signupPage = new SignupPage(driver);
        signupPage.signup("hopeSwag","hope","swag","awesome");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("hopeSwag","awesome");
        HomePage homePage = new HomePage(driver);
        homePage.logout();
        assertTrue(loginPage.hasUserJustLoggedOut());
    }
    @AfterAll
    public static void tearDown(){
        driver.quit();
    }



}
