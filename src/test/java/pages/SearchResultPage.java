package pages;

import common.helpers.Utils;
import driver.DriverManager;
import elements.BaseElement;
import elements.Link;
import org.openqa.selenium.WebElement;

import java.util.List;

import static elements.BaseElement.getWebElementText;

public class SearchResultPage extends GeneralPage{

    BaseElement tabSelectedMenuItem = new BaseElement("//div[@id='directory-search-resulttab']//a[@class='current']");
    BaseElement tabTopRate = new BaseElement("//div[contains(@data-bind,'Restaurant')]//a[text()='Đánh giá tốt nhất']");

    Link lnkResultItem = new Link ("//div[@class='result-name']//h2/a");
    Link lnkResultItemName = new Link("//div[@class='result-name']//h2/a[text()='%s']");
    Link lnkFoodItem = new Link("//li[@class='ldc-item ng-scope']//h2/a[text()='%s']");
    Link lnkResultFoodItem = new Link ("//li[@class='ldc-item ng-scope']//h2/a");

    Link lnkDGTN = new Link ("//a[@class='current'][contains(text(),'Đánh giá tốt nhất')]");


    public String selectRandomResultFoodItem(){
        lnkDGTN.waitForVisibility();
        String itemName = getRandomItemFromResultGrid().replace("Hệ Thống", "Hệ thống");
        lnkResultItemName.setDynamicValue(itemName);
        lnkResultItemName.waitForVisibility();
        if(lnkResultItemName.getAttribute("data-bind").contains("BranchName")){
            selectItemFromResultGrid(itemName);
            itemName = getRandomItemFromResultFood();
            selectItemFromResultFood(itemName);
        }else {
            selectItemFromResultGrid(itemName);
        }
        return itemName;
    }

    public void selectItemFromResultGrid(String item){
        String parentWindow = DriverManager.getParentWindowID();
        lnkResultItemName.setDynamicValue(item);
        lnkResultItemName.waitForClickable();
        lnkResultItemName.click();
        DriverManager.switchToWindowByID(parentWindow);
    }

    public void selectItemFromResultFood(String item){
        String parentWindow = DriverManager.getParentWindowID();
        lnkFoodItem.setDynamicValue(item);
        lnkFoodItem.waitForClickable();
        lnkFoodItem.click();
        DriverManager.switchToWindowByID(parentWindow);
    }

    public String getRandomItemFromResultFood() {
//        lnkResultFoodItem.waitForVisibility();
        int index = Utils.randomNumber(0, lnkResultFoodItem.getNumberOfElement()-1);
        return getWebElementText(lnkResultFoodItem.getElementByIndex(index));
    }

    public String getRandomItemFromResultGrid(){
//        lnkResultItem.waitForVisibility();
        int index = Utils.randomNumber(0, lnkResultItem.getNumberOfElement()-1);
        return getWebElementText(lnkResultItem.getElementByIndex(index));
    }

    public String getSelectedMenuItem(){
        tabSelectedMenuItem.waitForVisibility();
        return tabSelectedMenuItem.getText();
    }

    public void selectTopRateTab(){
        tabTopRate.waitForClickable();
        tabTopRate.click();
        lnkDGTN.waitForVisibility();
        DriverManager.sleepInSecond(2);
    }

//    public String selectResultGridFirstItem(){
//        String parentWindow = DriverManager.getParentWindowID();
//        String itemName = null;
//        lnkResultGridFirstItem.waitForVisibility();
//        if(lnkResultGridFirstItem.getAttribute("data-bind").contains("BranchName")){
//            lnkResultGridFirstItem.waitForClickable();
//            lnkResultGridFirstItem.click();
//            DriverManager.switchToWindowByID(parentWindow);
//            parentWindow = DriverManager.getParentWindowID();
//            lnkFirstFoodItem.waitForClickable();
//            itemName = lnkFirstFoodItem.getText();
//            lnkFirstFoodItem.click();
//            DriverManager.switchToWindowByID(parentWindow);
//        }else {
//            lnkResultGridFirstItem.waitForClickable();
//            itemName = lnkResultGridFirstItem.getText();
//            lnkResultGridFirstItem.click();
//            DriverManager.switchToWindowByID(parentWindow);
//        }
//        return itemName;
//    }

    public boolean ifSearchGridItemContains(String string){
        lnkResultItem.waitForAllElementVisible();
        List<WebElement> items = lnkResultItem.getElements();
        for(WebElement item : items){
            return getWebElementText(item).contains(string);
        }
        return false;
    }



}
