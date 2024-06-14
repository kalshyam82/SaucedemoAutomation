

package com.sauce.page.saucedemo;

import com.sauce.page.AbstractPageObject;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class LoginPage extends AbstractPageObject {

    @FindBy(id = "user-name")
    private WebElement txtUserName;

    @FindBy(id = "password")
    private WebElement txtPassword;

    @FindBy(id="login-button")
    private WebElement btnLogin;

    @Step
    public void fillUserName(String userName){
        this.txtUserName.sendKeys(userName);
    }

    @Step
    public void fillPassword(String password) {
        this.txtPassword.sendKeys(password);
    }
    @Step
    public void clickLogin(){
        this.btnLogin.click();
    }


}
