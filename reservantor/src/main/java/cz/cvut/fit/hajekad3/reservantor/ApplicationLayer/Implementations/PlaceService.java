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

    public PlaceDto savePlace(CreatePlaceDto placeDto) {
        Place newPlace = new Place(placeDto);

        Place ret = placeRepository.save(newPlace);

        return ret.convertToDto();
    }

    public PlaceDto getPlace(Long id) {
        Place ret = placeRepository.findById(id).orElse(null);

        if(ret == null)
            throw new NoSuchElementException("Error: Place does not exist. id: " + id);

        return ret.convertToDto();
    }

    public PlaceDto updatePlace(PlaceDto placeDto) {
        if(!placeRepository.existsById(placeDto.getId()))
            throw new NoSuchElementException("Error: Place does not exist. id: " + placeDto.getId());

        Place currPlace = new Place(placeDto);

        return placeRepository.save(currPlace).convertToDto();
    }

    public void deletePlace(Long id) {
        if(!placeRepository.existsById(id))
            throw new NoSuchElementException("Error: Place does not exist. id: " + id);

        placeRepository.deleteById(id);
    }
}
