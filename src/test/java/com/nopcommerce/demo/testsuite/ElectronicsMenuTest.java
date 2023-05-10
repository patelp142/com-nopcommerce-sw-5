package com.nopcommerce.demo.testsuite;

import com.nopcommerce.demo.customlisteners.CustomListeners;
import com.nopcommerce.demo.pages.*;
import com.nopcommerce.demo.testbase.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(CustomListeners.class)
public class ElectronicsMenuTest extends BaseTest {
    HomePage homePage;
    ComputerPage computerPage;
    DesktopPage desktopPage;
    AddToCartPage addToCartPage;
    ShoppingCartPage shoppingCartPage;
    CheckOutPage checkOutPage;
    GuestCheckOutPage guestCheckOutPage;
    CellPhonesPage cellPhonesPage;

    @BeforeMethod(alwaysRun = true)
    public void inIt() {
        homePage = new HomePage();
        computerPage = new ComputerPage();
        desktopPage = new DesktopPage();
        addToCartPage = new AddToCartPage();
        shoppingCartPage = new ShoppingCartPage();
        checkOutPage = new CheckOutPage();
        guestCheckOutPage = new GuestCheckOutPage();
        cellPhonesPage = new CellPhonesPage();
    }

    //1. Test name verifyUserShouldNavigateToCellPhonesPageSuccessfully()
    @Test(groups = {"sanity", "regression"})
    public void verifyUserShouldNavigateToCellPhonesPageSuccessfully() throws InterruptedException {
        //1.1 Mouse Hover on “Electronics” Tab
        homePage.mouseHoverToElectronic();
        //1.2 Mouse Hover on “Cell phones” and click
        Thread.sleep(1000);
        homePage.mouseHoverToElectronicAndClickOnCellPhone();
        //1.3 Verify the text “Cell phones”
        Assert.assertEquals(cellPhonesPage.verifyTextCellPhone(), "Cell phones");
    }

    @Test(groups = {"smoke", "regression"})
    //2. Test name verifyThatTheProductAddedSuccessfullyAndPlaceOrderSuccessfully()
    public void verifyThatTheProductAddedSuccessfullyAndPlaceOrderSuccessfully() throws InterruptedException {
        //2.1 Mouse Hover on “Electronics” Tab
        homePage.mouseHoverToElectronic();
        //2.2 Mouse Hover on “Cell phones” and click
        homePage.mouseHoverToElectronicAndClickOnCellPhone();
        //2.3 Verify the text “Cell phones”
        Assert.assertEquals(cellPhonesPage.verifyTextCellPhone(), "Cell phones");
        //2.4 Click on List View Tab
        cellPhonesPage.clickOnListView();
        //2.5 Click on product name “Nokia Lumia 1020” link
        Thread.sleep(2000);
        // waitUntilVisibilityOfElementLocated(By.xpath("//a[contains(text(),'Nokia Lumia 1020')]"),5);
        cellPhonesPage.clickOnNokiaLumia1020();
        //2.6 Verify the text “Nokia Lumia 1020”
        Assert.assertEquals(cellPhonesPage.verifyTextNokiaLumia1020(), "Nokia Lumia 1020");
        //2.7 Verify the price “$349.00”
        Assert.assertEquals(cellPhonesPage.verifyPrice(), "$349.00");
        //2.8 Change quantity to 2
        cellPhonesPage.changeThatQty();
        //2.9 Click on “ADD TO CART” tab
        cellPhonesPage.clickOnAddToCart();
        //2.10 Verify the Message "The product has been added to your shopping cart" on Top green Bar
        Assert.assertEquals(cellPhonesPage.verifyAddToCartSuccessfully(), "The product has been added to your shopping cart");
        //After that close the bar clicking on the cross button.
        cellPhonesPage.clickOnCrossButton();
        //2.11 Then MouseHover on "Shopping cart" and Click on "GO TO CART" button
        cellPhonesPage.mouseHoverToShoppingCart();
        //2.12 Verify the message "Shopping cart"
        shoppingCartPage.verifyShoppingCartText();
        //2.13 Verify the quantity is 2
        Assert.assertEquals(shoppingCartPage.verifyQty(), "2");
        //2.14 Verify the Total $698.00
        Assert.assertEquals(shoppingCartPage.verifyPriceText(), "$698.00");
    }
}
