package cz.cvut.fit.hajekad3.reservantor.InterfaceLayer.Dtos.Place;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Collection;

public class PlaceDto {
    private Long id;

    private Double latitude;

    private Double longitude;

    private Collection<Long> trainings;

    public PlaceDto(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Collection<Long> getTrainings() {
        return trainings;
    }

    public void setTrainings(Collection<Long> trainings) {
        this.trainings = trainings;
    }
}
