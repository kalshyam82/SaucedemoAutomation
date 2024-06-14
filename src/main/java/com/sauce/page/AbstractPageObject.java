
package com.sauce.page;

import com.sauce.driver.DriverManager;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import static com.sauce.config.ConfigurationManager.configuration;
import static org.openqa.selenium.support.PageFactory.initElements;

public class AbstractPageObject {

    protected AbstractPageObject() {
        initElements(new AjaxElementLocatorFactory(DriverManager.getDriver(), configuration().timeout()), this);
    }
}
