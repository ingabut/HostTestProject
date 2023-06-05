package ru.hostco.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.hostco.base.DriverManager;
import ru.hostco.utils.GeneralProperties;

import static ru.hostco.utils.GeneralProperties.generalConfig;

public class KeycloakPage {
    private static final Logger logger = LogManager.getLogger(KeycloakPage.class.getName());
    private final String login = generalConfig.login();
    private final String password = generalConfig.password();

    @FindBy(id = "username")
    private WebElement usernameInp;

    @FindBy(id = "password")
    private WebElement passwordInp;

    @FindBy(id = "kc-login")
    private WebElement signInBtn;
    public KeycloakPage() {
        PageFactory.initElements(DriverManager.getDriverThread(), this);
    }

    public MainPage login() {
        usernameInp.sendKeys(login);
        passwordInp.sendKeys(password);
        signInBtn.click();
        logger.info("Залогинились");
        return new MainPage();
    }
}

