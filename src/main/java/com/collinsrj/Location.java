package com.collinsrj;

import java.util.Objects;

/**
 * A location on the earth represented by latitude and longitude
 */
public class Location {
    /**
     * The radius of the earth in meters
     */
    private static final double RADIUS_EARTH = 6378137;

    /**
     * The latitude in radians
     */
    private final double latitude;
    /**
     * The {@code longitude} in radians
     */
    private final double longitude;

    public Location(final String latitude, final String longitude) {
        this.latitude = Math.toRadians(Double.parseDouble(latitude));
        this.longitude = Math.toRadians(Double.parseDouble(longitude));
    }

    /**
     * Get the distance to the {@code otherLocation} in meters
     *
     * @param otherLocation the location to measure the distance to
     * @return the distance in meters to {@code otherLocation}
     */
    public double distanceTo(final Location otherLocation) {
        double centralAngle = Math.acos(
                Math.sin(latitude) * Math.sin(otherLocation.latitude) +
                        Math.cos(latitude) * Math.cos(otherLocation.latitude) * Math.cos(longitude - otherLocation.longitude));
        return RADIUS_EARTH * centralAngle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Double.compare(location.latitude, latitude) == 0 &&
                Double.compare(location.longitude, longitude) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(latitude, longitude);
    }
}
