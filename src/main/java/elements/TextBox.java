package elements;

import common.helpers.Logger;

public class TextBox extends BaseElement{
    public TextBox(String xpath) {
        super(xpath);
    }

    public void clear(){
        try {
            Logger.info("Clear text of element: " + this.getXpath());
            getElement().clear();
        }catch (Exception e){
            Logger.error("Cannot clear text of element: " + this.getXpath());
            e.printStackTrace();
        }
    }

    public void enter(String value){
        try {
            clear();
            Logger.info("Enter <" + value + "> to " + this.getXpath());
            getElement().sendKeys(value);
        }catch (Exception e){
            Logger.error("Cannot enter <" + value + "> to " + this.getXpath());
            e.printStackTrace();
        }
    }
}
