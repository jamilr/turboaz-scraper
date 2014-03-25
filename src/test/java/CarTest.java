import org.junit.Test;
import org.turboaz.scraper.domain.CarRecord;

import static junit.framework.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: jr
 * Date: 3/11/14
 * Time: 1:55 PM
 * To change this template use File | Settings | File Templates.
 */

public class CarTest {

    @Test
    public void testGetSetProperties() {

        CarRecord car = new CarRecord();
        car.setCategory("Jeep");
        assertEquals("Jeep", car.getCategory());
    }
}
