
package com.sauce.test;

import com.sauce.BaseWeb;
import com.sauce.page.saucedemo.*;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import static com.sauce.config.ConfigurationManager.configuration;

public class SauceDemoWebTest extends BaseWeb {

    @Test(description = "Complete the End 2 End flow by adding 3 items in Saucedemo cart")
    public void sauceProductCheckout() {
        var loginPage = new LoginPage();
        loginPage.fillUserName(configuration().username());
        loginPage.fillPassword(configuration().password());
        loginPage.clickLogin();
        var productPage = new ProductsPage();
        var actCartCount=productPage.selectProducts();
        Assertions.assertThat(Integer.valueOf(actCartCount)).isEqualTo(3);
        var cartPage = new CartPage();
        cartPage.checkout();
        var checkoutInfoPage = new CheckoutInfoPage();
        checkoutInfoPage.FillyourInfomation();
        var checkoutOverviewPage = new CheckoutOverviewPage();
        checkoutOverviewPage.clickFinishBtn();
        var checkoutCompletepage = new CheckoutCompletePage();
        String actCheckout= checkoutCompletepage.validateCheckout();
        Assertions.assertThat(actCheckout).isEqualTo("Checkout: Completee!");
    }
}
