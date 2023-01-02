package cz.cvut.fit.hajekad3.reservantor.DomainLayer;

import cz.cvut.fit.hajekad3.reservantor.InterfaceLayer.Dtos.Place.CreatePlaceDto;
import cz.cvut.fit.hajekad3.reservantor.InterfaceLayer.Dtos.Place.PlaceDto;
import jakarta.persistence.*;

@Entity
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_place", nullable = false)
    private Long id;

    @Column(name = "latitude", nullable = false)
    private Double latitude;

    @Column(name = "longitude", nullable = false)
    private Double longitude;

    public Place() {}

    public Place(CreatePlaceDto placeDto) {
        setLatitude(placeDto.getLatitude());
        setLongitude(placeDto.getLongitude());
    }

    public Place(PlaceDto placeDto) {
        setId(placeDto.getId());
        setLatitude(placeDto.getLatitude());
        setLongitude(placeDto.getLongitude());
    }

    public PlaceDto convertToDto() {
        PlaceDto ret = new PlaceDto();

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
}
