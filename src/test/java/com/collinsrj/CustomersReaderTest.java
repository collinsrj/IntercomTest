package com.collinsrj;

import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Created by collinsrj on 09/05/2017.
 */
public class CustomersReaderTest {

    @Test
    public void testSingleUserSameLocation() throws Exception {
        File testFile = new File(CustomersReaderTest.class.getResource("singlecustomer.txt").getFile());
        CustomersReader reader = new CustomersReader(testFile);
        Location testLocation = new Location("52.833502", "-8.522366");
        List<Customer> customers = new ArrayList<>();
        reader.handleNearbyUsers(testLocation, 1000, customers::add);

        Customer[] expected = new Customer[]{new Customer(25, "David Behan", testLocation)};

        assertArrayEquals(expected, customers.toArray(new Customer[customers.size()]));
    }

    @Test
    public void testFarAwayUser() throws Exception {
        File testFile = new File(CustomersReaderTest.class.getResource("singlecustomer.txt").getFile());
        CustomersReader reader = new CustomersReader(testFile);
        Location testLocation = new Location("10.000", "10.000");
        List<Customer> customers = new ArrayList<>();
        reader.handleNearbyUsers(testLocation, 1000, customers::add);

        Customer[] expected = new Customer[]{};
        Customer[] actual = customers.toArray(new Customer[customers.size()]);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void testSampleFile() throws Exception {
        File testFile = new File(CustomersReaderTest.class.getResource("customers.txt").getFile());
        CustomersReader reader = new CustomersReader(testFile);
        Location intercomOffice = new Location("53.3393", "-6.2576841");
        List<Customer> customers = new ArrayList<>();
        reader.handleNearbyUsers(intercomOffice, 100000, customers::add);
        int expectedLength = 16;
        int actualLength = customers.toArray(new Customer[customers.size()]).length;

        assertEquals(expectedLength, actualLength);
    }

    @Test
    public void testSampleFileBadRecord() throws Exception {
        File testFile = new File(CustomersReaderTest.class.getResource("customersbadJSON.txt").getFile());
        CustomersReader reader = new CustomersReader(testFile);
        Location intercomOffice = new Location("53.3393", "-6.2576841");
        List<Customer> customers = new ArrayList<>();
        reader.handleNearbyUsers(intercomOffice, 100000, customers::add);
        int expectedLength = 1;
        int actualLength = customers.toArray(new Customer[customers.size()]).length;

        assertEquals(expectedLength, actualLength);
    }

    @Test
    public void testRecordMissingLocation() throws Exception {
        File testFile = new File(CustomersReaderTest.class.getResource("customersMissingLocation.txt").getFile());
        CustomersReader reader = new CustomersReader(testFile);
        Location intercomOffice = new Location("53.3393", "-6.2576841");
        List<Customer> customers = new ArrayList<>();
        reader.handleNearbyUsers(intercomOffice, 100000, customers::add);

        int expectedLength = 0; // there's only a single record and the location is missing
        int actualLength = customers.toArray(new Customer[customers.size()]).length;

        assertEquals(expectedLength, actualLength);
    }
}
