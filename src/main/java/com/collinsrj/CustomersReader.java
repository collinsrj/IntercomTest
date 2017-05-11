package com.collinsrj;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 * A class to support the reading of customer JSON files. Unreadable customer records will be ignored.
 */
public class CustomersReader {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final Logger LOGGER = Logger.getLogger(CustomersReader.class.getName());
    private static final Function<String, Customer> DESERIALIZE = json -> {
        try {
            return MAPPER.readValue(json, Customer.class);
        } catch (JsonParseException | JsonMappingException e) {
            LOGGER.log(Level.WARNING, "Unable to deserialize customer JSON: " + json, e);
            return null;
        } catch (IOException e) {
            // this is a low level error that shouldn't happen reading input
            throw new IllegalStateException("Problem reading JSON: " + json, e);
        }
    };
    private final File customersFile;

    /**
     * @param customersFile a UTF-8 encoded txt file with JSON entries on each line
     */
    public CustomersReader(File customersFile) {
        if (!customersFile.canRead()) {
            throw new IllegalArgumentException("Can't read the user location file specified: " + customersFile);
        }
        this.customersFile = customersFile;
    }

    /**
     * Handle the nearby users using the function provided
     *
     * @param location the location from which to get the distance
     * @param distance the distance to filter customers
     * @param consumer a {@link Consumer} function to handle the sorted nearby customers
     * @throws IOException an {@link IOException} if there was a problem reading the file
     */
    public void handleNearbyUsers(Location location, int distance, Consumer<Customer> consumer) throws IOException {
        assert customersFile.canRead();
        Path path = Paths.get(customersFile.toURI());
        try (Stream<String> stream = Files.lines(path)) {
            stream
                    .map(DESERIALIZE::apply)
                    .filter(Objects::nonNull)
                    .filter(customer -> customer.getLocation() != null)
                    .filter(customer -> customer.getLocation().distanceTo(location) < distance)
                    .sorted()
                    .forEach(consumer);
        }
    }
}
