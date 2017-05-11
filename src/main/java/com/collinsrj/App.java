package com.collinsrj;

import java.io.File;
import java.io.IOException;

/**
 * An application to output the names and user ids of customers within 100km, sorted by User ID (ascending).
 */
public class App {
    /**
     * The location of the <a href="https://www.intercom.com">Intercom</a> offices
     */
    private static final Location INTERCOM_OFFICES = new Location("53.3393", "-6.2576841");
    /**
     * The distance in meters to filter the user locations by
     */
    private static final int DISTANCE = 100000;

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.print("Usage: java com.collinsrj.App <customers_file>" + System.lineSeparator());
            System.exit(1);
        }
        File userLocationFile = new File(args[0]);
        if (!userLocationFile.canRead()) {
            System.err.print("Unable to read the file specified: " + userLocationFile.getAbsolutePath());
            System.exit(2);
        }
        CustomersReader reader = new CustomersReader(userLocationFile);
        try {
            reader.handleNearbyUsers(INTERCOM_OFFICES, DISTANCE,
                    userLocation ->
                            System.out.println(userLocation.getName() + "\t" + userLocation.getUserId()));
        } catch (IOException e) {
            System.err.println("Problem handling the file: " + userLocationFile.getAbsolutePath() + ". " + e.getLocalizedMessage());
            System.exit(3);
        }
    }
}
