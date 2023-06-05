package ru.hostco.base;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import ru.hostco.pages.KeycloakPage;
import ru.hostco.pages.LoginPage;
import ru.hostco.pages.MainPage;

import java.time.Duration;

import static ru.hostco.utils.GeneralProperties.generalConfig;

public class BaseTests {

    private WebDriver driver;
    protected LoginPage lp;
    protected KeycloakPage kp;
    protected MainPage mp;
    int timeout = 5;

    @BeforeClass
    public void setUp() {
        DriverManager.initiate();
        driver = DriverManager.getDriverThread();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
        driver.manage().window().maximize();
    }

    @BeforeMethod
    public void goHome() {
        openLoginPage();
    }
    public void openLoginPage() {
        driver.get(generalConfig.homepage());
        lp = new LoginPage();
        kp = lp.clickCustomerLogin();
        mp = kp.login();
    }

    @AfterMethod
    public void logout() {
        driver.manage().deleteAllCookies();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        DriverManager.quit();
    }
}

