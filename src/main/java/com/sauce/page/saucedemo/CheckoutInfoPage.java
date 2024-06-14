package com.sauce.page.saucedemo;

import com.sauce.data.dynamic.DataFactory;
import com.sauce.page.AbstractPageObject;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Test;
public class CheckoutInfoPage extends AbstractPageObject {

    @FindBy(id="first-name")
    WebElement txtFname;

    @FindBy(id="last-name")
    WebElement txtLname;

    @FindBy(id="postal-code")
    WebElement txtPostalCode;

    @FindBy(name="continue")
    WebElement btnContinue;
    @Step
    public void FillyourInfomation(){

        txtFname.sendKeys(DataFactory.getTxtFname());
        txtLname.sendKeys(DataFactory.getTxtLname());
        txtPostalCode.sendKeys(DataFactory.getTxtPostalCode());
        btnContinue.click();
    }
}
