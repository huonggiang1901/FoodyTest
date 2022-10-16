package elements;

import common.helpers.Logger;
import driver.DriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SelectBox extends BaseElement{

    public SelectBox(String xpath) {
        super(xpath);
    }

    public static void selectItemInCustomDropdown(Button customSelectBox, BaseElement optionList,
                                           String expectedItem) {
        customSelectBox.waitForClickable();
        customSelectBox.click();
        DriverManager.sleepInSecond(1);

        optionList.waitForElementPresence();
        List<WebElement> allItems = optionList.getElements();

        for (WebElement item : allItems) {
            if (getWebElementText(item).trim().equals(expectedItem)) {
                scrollToBottom(item);
                clickElement(item);
                DriverManager.waitForPageLoad();
                break;
            }
        }
    }

}
