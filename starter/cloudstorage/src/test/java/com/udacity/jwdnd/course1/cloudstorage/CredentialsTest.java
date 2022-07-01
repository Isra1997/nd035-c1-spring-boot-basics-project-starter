package com.udacity.jwdnd.course1.cloudstorage;

import PageObject.HomePage;
import PageObject.LoginPage;
import PageObject.SignupPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CredentialsTest {

    @LocalServerPort
    int port;

    private static WebDriver driver;

    private HomePage homePage;

    WebDriverWait wait;


    @BeforeAll
    public static void setup(){
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void beforeEach(){
        driver = new ChromeDriver();
        driver.get("http://localhost:"+port+"/login");
        signUp("testCreateNoteUsername","testCreateNoteFirstname","testCreateNoteLastname","testCreateNotePassword");
        login("testCreateNoteUsername","testCreateNotePassword");

        //go to the credentials tab
        WebElement notesTab = driver.findElement(By.id("nav-credentials-tab"));
        notesTab.click();

        //wait until the credential's page is loaded
        this.wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("add-credential-button")));

        //create home-page
        this.homePage = new HomePage(driver);
    }

    private void createCredential(String url,String username,String password) {
        //click the add credential button
        driver.findElement(By.id("add-credential-button")).click();

        //create the home page
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("credentialModal")));
        homePage.createCredential(url,username,password);

        //go to the home page
        homePage.goToTheHomePage();

        //wait until the homePage is loaded
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("add-credential-button")));
    }

    @AfterEach
    public void afterEach() {
        if (this.driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testCreateCredential(){
        //click the add credential button
        driver.findElement(By.id("add-credential-button")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("credentialModal")));
        homePage.createCredential("urlText","username","password");

        //go to the home page
        homePage.goToTheHomePage();

        //wait until the homePage is loaded
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("add-credential-button")));

        //assert that the credential has been added
        assertTrue(homePage.isDisplayed("urlText",driver));
    }

    @Test
    public void testDeleteCredential(){
        //create credential
        createCredential("delete url","delete username","delete password");

        //get the current row count
        int currentCount = homePage.getThCount(driver);

        //delete credential
        homePage.deleteCredential("delete url",driver);

        //go to the home page
        homePage.goToTheHomePage();

        //wait until the homePage is loaded
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("add-credential-button")));

        //assert that the credential has been deleted
        assertEquals(currentCount-1,homePage.getThCount(driver));
    }


    @Test
    public void testEditCredential() throws InterruptedException {
        //create credential
        createCredential("edit url","edit username","edit password");
        //edit credential
        homePage.editCredential("url",driver,wait);

        //go to the home page
        homePage.goToTheHomePage();

        //wait until the homePage is loaded
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("add-credential-button")));

        //assert that the credential has been edited
        assertTrue(homePage.isDisplayed("url",driver));
    }


    private void signUp(String username,String firstname,String lastname,String password){
        driver.get("http://localhost:"+port+"/signup");
        SignupPage signupPage = new SignupPage(driver);
        signupPage.signup(username, firstname, lastname, password);
    }

    private void login(String username, String password){
        driver.get("http://localhost:"+port+"/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);
    }


    @AfterAll
    public static void tearDown(){
        driver.quit();
    }
}
