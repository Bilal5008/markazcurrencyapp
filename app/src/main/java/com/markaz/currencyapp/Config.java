package com.markaz.currencyapp;

import static java.lang.System.getProperties;
import java.util.Properties;

public final class Config {
    public static final String BASE_URL = "https://openexchangerates.org/";

    private static final String ACCESS_TOKEN = "access_key=";
    private static final long ONE_MIN_IN_MILLIS = 60 * 1000;


    private static final long ONE_DAY_IN_MILLIS = 24 * 60 * ONE_MIN_IN_MILLIS;


    private Config() {

    }


    public static String getProperty(String propertyKey) {
        Properties properties = getProperties();
        String key = null;
        key = properties.getProperty(propertyKey);
        return key;
    }

    public static String getBaseURLForRetrofit()
    {
        return getProperty(BASE_URL);
    }


}
