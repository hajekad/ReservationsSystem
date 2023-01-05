package cz.cvut.fit.hajekad3.reservantor.Integration;

import cz.cvut.fit.hajekad3.reservantor.ApplicationLayer.Implementations.PlaceService;
import cz.cvut.fit.hajekad3.reservantor.DomainLayer.Place;
import cz.cvut.fit.hajekad3.reservantor.DomainLayer.Training;
import cz.cvut.fit.hajekad3.reservantor.Helper.CoachHelper;
import cz.cvut.fit.hajekad3.reservantor.Helper.PlaceHelper;
import cz.cvut.fit.hajekad3.reservantor.Helper.TrainingHelper;
import cz.cvut.fit.hajekad3.reservantor.InfrastructureLayer.Storage.Abstractions.IPlaceRepository;
import cz.cvut.fit.hajekad3.reservantor.InfrastructureLayer.Storage.Abstractions.ITrainingRepositoryJpa;
import cz.cvut.fit.hajekad3.reservantor.InterfaceLayer.Dtos.Place.CreatePlaceDto;
import cz.cvut.fit.hajekad3.reservantor.InterfaceLayer.Dtos.Place.PlaceDto;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlaceIntegrationTest {
    @Autowired
    private PlaceService placeService;
    @Autowired
    private IPlaceRepository placeRepository;
    @Autowired
    private ITrainingRepositoryJpa trainingRepository;

    private CreatePlaceDto testCreatePlaceDto;
    private PlaceDto testPlaceDto;
    private Place testPlace;
    private Training testTraining;
    private TrainingHelper trainingHelper;
    private PlaceHelper placeHelper;

    public PlaceIntegrationTest() {
        this.trainingHelper = new TrainingHelper();
        this.placeHelper = new PlaceHelper();
    }

    @Before
    public void setUp() {
        testTraining = trainingHelper.fillTraining();
        testPlaceDto = placeHelper.fillPlaceDto();
        testPlace = placeHelper.fillPlace();

        trainingRepository.save(testTraining);
    }

    @Test
    public void testUpdatePlace() {
        PlaceDto savedPlaceDto = placeHelper.fillPlaceDto();
        PlaceDto updatedPlaceDto = placeService.updatePlace(savedPlaceDto);
        Place updatedPlace = placeRepository.findById(updatedPlaceDto.getId()).orElse(null);

        assertNotNull(updatedPlace);
        assert(savedPlaceDto.getId() == updatedPlace.getId());
    }

    @Test
    public void testDeletePlace() {
        PlaceDto savedPlaceDto = placeService.savePlace(placeHelper.fillCreatePlaceDto());
        placeService.deletePlace(savedPlaceDto.getId());
        Place deletedPlace = placeRepository.findById(savedPlaceDto.getId()).orElse(null);

        assert(deletedPlace == null);
    }

}
