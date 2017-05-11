package com.collinsrj;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

/**
 * A <a href="https://github.com/FasterXML/jackson">Jackson</a> deserializer for handling JSON representations of
 * {@link Customer}
 * An example JSON would look like:
 * {"latitude": "52.833502", "user_id": 25, "name": "David Behan", "longitude": "-8.522366"}
 */
class CustomerDeserializer extends StdDeserializer<Customer> {

    public CustomerDeserializer(Class<?> vc) {
        super(vc);
    }

    public CustomerDeserializer() {
        this(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Customer deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        long userId = node.get("user_id").longValue();
        String name = node.get("name").asText();

        JsonNode latitudeNode = node.get("latitude");
        String latitude = latitudeNode == null ? null : latitudeNode.asText();
        JsonNode longitudeNode = node.get("longitude");
        String longitude = latitudeNode == null ? null : longitudeNode.asText();
        Location location = (latitude == null || longitude == null) ? null : new Location(latitude, longitude);

        return new Customer(userId, name, location);
    }
}
