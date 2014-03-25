package org.turboaz.scraper.domain;

/**
 * Created with IntelliJ IDEA.
 * User: jr
 * Date: 3/11/14
 * Time: 1:51 PM
 * To change this template use File | Settings | File Templates.
 */

public class Seller {

    private String name;

    private String[] phone;

    public Seller(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getPhone() {
        return phone;
    }

    public void setPhone(String[] phone) {
        this.phone = phone;
    }
}
