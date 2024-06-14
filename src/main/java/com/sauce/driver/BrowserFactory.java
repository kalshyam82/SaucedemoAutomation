
package com.sauce.driver;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;
import static com.sauce.config.ConfigurationManager.configuration;
import static com.sauce.data.changeless.BrowserData.*;


public enum BrowserFactory {

    CHROME {
        @Override
        public WebDriver createLocalDriver() {
            return new ChromeDriver(getOptions());
        }


        @Override
        public ChromeOptions getOptions() {
            var chromeOptions = new ChromeOptions();
            chromeOptions.addArguments(START_MAXIMIZED);
            chromeOptions.addArguments(DISABLE_INFOBARS);
            chromeOptions.addArguments(DISABLE_NOTIFICATIONS);
            chromeOptions.addArguments(REMOTE_ALLOW_ORIGINS);

            if (configuration().headless()) chromeOptions.addArguments(CHROME_HEADLESS);

            return chromeOptions;
        }
    }, EDGE {
        @Override
        public WebDriver createLocalDriver() {
            return new EdgeDriver(getOptions());
        }


        @Override
        public EdgeOptions getOptions() {
            var edgeOptions = new EdgeOptions();
            edgeOptions.addArguments(START_MAXIMIZED);

            if (configuration().headless()) edgeOptions.addArguments(GENERIC_HEADLESS);

            return edgeOptions;
        }
    };


    /**
     * Used to run local tests where the WebDriverManager will take care of the driver
     *
     * @return a new WebDriver instance based on the browser set
     */
    public abstract WebDriver createLocalDriver();

    /**
     * @return a new AbstractDriverOptions instance based on the browser set
     */
    public abstract AbstractDriverOptions<?> getOptions();

}
