# Sauce Demo Test Automation using Java and Selenium WebDriver


![Parallel testing execution example with Selenium](assets/Build.png)

**This project delivers to you a complete test automation for Saucedemo web tests using the best frameworks and
practices.**

It has a complete solution to run tests in different ways:

* local testing using the browser on your local machine


## Examples

### Local testing execution example

![Local testing execution example](assets/Test_Execution.gif)


## Languages and Frameworks

This project uses the following languages and frameworks:

* [Java 22](https://openjdk.java.net/projects/jdk/22/) as the programming language
* [TestNG](https://testng.org/doc/) as the UnitTest framework to support the test creation
* [Selenium WebDriver](https://www.selenium.dev/) as the web browser automation framework using the Java binding
* [AssertJ](https://joel-costigliola.github.io/assertj/) as the fluent assertion library
* [Allure Report](https://docs.qameta.io/allure/) as the testing report strategy
* [DataFaker](https://www.datafaker.net/) as the faker data generation strategy
* [Log4J2](https://logging.apache.org/log4j/2.x/) as the logging management strategy
* [Owner](http://owner.aeonbits.org/) to minimize the code to handle the properties file
* [TestContainers](https://java.testcontainers.org/modules/webdriver_containers/) Webdriver Containers

## Test architecture

We know that any automation project starts with a good test architecture.

This project can be your initial test architecture for a faster start.
You will see the following items in this architecture:

* [Page Objects pattern](#page-objects-pattern)
* [Execution types](#execution-types)
* [BaseTest](#basetest)
* [TestListener](#testlistener)
* [Logging](#logging)
* [Configuration files](#configuration-files)
* [Test Data Factory](#test-data-factory)
* [Profiles executors on pom.xml](#profiles-executors-on-pomxml)


Do you have any other items to add to this test architecture? Please do a pull request or open an issue to discuss.

### Page Objects pattern

I will not explain the Page Object pattern because you can find a lot of good explanations and examples on the internet.
Instead, I will explain what exactly about page objects I'm using in this project.

#### AbstractPageObject

This class has a protected constructor to remove the necessity to init the elements using the Page Factory.
Also, it sets the timeout from the `timeout` property value located on `general.properties` file.

All the Page Object classes should extend the `AbstractPageObject`.
It also tries to remove the `driver` object from the Page Object class as much as possible.

As much as possible avoid this strategy to not get an `ElementNotFoundException` or `StaleElementReferenceException`.
Use this approach if you know that the page does not refresh.

### Execution types

There are different execution types:

- `local`
- `local-suite`


The `TargetFactory` class will resolve the target execution based on the `target` property value located
on `general.properties` file. Its usage is placed on the `BaseWeb` class before each test execution.

#### Local execution

##### Local machine

**This approach is automatically used when you run the test class in your IDE.**

When the `target` is `local` the `createLocalDriver()` method is used from the `BrowserFactory` class to return the
browser instance.

The browser used in the test is placed on the `browser` property in the `general.properties` file.

##### Local Suite

It's the same as the Local Execution, where the difference is that the browser is taken from the TestNG suite file instead of the `general.properties`
file, enabling you to run multi-browser test approach locally.



#### BrowserFactory class

This Factory class is a Java enum that has all implemented browsers to use during the test execution.
Each browser is an `enum`, and each enum implements four methods:

* `createLocalDriver()`: creates the browser instance for the local execution. The browser driver is automatically
  managed by the WebDriverManager library
* `createDriver()`: creates the browser instance for the remote execution
* `getOptions()`: creates a new browser `Options` setting some specific configurations, and it's used for the remote
  executions using the Selenium Grid
* `createTestContainerDriver()` : Creates selenium grid lightweight test container in Standalone mode with Chrome/Firefox/Edge browser support.

You can see that the `createLocalDriver()` method use the `getOptions()` to get specific browser configurations, as
starting the browser maximized and others.

The `getOptions()` is also used for the remote execution as it is a subclass of the `AbstractDriverOptions` and can be
automatically accepted as either a `Capabilities` or `MutableCapabilities` class, which is required by
the `RemoteWebDriver` class.

#### DriverManager class

The
class DriverManager create a `ThreadLocal` for the WebDriver instance, to make sure there's no conflict when we run it in parallel.

### BaseTest

This testing pattern was implemented on
the BaseWeb
class to automatically run the pre (setup) and post (teardown) conditions.

The pre-condition uses `@BeforeMethod` from TestNG creates the browser instance based on the values passed either local
or remote execution.
The post-condition uses `@AfterMethod` to close the browser instance.
Both have the `alwaysRun` parameter as `true` to force the run on a pipeline.

Pay attention that it was designed to open a browser instance to each `@Test` located in the test class.

This class also has the `TestListener` annotation which is a custom TestNG listener, and will be described in the next
section.

### TestListener

The `TestListener` is a class that
implements [ITestListener](https://testng.org/doc/documentation-main.html#logging-listeners).
The following method is used to help logging errors and attach additional information to the test report:

* `onTestStart`: add the browser information to the test report
* `onTestFailure`: log the exceptions and add a screenshot to the test report
* `onTestSkipped`: add the skipped test to the log

### Logging

All the log is done by the Log4J using the `@Log4j2` annotation.

The `log4j2.properties` has two strategies: console and file.
A file with all the log information will be automatically created on the user folder with `test_automation.log`
filename.
If you want to change it, update the `appender.file.fileName` property value.

The `log.error` is used to log all the exceptions this architecture might throw. Use `log.info` or `log.debug` to log
important information, like the users, automatically generated by the
factory DataFactory

### Configuration files

This project uses a library called [Owner](http://owner.aeonbits.org/). You can find the class related to the property
file reader in the following classes:

* Configuration
* ConfigurationManager

There are 2 properties (configuration) files located on `src/test/java/resources/`:

* `general.properties`: general configuration as the target execution, browser, base url, timeout, and faker locale
* `grid.properties`: url and port for the Selenium grid usage

The properties were divided into three different ones to better separate the responsibilities and enable the changes
easy without having a lot of properties inside a single file.

### Test Data Factory

Is the utilization of the Factory design pattern with the Fluent Builder to generate dynamic data.
The [BookingDataFactory](https://github.com/eliasnogueira/selenium-java-lean-test-achitecture/blob/master/src/main/java/com/eliasnogueira/data/BookingDataFactory.java)
has only one factory `createBookingData` returning a `Booking` object with dynamic data.

This dynamic data is generated by JavaFaker filling all the fields using the Build pattern.

Reading reference: https://reflectoring.io/objectmother-fluent-builder

### Profiles executors on pom.xml

There is a profile called `web-execution` created to execute the test suite `local.xml`
inside `src/test/resources/suites` folder.
To execute this suite, via the command line you can call the parameter `-P` and the profile id.

Eg: executing the multi_browser suite

``` bash
mvn test -Pweb-execution -Dtestng.dtd.http=true 
```

If you have more than one suite on _src/test/resources/suites_ folder you can parameterize the xml file name.
To do this you need:

* Create a property on `pom.xml` called _suite_

```xml

<properties>
    <suite>local</suite>
</properties>
```

* Change the profile id

```xml

<profile>
    <id>web-execution</id>
</profile>   
```

* Replace the xml file name to `${suite}` on the profile

```xml

<configuration>
    <suiteXmlFiles>
        <suiteXmlFile>src/test/resources/suites/${suite}.xml</suiteXmlFile>
    </suiteXmlFiles>
</configuration>
```
