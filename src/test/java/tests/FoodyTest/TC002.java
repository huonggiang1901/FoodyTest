package tests.FoodyTest;

import common.helpers.AssertHelper;
import common.helpers.Logger;
import io.qameta.allure.Description;
import listeners.TestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SearchResultPage;
import tests.TestBase;

@Listeners({TestListener.class})
public class TC002 extends TestBase {

    HomePage homePage = new HomePage();
    SearchResultPage searchResultPage = new SearchResultPage();

    @Test()
    @Description("Verify user can search restaurant")
    public void TC002() {

        Logger.step("1. Navigate to \"Foody\" website");
        Logger.step("2. Select \"Tỉnh Thành\" combobox: TP.HCM");
        homePage.selectCity("TP. HCM");

        Logger.step("3. Select \"Category\" combobox: Ăn Uống -> Quán Ăn");
        homePage.selectCategoryItem("Ăn uống", "Quán ăn");

        Logger.step("4. Enter value in \"Filter\" textbox: Phở Bò");
        Logger.step("5. Click \"Search\" button");
        searchResultPage = homePage.searchItem("Phở Bò");

        Logger.verify("Verify that the name of all displayed items in the result grid contains \"Phở/Bò\"");
        AssertHelper.checkTrue(searchResultPage.ifSearchGridItemContains("Phở Bò"));

    }

}
