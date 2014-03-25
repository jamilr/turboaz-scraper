package org.turboaz.scraper.util;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.SocketTimeoutException;

/**
 * Created with IntelliJ IDEA.
 * User: jr
 * Date: 3/11/14
 * Time: 4:40 PM
 * To change this template use File | Settings | File Templates.
 */

public class ScraperUtil {

    private static Logger logger = LoggerFactory.getLogger(ScraperUtil.class.getSimpleName());

    public static Document loadHTMLDocument(String url) {

        Document document = null;
        try {

            Connection connection = Jsoup.connect(url);

            document = connection
                    .timeout(Util.convertStringToInt(PropertiesUtil.getInstance().getProperty("con-timeout")))
                    .userAgent("Mozilla")
                    .get();

        } catch(SocketTimeoutException socEx) {
            logger.error(socEx.getMessage(), socEx);
        } catch (IOException ex) {
            logger.error(ex.getMessage(), ex);
        }

        return document;
    }

    public static String extractMaker(Element el){
        return new String();
    }
}
