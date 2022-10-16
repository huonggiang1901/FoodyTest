package pages;

import driver.DriverManager;
import elements.*;

public class GeneralPage {

    TextBox txtFilter = new TextBox("//input[@id='pkeywords']");
    Button btnSearch = new Button("//a[@class='ico-search']//span[@class='fa fa-search']");
    Button btnShowCity = new Button("//div[@id='head-province']//span[@class='fa fa-sort-desc']");
    BaseElement cityList = new BaseElement("//ul//label[@class='ng-binding']");
    Button btnShowCategory = new Button("//div[@id='head-navigation']//span[@class='fa fa-sort-desc']");
    Link lnkCategory = new Link("//div[contains(.,'Select category')]/ancestor::li/following-sibling::li//span[contains(text(),'%s')]");
    Link lnkCategoryMenu = new Link("//span[text()='%s']/parent::a/following-sibling::ul[@class='menu-box']");
    Link lnkCategoryMenuItem = new Link("//span[text()='%s']/parent::a/following-sibling::ul//a[@title='%s']");
    BaseElement loading = new BaseElement("//img[@class='loading']");

    public void inputFilterValue(String item){
        txtFilter.waitForVisibility();
        txtFilter.enter(item);
    }

    public void clickSearchBtn(){
        btnSearch.waitForClickable();
        btnSearch.click();
    }

    public SearchResultPage searchItem(String item){
        inputFilterValue(item);
        clickSearchBtn();
        return new SearchResultPage();
    }

    public void selectCity(String cityName){
        SelectBox.selectItemInCustomDropdown(btnShowCity, cityList, cityName);
    }

    public void clickShowCategoryBtn(){
        btnShowCategory.waitForClickable();
        btnShowCategory.click();
    }

    public void clickCategory(String categoryName){
        lnkCategory.setDynamicValue(categoryName);
        lnkCategory.waitForVisibility();
        lnkCategory.moveMouse();
    }

    public void hoverToCategoryMenu(String category){
        lnkCategoryMenu.setDynamicValue(category);
        lnkCategoryMenu.waitForVisibility();
        lnkCategoryMenu.moveMouse();
    }

    public void clickCategoryMenuItem(String category, String item) {
        lnkCategoryMenuItem.setDynamicValue(category, item);
        lnkCategoryMenuItem.scrollToBottom();
        lnkCategoryMenuItem.click();
    }

    public void selectCategoryItem(String category, String item){
        clickShowCategoryBtn();
        clickCategory(category);
        hoverToCategoryMenu(category);
        clickCategoryMenuItem(category, item);
    }

}
