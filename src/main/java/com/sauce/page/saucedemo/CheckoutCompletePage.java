package com.sauce.page.saucedemo;

import com.sauce.page.AbstractPageObject;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutCompletePage extends AbstractPageObject {

    @FindBy(xpath="//*[@id='header_container']/div[2]/span")
    WebElement txtCheckoutComplete;
    @Step
    public String validateCheckout(){

        return txtCheckoutComplete.getText();
    }
}
