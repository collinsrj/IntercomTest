package com.collinsrj;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by collinsrj on 09/05/2017.
 */
public class CustomerTest {
    /**
     * Test the natural sort order of {@link Customer}
     */
    @Test
    public void testCompareTo() {
        Customer customer1 = new Customer(1, "Customer 1", null);
        Customer customer2 = new Customer(2, "Customer 2", null);

        assertEquals(-1, customer1.compareTo(customer2));
        assertEquals(1, customer2.compareTo(customer1));
        assertEquals(0, customer1.compareTo(customer1));
    }

    /**
     * Test the natural sort order of {@link Customer}
     */
    @Test
    public void testComparable() {
        Customer customer1 = new Customer(1, "Customer 1", null);
        Customer customer2 = new Customer(2, "Customer 2", null);
        Customer customer3 = new Customer(3, "Customer 3", null);
        Customer[] arrayToSort = new Customer[]{customer3, customer2, customer1};

        Customer[] expected = new Customer[]{customer1, customer2, customer3};

        Arrays.sort(arrayToSort);
        assertArrayEquals(expected, arrayToSort);
    }

    @Test
    public void testEquals() {
        Location intercomOffice = new Location("53.3393", "-6.2576841");
        Customer customer = new Customer(1, "Customer 1", intercomOffice);
        Customer anotherCustomer = new Customer(1, "Customer 1", intercomOffice);
        assertTrue(customer.equals(customer));
        assertFalse(customer.equals(null));
        assertFalse(customer.equals(""));
        assertTrue(customer.equals(anotherCustomer));
    }

    @Test
    public void testHashcode() {
        Location intercomOffice = new Location("53.3393", "-6.2576841");
        Customer customer = new Customer(1, "Customer 1", intercomOffice);
        Customer anotherCustomer = new Customer(1, "Customer 1", intercomOffice);
        assertEquals(customer.hashCode(), anotherCustomer.hashCode());
    }
}
