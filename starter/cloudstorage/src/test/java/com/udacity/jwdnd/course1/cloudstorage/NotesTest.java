package com.udacity.jwdnd.course1.cloudstorage;

import PageObject.HomePage;
import PageObject.LoginPage;
import PageObject.ResultPage;
import PageObject.SignupPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NotesTest {

    private static WebDriver driver;
    @LocalServerPort
    static int port;


    @BeforeAll
    public static void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://localhost:"+port+"/signup");
        SignupPage signupPage = new SignupPage(driver);
        signupPage.signup("hopeSwag","hope","swag","awesome");
    }

    @BeforeEach
    public void beforeEach(){
        driver = new ChromeDriver();
        driver.get("http://localhost:"+port+"/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("hopeSwag","awesome");
    }

    @Test
    public void  createNote() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.createNote("hi there!","lovely to see you",driver);
        ResultPage resultPage = new ResultPage(driver);
        resultPage.goHome();
        assertTrue(homePage.isNoteDisplayed("hi there!",driver));
        homePage.logout();
    }

    @Test
    public void deleteNote() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.createNote("Delete test!","This note is meant to be deleted.",driver);
        homePage.deleteNote("Delete test!",driver);
        assertFalse(homePage.isNoteDisplayed("Delete test!",driver));
    }


    @AfterAll
    public static void tearDown(){
        driver.quit();
    }
}
