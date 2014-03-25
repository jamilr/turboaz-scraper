package org.turboaz.scraper.util;

import org.turboaz.scraper.domain.CarRecord;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: jr
 * Date: 3/11/14
 * Time: 4:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class Constants {

    public static final String PROPS_FILENAME = "app.properties";

    public static final String JSON_FILE = "data.json";

    public static Map<String, String> TURBO_AZ_MAP = new HashMap<String, String>();
    static {
        TURBO_AZ_MAP.put("ad_make_id", "maker");
        TURBO_AZ_MAP.put("ad_model", "model");
        TURBO_AZ_MAP.put("ad_reg_year", "year");
        TURBO_AZ_MAP.put("ad_category", "category");
        TURBO_AZ_MAP.put("ad_color", "color");
        TURBO_AZ_MAP.put("ad_engine_volume", "volume");
        TURBO_AZ_MAP.put("ad_power", "power");
        TURBO_AZ_MAP.put("ad_fuel_type", "petroleum");
        TURBO_AZ_MAP.put("ad_mileage", "mileage");
        TURBO_AZ_MAP.put("ad_transmission", "transmission");
        TURBO_AZ_MAP.put("ad_gear", "gear");
        TURBO_AZ_MAP.put("ad_new", "newCar");
        TURBO_AZ_MAP.put("ad_price", "price");
    }

    public static Map<String, Field> CAR_RECORD_FIELD_MAP = new LinkedHashMap<String, Field>();
    static {
        Field[] fields = CarRecord.class.getDeclaredFields();
        for (Field field: fields) {
            field.setAccessible(true);
            CAR_RECORD_FIELD_MAP.put(field.getName(), field);
        }
    }
}
