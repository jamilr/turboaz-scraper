package org.turboaz.scraper.entry;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.turboaz.scraper.service.ScraperService;

/**
 * Created with IntelliJ IDEA.
 * User: jr
 * Date: 3/11/14
 * Time: 12:14 PM
 * To change this template use File | Settings | File Templates.
 */

public class TurboAzScraperStandalone {

    private static ApplicationContext context;

    public static void main(String... args) {

        context = new ClassPathXmlApplicationContext(new String[] {"turboaz-context.xml"});
        ScraperService scraperService = (ScraperService)context.getBean("TURBOAZSCRAPER");
        scraperService.scrape();
    }

}
