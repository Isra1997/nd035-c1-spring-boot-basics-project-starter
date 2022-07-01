package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

    @FindBy(id = "nav-credentials-tab")
    private WebElement credentialsTab;

    @FindBy(id = "home-link-success")
    private WebElement linkToHomePage;

    @FindBy(id = "credential-url")
    private WebElement credentialUrl;

    @FindBy(id = "credential-username")
    private WebElement credentialUsername;

    @FindBy(id = "credential-password")
    private WebElement credentialPassword;

    @FindBy(id = "credential-form")
    private WebElement credentialForm;



    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver,this);
    }

    public void createCredential(String url,String username,String password){
        this.credentialUrl.click();
        this.credentialUrl.sendKeys(url);
        this.credentialUsername.click();
        this.credentialUsername.sendKeys(username);
        this.credentialPassword.click();
        this.credentialPassword.sendKeys(password);
        this.credentialForm.submit();
    }

    public void createNote(String noteTitle,String noteDescription){
        this.noteTitle.click();
        this.noteTitle.sendKeys(noteTitle);
        this.noteDescription.click();
        this.noteDescription.sendKeys(noteDescription);
        noteForm.submit();
    }

    public void deleteCredential(String credentialUrl,WebDriver driver){
        List<WebElement> inputs = driver.findElements(By.tagName("th"));
        List<WebElement> deleteButtons = driver.findElements(By.tagName("a"));

        for (WebElement input : inputs) {
            if (input.getText().equals(credentialUrl)) {
                deleteButtons.get(deleteButtons.size()-1).click();
                break;
            }
        }

    }

    public int getThCount(WebDriver driver){
        List<WebElement> inputs = driver.findElements(By.tagName("th"));
        return inputs.size();
    }


    public boolean isDisplayed(String text, WebDriver driver){
        List<WebElement> inputs = driver.findElements(By.tagName("th"));
        for (WebElement input : inputs) {
            if (input.getText().equals(text)) {
                return true;
            }
        }
        return false;
    }


    public void deleteNote(String noteTitle,WebDriver driver){
        List<WebElement> inputs = driver.findElements(By.tagName("th"));
        List<WebElement> deleteButtons = driver.findElements(By.tagName("a"));

        for (WebElement input : inputs) {
            if (input.getText().equals(noteTitle)) {
                deleteButtons.get(deleteButtons.size()-1).click();
                break;
            }
        }

    }

    public void editNote(String noteTitleEdited,WebDriver driver,WebDriverWait wait){
        List<WebElement> editButtons = driver.findElements(By.tagName("button"));

        //get the edit button
        for (int i = 0; i < editButtons.size(); i++) {
            if(editButtons.get(i).getText().equals("Edit")){
                editButtons.get(i).click();
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("noteModal")));
                driver.findElement(By.id("note-title")).clear();
                driver.findElement(By.id("note-title")).sendKeys(noteTitleEdited);
                driver.findElement(By.id("save-note-changes")).click();
                break;
            }
        }
    }


    public void editCredential(String urlEdited,WebDriver driver,WebDriverWait wait){
        List<WebElement> editButtons = driver.findElements(By.tagName("button"));

        //get the edit button
        for (int i = 0; i < editButtons.size(); i++) {
            if(editButtons.get(i).getText().equals("Edit")){
                editButtons.get(i).click();
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("credentialModal")));
                driver.findElement(By.id("credential-url")).clear();
                driver.findElement(By.id("credential-url")).sendKeys(urlEdited);
                driver.findElement(By.id("save-credential-changes")).click();
                break;
            }
        }
    }



    public void goToTheHomePage(){
        linkToHomePage.click();
    }


    public void logout(){
        logoutButton.click();
    }
}
