package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResultPage {
    @FindBy(id="home-link-success")
    private WebElement homeLink;

    public ResultPage(WebDriver driver) {
        PageFactory.initElements(driver,this);
    }

    public void goHome(){
        homeLink.click();
    }
}
