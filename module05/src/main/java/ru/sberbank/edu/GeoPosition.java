package ru.sberbank.edu;

/**
 * Geo position.
 */
public class GeoPosition {
    /**
     * Широта в радианах.
     */
    private double latitude;

    /**
     * Долгота в радианах.
     */
    private double longitude;

    /**
     * Ctor.
     *
     * @param latitudeGradus  - latitude in gradus
     * @param longitudeGradus - longitude in gradus
     *                        Possible values: 55, 55(45'07''), 59(57'00'')
     */
    public GeoPosition(String latitudeGradus, String longitudeGradus) {
        this.latitude = degreesToRadians(parseDegrees(latitudeGradus));
        this.longitude = degreesToRadians(parseDegrees(longitudeGradus));
    }

    private double degreesToRadians(double degrees) {
        return degrees * Math.PI / 180;
    }

    private double parseDegrees(String degreesString) {

            String[] parts = degreesString.split("[()']");
            double degrees = Double.parseDouble(parts[0].trim());
            double minutes = (parts.length > 1) ? Double.parseDouble(parts[1].trim()) : 0.0;
            double seconds = (parts.length > 2) ? Double.parseDouble(parts[2].trim()) / 3600 : 0.0;
            return degrees + (minutes / 60) + seconds;
        }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return "GeoPosition{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
