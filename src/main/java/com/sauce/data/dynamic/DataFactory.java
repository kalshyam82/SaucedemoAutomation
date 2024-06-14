
package com.sauce.data.dynamic;

import net.datafaker.Faker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class DataFactory {

    public static final Faker faker = new Faker();
    private static final Logger logger = LogManager.getLogger(DataFactory.class);

    private DataFactory() {
    }

    public static String getTxtFname() {
        return faker.name().firstName();
    }

    public static String getTxtLname() {
        return faker.name().lastName();
    }

    public static String getTxtPostalCode() {
        return faker.address().zipCode();
    }

}
