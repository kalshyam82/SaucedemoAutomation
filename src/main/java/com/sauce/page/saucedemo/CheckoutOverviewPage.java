package com.sauce.page.saucedemo;

import com.sauce.page.AbstractPageObject;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutOverviewPage extends AbstractPageObject {

    @FindBy(name="finish")
    WebElement btnFinish;
@Step
    public void clickFinishBtn(){
        btnFinish.click();
    }
}
