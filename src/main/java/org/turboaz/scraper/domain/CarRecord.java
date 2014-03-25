package org.turboaz.scraper.domain;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: jr
 * Date: 3/11/14
 * Time: 1:36 PM
 * To change this template use File | Settings | File Templates.
 */

public class CarRecord
        implements Valuable {

    private String maker;

    private String model;

    private String year;

    private String category;

    private String color;

    private String volume;

    private String power;

    private String petroleum;

    private String mileage;

    private String transmission;

    private String gear;

    private String newCar;

    private String price;

    private String[] attributes;

    private String additional;

    private Seller seller;

    public CarRecord() {

    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getEngineVolume() {
        return volume;
    }

    public void setEngineVolume(String engineVolume) {
        this.volume = engineVolume;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getPetroleum() {
        return petroleum;
    }

    public void setPetroleum(String petroleum) {
        this.petroleum = petroleum;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getGear() {
        return gear;
    }

    public void setGear(String gear) {
        this.gear = gear;
    }

    public String getNewCar() {
        return newCar;
    }

    public void setNewCar(String newCar) {
        this.newCar = newCar;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String[] getAttributes() {
        return attributes;
    }

    public void setAttributes(String[] attributes) {
        this.attributes = attributes;
    }

    public String getAdditional() {
        return additional;
    }

    public void setAdditional(String additional) {
        this.additional = additional;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    @Override
    public boolean correct() {
        return (maker != null && !maker.isEmpty())
                && (model != null && !model.isEmpty())
                && (price != null && !price.isEmpty())
                && (color != null && !color.isEmpty())
                && (newCar != null && !newCar.isEmpty());
    }
}
