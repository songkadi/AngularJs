package com.songkadi.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CLAIM")
public class Claim {

    @Id
    @NotEmpty
    @Column(name = "CLAIM_NUMBER", nullable = false)
    private String claimNumber;

    @NotEmpty
    @Column(name = "CAR_VEHICLE", nullable = false)
    private String carVehicle;

    @Column(name = "LATITUDE")
    private Double latitude;

    @Column(name = "LONGITUDE")
    private Double longitude;

    public String getClaimNumber() {
        return claimNumber;
    }

    public void setClaimNumber(String claimNumber) {
        this.claimNumber = claimNumber;
    }

    public String getCarVehicle() {
        return carVehicle;
    }

    public void setCarVehicle(String carVehicle) {
        this.carVehicle = carVehicle;
    }

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
