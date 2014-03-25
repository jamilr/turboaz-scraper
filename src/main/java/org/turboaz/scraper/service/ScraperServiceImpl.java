package org.turboaz.scraper.service;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.turboaz.scraper.domain.CarRecord;
import org.turboaz.scraper.domain.Seller;
import org.turboaz.scraper.util.Constants;
import org.turboaz.scraper.util.PropertiesUtil;
import org.turboaz.scraper.util.ScraperUtil;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: jr
 * Date: 3/11/14
 * Time: 12:20 PM
 * To change this template use File | Settings | File Templates.
 */

@Service("TURBOAZSCRAPER")
public class ScraperServiceImpl implements ScraperService {

    private static Logger logger = LoggerFactory.getLogger(ScraperServiceImpl.class.getSimpleName());

    private String maker;
    private String model;

    @Autowired
    @Qualifier("JsonWriter")
    private JSONWriterService jsonWriterService;

    public ScraperServiceImpl() {
    }

    @PostConstruct
    protected void init() {
        StringBuilder pathBuilder = new StringBuilder(PropertiesUtil.getInstance().getProperty("data_folder"));
        pathBuilder.append(Constants.JSON_FILE);
        jsonWriterService.setFileName(pathBuilder.toString());


        Field[] fields = CarRecord.class.getDeclaredFields();
        for (Field field: fields)
            field.setAccessible(true);
    }

    @Override
    public void scrape() {

        Document landingPageTurboAZ = ScraperUtil.loadHTMLDocument(PropertiesUtil.getInstance().getProperty("url"));

        Elements carMakersListLeftEls = landingPageTurboAZ.select("div.makes_first");
        Elements carMakersListRightEls = landingPageTurboAZ.select("div.makes_second");

        Elements leftMakers = carMakersListLeftEls.select(".make > a");
        Elements rightMakers = carMakersListRightEls.select(".make > a");

        for (Element maker: leftMakers)
            scrapeCarsByMaker(maker);

        for (Element maker: rightMakers)
            scrapeCarsByMaker(maker);

        jsonWriterService.close();
    }

    public void scrapeCarsByMaker(Element maker) {

        String linkStr = maker.attr("abs:href");
        this.maker = maker.text();

        logger.info("Scraping data for ".concat(this.maker));

        Document carModelDoc = ScraperUtil.loadHTMLDocument(linkStr);

        Elements models = carModelDoc.select("div.make > a");

        List<CarRecord> carList;
        for (Element modelEl: models) {

            String modelURL = modelEl.attr("abs:href");
            this.model = modelEl.text();

            Document carsPageDoc = ScraperUtil.loadHTMLDocument(modelURL);
            Elements cars = carsPageDoc.select("div.cat-i");
            carList = scrapeCarsByModel(cars);
            jsonWriterService.write(carList);
        }
    }

    public List<CarRecord> scrapeCarsByModel(Elements cars) {

        List<CarRecord> carList = new ArrayList<CarRecord>();

        for (Element carEl: cars) {
            String carLinkStr = carEl.select("a[href]").first().attr("abs:href");
            CarRecord car = scrapeCar(carLinkStr);
            if (car.correct())
                carList.add(car);
        }

        return carList;
    }

    public CarRecord scrapeCar(String carLink) {

        CarRecord car = new CarRecord();

        logger.debug(carLink);
        Document carPageDoc = ScraperUtil.loadHTMLDocument(carLink);
        Elements dataBlocks = carPageDoc.select("div.data-block");
        Elements sellerBlock = carPageDoc.select("div.seller-contacts");

        //scraping Car Details
        car = scrapingCarData(car, dataBlocks);

        //scraping seller block
        Seller seller = scrapingSeller(sellerBlock);
        car.setSeller(seller);

        return car;
    }

    private CarRecord scrapingCarData(CarRecord car, Elements dataBlocks) {

        if (dataBlocks.size() < 3) return car;

        //scraping main characteristics block
        Elements carChars = dataBlocks.get(0).select("ul > li.data-i");
        car = scrapingCarDetails(car, carChars);

        //scraping extra features block
        Elements extraDataEls = dataBlocks.get(1).select("ul > li.data-i");
        String[] extraFeatures = scrapeExtraFeatures(extraDataEls);
        car.setAttributes(extraFeatures);

        //scraping additional data block
        String addDataStr = dataBlocks.get(2).select("p").first().text();
        car.setAdditional(addDataStr);

        return car;
    }

    public CarRecord scrapingCarDetails(CarRecord car, Elements carAttrs) {

        if (carAttrs == null || carAttrs.isEmpty())
            return new CarRecord();

        Iterator<Element> iter = carAttrs.iterator();

        while (iter.hasNext()) {

            Element carAttr = iter.next();

            Element carAttrLabel = carAttr.select("label").first();
            if (carAttrLabel == null) continue;;

            String attrName = carAttrLabel.attr("for");
            if (attrName == null || attrName.isEmpty()) continue;;

            String carRecFieldName = Constants.TURBO_AZ_MAP.get(attrName);
            String attrStrVal = carAttr.select("div.data-i-val").text();

//            logger.debug(attrName);
//            logger.debug(carRecFieldName);

            try {
                if (Constants.CAR_RECORD_FIELD_MAP.containsKey(carRecFieldName))
                    Constants.CAR_RECORD_FIELD_MAP.get(carRecFieldName).set(car, attrStrVal);
            } catch (IllegalAccessException ex) {
                logger.error(ex.getMessage());
            }
        }

        return car;
    }

    public Seller scrapingSeller(Elements sellerBlock) {

        if (sellerBlock == null || sellerBlock.isEmpty())
            return new Seller();

        Seller seller = new Seller();

        //implement a logic for scraping seller section
        Elements phoneEls = sellerBlock.select("a.phone");
        String phones[] = new String[phoneEls.size()];

        int i =0;
        Iterator<Element> iterator = phoneEls.iterator();
        while (iterator.hasNext() && i < phones.length) {
            Element phoneEl = iterator.next();
            String phone = phoneEl.text();
            phones[i] = phone;
            i++;
        }

        String sellerName = sellerBlock.select("div.seller-name").text();

        seller.setName(sellerName);
        seller.setPhone(phones);
        return seller;
    }

    public String[] scrapeExtraFeatures(Elements extra) {

        if (extra == null) return new String[0];

        String[] extraFeatures = new String[extra.size()];

        int i=0;
        Iterator<Element> iter = extra.iterator();

        while (iter.hasNext()) {
            Element extraEl = iter.next();
            extraFeatures[i] = extraEl.text();
            i++;
        }

        return extraFeatures;
    }
}
