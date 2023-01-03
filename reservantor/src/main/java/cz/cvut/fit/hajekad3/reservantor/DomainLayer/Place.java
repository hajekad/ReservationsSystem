package cz.cvut.fit.hajekad3.reservantor.DomainLayer;

import cz.cvut.fit.hajekad3.reservantor.InterfaceLayer.Dtos.Place.CreatePlaceDto;
import cz.cvut.fit.hajekad3.reservantor.InterfaceLayer.Dtos.Place.PlaceDto;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;

@Entity (name = "Place")
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_place", nullable = false)
    private Long id;

    @Column(name = "latitude", nullable = false)
    private Double latitude;

    @Column(name = "longitude", nullable = false)
    private Double longitude;

    @OneToMany
    @Column(name = "trainings", nullable = true)
    private Collection<Training> trainings;

    public Place() {}

    public Place(CreatePlaceDto placeDto) {
        trainings = new ArrayList<Training>();
        setLatitude(placeDto.getLatitude());
        setLongitude(placeDto.getLongitude());
    }

    public Place(PlaceDto placeDto) {
        trainings = new ArrayList<Training>();
        setId(placeDto.getId());
        setLatitude(placeDto.getLatitude());
        setLongitude(placeDto.getLongitude());
    }

    public PlaceDto convertToDto() {
        PlaceDto ret = new PlaceDto();

        ArrayList<Long> tmp = new ArrayList<Long>();
        for (Training i: trainings) {
            tmp.add(i.getId());
        }
        ret.setTrainings(tmp);

        ret.setId(getId());
        ret.setLatitude(getLatitude());
        ret.setLongitude(getLongitude());

        return ret;
    }

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

    public Collection<Training> getTrainings() {
        return trainings;
    }

    public void setTrainings(Collection<Training> trainings) {
        this.trainings = trainings;
    }
}
