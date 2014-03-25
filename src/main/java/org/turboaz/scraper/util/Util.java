package org.turboaz.scraper.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.IllegalFormatException;

/**
 * Created with IntelliJ IDEA.
 * User: jr
 * Date: 3/11/14
 * Time: 4:48 PM
 * To change this template use File | Settings | File Templates.
 */

public class Util {

    private static Logger logger = LoggerFactory.getLogger(Util.class.getSimpleName());

    public static Integer convertStringToInt(String source) {

        Integer value = null;

        try {
            value = Integer.parseInt(source);
        } catch (IllegalFormatException ex) {
            logger.error(ex.getMessage());
        }

        return value;
    }
}
