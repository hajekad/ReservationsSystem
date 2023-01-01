package cz.cvut.fit.hajekad3.reservantor.ApplicationLayer.Implementations;

import cz.cvut.fit.hajekad3.reservantor.DomainLayer.Place;
import cz.cvut.fit.hajekad3.reservantor.InfrastructureLayer.Storage.Abstractions.IPlaceRepository;
import cz.cvut.fit.hajekad3.reservantor.InterfaceLayer.Dtos.Place.CreatePlaceDto;
import cz.cvut.fit.hajekad3.reservantor.InterfaceLayer.Dtos.Place.PlaceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class PlaceService {
    @Autowired
    private IPlaceRepository placeRepository;

    public PlaceService(IPlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    public PlaceDto savePlace(CreatePlaceDto placeDto){
        System.out.print("Service received a post request for place: ");
        System.out.println(placeDto.getLatitude() + ' ' + placeDto.getLongitude());

        Place newPlace = new Place(placeDto);

        Place ret = placeRepository.save(newPlace);

        return ret.convertToDto();
    }

    public PlaceDto getPlace(Long id) {
        System.out.print("Service received a get request for place_id: ");
        System.out.println(id);

        Place ret = placeRepository.findById(id).orElse(null);

        if(ret == null)
            throw new NoSuchElementException("No such id.");

        return ret.convertToDto();
    }
}
