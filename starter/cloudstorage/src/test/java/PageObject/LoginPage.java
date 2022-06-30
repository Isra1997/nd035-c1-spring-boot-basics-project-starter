package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    @FindBy(id = "inputUsername")
    private WebElement username;

    @FindBy(id = "inputPassword")
    private WebElement password;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(id = "logoutMessage")
    private WebElement logoutMessage;


    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver,this);
    }

    public void  login(String username, String password){
        this.username.sendKeys(username);
        this.password.sendKeys(password);
        this.loginButton.submit();
    }

    public boolean isLoginPageDisplayed(){
        return loginButton.isDisplayed();
    }

    public boolean hasUserJustLoggedOut(){
        return logoutMessage.isDisplayed();
    }

}
