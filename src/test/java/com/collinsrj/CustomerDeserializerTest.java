package com.collinsrj;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by collinsrj on 09/05/2017.
 */
public class CustomerDeserializerTest {
    @Test
    public void testDeserialize() throws Exception {
        String json = "{\"latitude\": \"52.833502\", \"user_id\": 25, \"name\": \"David Behan\", \"longitude\": \"-8.522366\"}";
        Customer expected = new Customer(25, "David Behan", new Location("52.833502", "-8.522366"));
        Customer actualCustomer = new ObjectMapper().readValue(json, Customer.class);

        assertEquals(expected, actualCustomer);
    }

    @Test
    public void testDeserializeNoLocation() throws Exception {
        String json = "{\"user_id\": 25, \"name\": \"David Behan\"}";
        Customer expected = new Customer(25, "David Behan", null);
        Customer actualCustomer = new ObjectMapper().readValue(json, Customer.class);

        assertEquals(expected, actualCustomer);
    }
}
