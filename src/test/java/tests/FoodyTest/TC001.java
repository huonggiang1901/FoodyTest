package tests.FoodyTest;

import common.helpers.AssertHelper;
import common.helpers.Logger;
import io.qameta.allure.Description;
import listeners.TestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.FoodyFoodItemPage;
import pages.HomePage;
import pages.SearchResultPage;
import tests.TestBase;

@Listeners({TestListener.class})
public class TC001 extends TestBase {

    HomePage homePage = new HomePage();
    FoodyFoodItemPage foodyFoodItemPage = new FoodyFoodItemPage();
    SearchResultPage searchResultPage = new SearchResultPage();

    @Test()
    @Description("Verify the homepage information loaded correctly")
    public void TC001() {

        Logger.step("1. Navigate to \"Foody\" website");
        Logger.verify("Verify that: \"Giao tận nơi\" title is displayed");
        AssertHelper.checkTrue(homePage.isDeliveryTitleDisplayed());

        Logger.verify("Verify that: \"Đặt bàn ưu đãi\" title is displayed");
        Logger.info("Verify point is removed");

        Logger.step("2. On home page, enter value in \"Filter\" textbox: \"Cơm Tấm\"");
        Logger.step("3. Click \"Search\" button");
        searchResultPage = homePage.searchItem("Cơm Tấm");

        Logger.verify("Verify that: \"Địa điểm\" menu item is selected");
        AssertHelper.checkEqual(searchResultPage.getSelectedMenuItem(), "Địa điểm");

        Logger.step("4. Click tab \"Đánh giá tốt nhất\"");
        searchResultPage.selectTopRateTab();

        Logger.step("5. Select any item from result grid");
        String itemName = searchResultPage.selectRandomResultFoodItem();

        Logger.verify("Verify that: The selected item is displayed correctly in details in next tab. (Name, address,…)");
        AssertHelper.checkEqual(foodyFoodItemPage.getFoodItemName(),itemName);
        AssertHelper.checkTrue(foodyFoodItemPage.isFoodItemStreetDisplayed());

    }

}
