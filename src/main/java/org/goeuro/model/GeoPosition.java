package org.goeuro.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Model Class which will keep the Geo Information from JSON responce
 *
 *
 * Created by arthurh on 1/27/15.
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeoPosition {

    private Double  latitude;
    private Double  longitude;

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
