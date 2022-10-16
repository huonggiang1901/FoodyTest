package pages;

import elements.BaseElement;

public class FoodyFoodItemPage extends GeneralPage{

    BaseElement foodItemName = new BaseElement("//h1[@itemprop='name']");
    BaseElement foodItemStreet = new BaseElement("//span[@itemprop='streetAddress']");

    public String getFoodItemName(){
        foodItemName.waitForVisibility();
        return foodItemName.getText();
    }

    public  boolean isFoodItemStreetDisplayed(){
        return foodItemStreet.isDisplayed();
    }

}
