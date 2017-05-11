package com.collinsrj;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

/**
 * Tests the basic app functionality. Unit testing doesn't readily allow for testing none zero exit codes. So coverage
 * of App is limited.
 */
public class AppTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testMain() throws Exception {
        File customersFile = new File(getClass().getResource("singlenearbycustomer.txt").getFile());
        String[] args = new String[]{customersFile.getAbsolutePath()};

        App.main(args);

        assertEquals("Robert Collins\t1" + System.lineSeparator(), outContent.toString());
    }
}
