package org.turboaz.scraper.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: jr
 * Date: 3/11/14
 * Time: 4:42 PM
 * To change this template use File | Settings | File Templates.
 */

public class PropertiesUtil {

    private static Properties prop;

    static {
        prop = new Properties();
        loadProperties();
    }

    public static Properties getInstance() {
        return prop;
    }

    private static Properties loadProperties() {
        try {
            prop.load( new FileInputStream(Constants.PROPS_FILENAME));
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }
        return prop;
    }
}
