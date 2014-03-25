package org.turboaz.scraper.service;

/**
 * Created with IntelliJ IDEA.
 * User: jr
 * Date: 3/24/14
 * Time: 11:17 AM
 * To change this template use File | Settings | File Templates.
 */
public interface JSONWriterService {

    void setFileName(String fileName);
    void write(Object object);
    void close();

}
