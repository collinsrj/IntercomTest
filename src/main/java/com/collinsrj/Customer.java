package com.collinsrj;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Objects;

/**
 * An Intercom customer
 */
@JsonDeserialize(using = CustomerDeserializer.class)
public class Customer implements Comparable<Customer> {
    private final long userId;
    private final String name;
    /**
     * The location of the user. May be null.
     */
    private final Location location;

    /**
     * Construct a customer
     *
     * @param userId   the ID of the customer
     * @param name     the name of the customer
     * @param location the {@link Location} of the customer
     */
    public Customer(long userId, String name, Location location) {
        this.userId = userId;
        this.name = name;
        this.location = location;
    }

    /**
     * Get the location of the customer. This may be null if no location is known.
     *
     * @return the location or null, if no location is known.
     */
    Location getLocation() {
        return location;
    }

    /**
     * Get the user ID associated with the user location.
     *
     * @return the userId
     */
    long getUserId() {
        return userId;
    }

    /**
     * Get the name of the customer associated with the user location.
     *
     * @return the name
     */
    String getName() {
        return name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(Customer otherCustomer) {
        return Long.compare(userId, otherCustomer.userId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer that = (Customer) o;
        return userId == that.userId &&
                Objects.equals(name, that.name) &&
                Objects.equals(location, that.location);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(userId, name, location);
    }
}
