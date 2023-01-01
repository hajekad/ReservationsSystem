package cz.cvut.fit.hajekad3.reservantor.InterfaceLayer.Dtos.Place;

public class CreatePlaceDto {
    private Double latitude;

    private Double longitude;

    public CreatePlaceDto() {}

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
