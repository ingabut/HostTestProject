package ru.hostco.pages;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.hostco.base.DriverManager;

public class LoginPage {
    @FindBy(xpath="//a[@data-testid = 'signInBtn']")
    private WebElement loginBtn;
    public LoginPage() {
        PageFactory.initElements(DriverManager.getDriverThread(), this);
    }
    public KeycloakPage clickCustomerLogin() {
        loginBtn.click();
        return new KeycloakPage();
    }
}
