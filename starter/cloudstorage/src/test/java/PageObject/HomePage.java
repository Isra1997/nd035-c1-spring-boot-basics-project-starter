package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class HomePage {

    @FindBy(id = "logoutButton")
    private WebElement logoutButton;

    @FindBy(id = "nav-files-tab")
    private WebElement  fileTab;

    @FindBy(id = "nav-notes-tab")
    private WebElement notesTab;

    @FindBy(id="add-notes")
    private WebElement addNoteButton;

    @FindBy(id = "note-title")
    private WebElement noteTitle;

    @FindBy(id = "note-form")
    private WebElement noteForm;

    @FindBy(id = "note-description")
    private WebElement noteDescription;

    @FindBy(id = "note-submit")
    private WebElement noteSubmitButton;

    @FindBy(id = "1")
    private WebElement notesTableRow;

    @FindBy(id = "nav-credentials-tab")
    private WebElement credentialsTab;



    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver,this);
    }

    public void createNote(String noteTitle,String noteDescription,WebDriver driver) throws InterruptedException {
        notesTab.click();
        Thread.sleep(2000);
        List<WebElement> inputs = driver.findElements(By.tagName("button"));
        for (WebElement input : inputs) {
            System.out.println(input.getAttribute("value"));
            if (input.getAttribute("value").equals("+ Add a New Note")) {
                addNoteButton = input;
            }
        }
        addNoteButton.click();
        Thread.sleep(2000);
        this.noteTitle.sendKeys(noteTitle);
        this.noteDescription.sendKeys(noteDescription);
        this.noteForm.submit();
        Thread.sleep(2000);
    }


    public boolean isNoteDisplayed(String noteTitle,WebDriver driver) throws InterruptedException {
        Thread.sleep(2000);
        List<WebElement> inputs = driver.findElements(By.tagName("th"));
        for (WebElement input : inputs) {
            System.out.println(input.getText());
            if (input.getText().equals(noteTitle)) {
                return true;
            }
        }
        Thread.sleep(2000);
        return false;
    }


    public void deleteNote(String noteTitle,WebDriver driver){
        WebElement trTag = driver.findElement(By.id("note-tbody"));
        List<WebElement> childElements = trTag.findElements(By.xpath("./child::*"));
        WebElement deleteBtn = null;
        WebElement noteTitleElement = null;
        for (WebElement element: childElements) {
            if(element.getText().equals("Delete")){
                deleteBtn = element;
            }
            if(element.getText().equals(noteTitle) && deleteBtn !=null){
                deleteBtn.click();
            }
        }

    }

    public void logout(){
        logoutButton.click();
    }
}
