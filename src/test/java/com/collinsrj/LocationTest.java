package com.collinsrj;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by collinsrj on 09/05/2017.
 */
public class LocationTest {
    @Test
    public void testDistance() throws Exception {
        Location intercomOffice = new Location("53.3393", "-6.2576841");
        Location theSpireOConnellSt = new Location("53.349809", "-6.2624431");

        double actual = intercomOffice.distanceTo(theSpireOConnellSt);
        assertEquals(1211, actual, 1.0);
    }

    @Test
    public void testDistanceToSameLocation() throws Exception {
        Location intercomOffice = new Location("53.3393", "-6.2576841");

        double actual = intercomOffice.distanceTo(intercomOffice);
        assertEquals(0, actual, 0.0);
    }

    /**
     * Just sanity check the distance makes sense across a meridian.
     */
    @Test
    public void testAcrossGreenwichMeridian() throws Exception {
        // By definiion, at the equator one line of longitude is 60NM or about 111KM
        Location location1 = new Location("0.0", "-1.0");
        Location location2 = new Location("0.0", "1.0");

        double actual = location1.distanceTo(location2);
        assertEquals(222638, actual, 1.0);
    }
}
