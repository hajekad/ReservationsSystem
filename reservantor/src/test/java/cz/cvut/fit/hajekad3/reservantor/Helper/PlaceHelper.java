package cz.cvut.fit.hajekad3.reservantor.Helper;

import cz.cvut.fit.hajekad3.reservantor.DomainLayer.Place;
import cz.cvut.fit.hajekad3.reservantor.InterfaceLayer.Dtos.Place.CreatePlaceDto;
import cz.cvut.fit.hajekad3.reservantor.InterfaceLayer.Dtos.Place.PlaceDto;

import java.util.ArrayList;

public class PlaceHelper {
    public Place fillPlace() {
        Place ret = new Place(fillPlaceDto());

        return ret;
    }

    public PlaceDto fillPlaceDto() {
        PlaceDto ret = new PlaceDto();

        ret.setId(1L);
        ret.setLatitude(50D);
        ret.setLongitude(50D);
        ret.setTrainings(new ArrayList<>());

        return ret;
    }

    public CreatePlaceDto fillCreatePlaceDto() {
        CreatePlaceDto ret = new CreatePlaceDto();

        ret.setLatitude(50D);
        ret.setLongitude(50D);

        return ret;
    }

    public Place fillPlaceFromCreatePlaceDto() {
        Place ret = fillPlace();

        return ret;
    }
}
