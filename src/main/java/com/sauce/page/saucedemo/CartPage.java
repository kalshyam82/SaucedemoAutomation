package com.sauce.page.saucedemo;

import com.sauce.page.AbstractPageObject;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends AbstractPageObject {

    @FindBy(name="checkout")
    WebElement btnCheckout;

    @Step
   public void checkout(){
        btnCheckout.click();
    }

}
