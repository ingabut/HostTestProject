package ru.hostco.pages;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import ru.hostco.base.DriverManager;
import ru.hostco.pojo.Indicator;
import ru.hostco.utils.CommonUtils;

import java.util.List;
import java.util.stream.Collectors;

public class MainPage {

    @FindBy(xpath = "//app-dropdown[@class='long']//a[@data-testid = 'userBtn']")
    private WebElement usernameBtn;

    @FindBy(xpath = "//span[text()='Личный кабинет']/..")
    private WebElement personalAccountBtn;

    @FindBy(xpath = "//a[@routerlink ='card']")
    private WebElement miscBtn;

    @FindBy(xpath = "//a[@href='/account/card/health']")
    private WebElement indicatorsLink;

    @FindBy(xpath = "//p-dropdown[@placeholder='Показатель']//div[@aria-haspopup='listbox']")
    private WebElement chevronIndicatorBtn;

    @FindBy(xpath = "//li[@role='option']")
    private List<WebElement> indicatorDropdownList;

    private static final WebDriverWait wait = DriverManager.getWait();
    private static final Logger logger = LogManager.getLogger(MainPage.class.getName());

    public MainPage() {
        PageFactory.initElements(DriverManager.getDriverThread(), this);
    }
    @Step("Переходим в раздел \"Разное\"")
    public void goToMisc() {
        usernameBtn.click();
        logger.info("Нажали на кнопку с именем пользователя");
        wait.until(ExpectedConditions.visibilityOf(personalAccountBtn));
        personalAccountBtn.click();
        logger.info("Вошли в \"Личный кабинет\"");
        miscBtn.click();
        logger.info("Перешли в раздел \"Разное\"");
        Assert.assertTrue(indicatorsLink.isDisplayed());
    }

    @Step("Нажимаем на шеврон для выбора индикатора")
    public void clickIndicatorChevron() {
        chevronIndicatorBtn.click();
        logger.info("Нажали на шеврон для выбора индикатора");
    }
    @Step("Выбираем случайный индикатор")
    public String chooseRandomIndicator() {
        WebElement indicatorElement = CommonUtils.getRandomElement(indicatorDropdownList);
        indicatorElement.click();
        String indicatorName = indicatorElement.getText();
        Allure.addAttachment("Выбрали индикатор: ", indicatorName);
        logger.info("Выбрали индикатор \"{}\"", indicatorName);
        DriverManager.getWait().until(ExpectedConditions.visibilityOfAllElements(indicatorDropdownList));
        return indicatorName;
    }

    @Step("Проверям, что на странице отображены только данные по выбранному индикатору")
    public void assertChosenIndicatorsOnPage(String indicator) {
        List <WebElement> indicatorList = DriverManager.getDriverThread()
                .findElements(By.xpath("//div[@class='col indicator']/div[contains(@class, 'break-word')]"));
        if (!indicatorList.isEmpty()) {
            List<String> uniqueIndicators = indicatorList.stream()
                    .map(ind -> ind.getAttribute("textContent"))
                    .distinct()
                    .collect(Collectors.toList());
            Assert.assertTrue(uniqueIndicators.stream().count() <= 1,
                              String.format("На странице отображены индикаторы: \"%s\"", uniqueIndicators));

            Assert.assertTrue(uniqueIndicators.get(0).equals(indicator),
                              String.format("На странице отображен индикатор: ", uniqueIndicators.get(0)));

            Allure.addAttachment("Список уникальных индикаторов на странице ", uniqueIndicators.toString());
        } else {
            Allure.addAttachment(indicator, String.format("Индикатор \"%s\" отсутствует", indicator));
        }
    }



}
