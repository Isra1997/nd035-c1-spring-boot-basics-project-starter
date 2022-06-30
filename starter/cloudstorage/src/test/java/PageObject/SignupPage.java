package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPage {

    @FindBy(id = "inputFirstName")
    private WebElement firstname;

    @FindBy(id = "inputLastName")
    private WebElement lastname;

    @FindBy(id="inputUsername")
    private WebElement username;

    @FindBy(id = "inputPassword")
    private WebElement password;

    @FindBy(id = "buttonSignUp")
    private WebElement buttonSubmit;

    public SignupPage(WebDriver driver) {
        PageFactory.initElements(driver,this);
    }

    public void signup(String username,String firstname,String lastname,String password){
        this.firstname.sendKeys(firstname);
        this.lastname.sendKeys(lastname);
        this.username.sendKeys(username);
        this.password.sendKeys(password);
        this.buttonSubmit.click();
    }

    public boolean isSignUpPageDisplayed(){
        return firstname.isDisplayed();
    }
}
