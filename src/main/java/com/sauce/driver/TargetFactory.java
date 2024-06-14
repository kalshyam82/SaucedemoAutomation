
package com.sauce.driver;

import com.sauce.enums.Target;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import static com.sauce.config.ConfigurationManager.configuration;
import static com.sauce.driver.BrowserFactory.valueOf;


public class TargetFactory {

    private static final Logger logger = LogManager.getLogger(TargetFactory.class);

    public WebDriver createInstance(String browser) {
        Target target = Target.get(configuration().target().toUpperCase());

        return switch (target) {
            case LOCAL -> valueOf(configuration().browser().toUpperCase()).createLocalDriver();
            case LOCAL_SUITE -> valueOf(browser.toUpperCase()).createLocalDriver();
        };
    }

}
