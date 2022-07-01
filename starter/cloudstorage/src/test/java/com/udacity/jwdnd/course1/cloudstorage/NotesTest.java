package com.udacity.jwdnd.course1.cloudstorage;

import PageObject.HomePage;
import PageObject.LoginPage;
import PageObject.SignupPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NotesTest {

    private static WebDriver driver;
    private HomePage homePage;
    private WebDriverWait wait;
    @LocalServerPort
    int port;


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

        //go to the notes tab
        WebElement notesTab = driver.findElement(By.id("nav-notes-tab"));
        notesTab.click();

        //wait until the note's page is loaded
        this.wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("add-note-button")));

        //click the add note button
        driver.findElement(By.id("add-note-button")).click();

        //create the home page
        this.homePage = new HomePage(driver);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("noteModal")));
        homePage.createNote("Test note title","Test note description");

        //go to the home page
        homePage.goToTheHomePage();

        //wait until the homePage is loaded
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("add-note-button")));
    }

    @AfterEach
    public void afterEach() {
        if (this.driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testEditNote() throws InterruptedException {
        //edit note
        homePage.editNote("title edit",driver,wait);

        //go to the home page
        homePage.goToTheHomePage();

        //wait until the homePage is loaded
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("add-note-button")));

        //assert that the note has been edited
        assertTrue(homePage.isDisplayed("title edit",driver));
    }

    @Test
    public void  testCreateNote() {
        //click the add note button
        driver.findElement(By.id("add-note-button")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("noteModal")));
        homePage.createNote("Test note title.","Test note description.");

        //go to the home page
        homePage.goToTheHomePage();

        //wait until the homePage is loaded
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("add-note-button")));

        //assert that the note has been added
        assertTrue(homePage.isDisplayed("Test note title.",driver));
    }

    @Test
    public void testDeleteNote(){
        //get the current row count
        int currentCount = homePage.getThCount(driver);
        //delete note
        homePage.deleteNote("Test note title",driver);

        //go to the home page
        homePage.goToTheHomePage();

        //wait until the homePage is loaded
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("add-note-button")));

        //assert that the note has been deleted
        assertEquals(currentCount-1,homePage.getThCount(driver));
    }


    @AfterAll
    public static void tearDown(){
        driver.quit();
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
}
