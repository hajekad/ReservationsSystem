package cz.cvut.fit.hajekad3.reservantor.ApplicationLayer.Implementations;

import cz.cvut.fit.hajekad3.reservantor.DomainLayer.Place;
import cz.cvut.fit.hajekad3.reservantor.DomainLayer.Training;
import cz.cvut.fit.hajekad3.reservantor.InfrastructureLayer.Storage.Abstractions.IPlaceRepository;
import cz.cvut.fit.hajekad3.reservantor.InfrastructureLayer.Storage.Abstractions.ITrainingRepositoryJpa;
import cz.cvut.fit.hajekad3.reservantor.InterfaceLayer.Dtos.Place.CreatePlaceDto;
import cz.cvut.fit.hajekad3.reservantor.InterfaceLayer.Dtos.Place.PlaceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.NoSuchElementException;

@Service
public class PlaceService {
    @Autowired
    private IPlaceRepository placeRepository;

    @Autowired
    private ITrainingRepositoryJpa trainingRepository;

    public PlaceService(IPlaceRepository placeRepository, ITrainingRepositoryJpa trainingRepository) {
        this.placeRepository = placeRepository;
        this.trainingRepository = trainingRepository;
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

        ArrayList<Training> tmp = new ArrayList<Training>();
        Training training = null;
        for (Long i: placeDto.getTrainings()) {
            training = trainingRepository.findById(i).orElse(null);

            if(training == null)
                throw new NoSuchElementException("Error: No such Trainee");

            tmp.add(training);
        }
        currPlace.setTrainings(tmp);

        return placeRepository.save(currPlace).convertToDto();
    }

    public void deletePlace(Long id) {
        if(!placeRepository.existsById(id))
            throw new NoSuchElementException("Error: Place does not exist. id: " + id);

        placeRepository.deleteById(id);
    }
}
