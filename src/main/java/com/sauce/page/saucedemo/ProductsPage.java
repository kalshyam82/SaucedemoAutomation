
package com.sauce.page.saucedemo;

import com.sauce.data.dynamic.DataFactory;
import com.sauce.driver.DriverManager;
import com.sauce.page.AbstractPageObject;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProductsPage extends AbstractPageObject {

    @FindBy(xpath="//div[@id='inventory_container']/div/div[@id='inventory_container']/div/div//*/button")
    List<WebElement> selectProduct;

    @FindBy(xpath = "//div[@id='shopping_cart_container']")
    WebElement iShoppingCart;

    @FindBy(xpath = "//div[@id='shopping_cart_container']/a/span")
    WebElement iShoppingCartCount;

    @Step
    public String selectProducts(){
        selectProduct.get(DataFactory.faker.number().numberBetween(0,1)).click();
        selectProduct.get(DataFactory.faker.number().numberBetween(2,3)).click();
        selectProduct.get(DataFactory.faker.number().numberBetween(4,5)).click();
        var cartCount =iShoppingCartCount.getText();
        iShoppingCart.click();
        return cartCount;
//
//        List<WebElement> wb= DriverManager.getDriver().findElements(By.xpath("//div[@class='cart_item']"));
//        System.out.println("count****-->"+wb.size());
//        DriverManager.getDriver().findElement(By.name("checkout")).click();
//        DriverManager.getDriver().findElement(By.id("first-name")).sendKeys("Shyam");
//        DriverManager.getDriver().findElement(By.id("last-name")).sendKeys("Kal");
//        DriverManager.getDriver().findElement(By.id("postal-code")).sendKeys("123456");
//        DriverManager.getDriver().findElement(By.name("continue")).click();
//        DriverManager.getDriver().findElement(By.name("finish")).click();
//        System.out.println("TITLE******"+DriverManager.getDriver().findElement(By.xpath("//*[@id='header_container']/div[2]/span")).getText());
    }
}
