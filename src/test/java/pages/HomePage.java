package pages;

import elements.BaseElement;

public class HomePage extends GeneralPage{

    BaseElement deliveryTitle = new BaseElement("//div[@class='title' and text()='Giao tận nơi']");

    public boolean isDeliveryTitleDisplayed(){
        return deliveryTitle.isDisplayed();
    }

}
