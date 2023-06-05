package ru.hostco.tests;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.hostco.base.BaseTests;
import ru.hostco.reporting.TestListener;

@Listeners({TestListener.class})
public class HealthIndicatorsTest extends BaseTests {

    @Test(description = "Проверка отображения индикаторов на первой странице в соответствии с фильтром")
    public void testIndicatorFilter(){
        mp.goToMisc();
        mp.clickIndicatorChevron();
        mp.assertChosenIndicatorsOnPage(mp.chooseRandomIndicator());
    }
}
