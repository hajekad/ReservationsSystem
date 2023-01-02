package cz.cvut.fit.hajekad3.reservantor.ApplicationLayer.Implementations;

import cz.cvut.fit.hajekad3.reservantor.DomainLayer.Coach;
import cz.cvut.fit.hajekad3.reservantor.DomainLayer.Place;
import cz.cvut.fit.hajekad3.reservantor.DomainLayer.Training;
import cz.cvut.fit.hajekad3.reservantor.InfrastructureLayer.Storage.Abstractions.ICoachRepository;
import cz.cvut.fit.hajekad3.reservantor.InfrastructureLayer.Storage.Abstractions.IPlaceRepository;
import cz.cvut.fit.hajekad3.reservantor.InfrastructureLayer.Storage.Abstractions.ITrainingRepository;
import cz.cvut.fit.hajekad3.reservantor.InterfaceLayer.Dtos.Training.CreateTrainingDto;
import cz.cvut.fit.hajekad3.reservantor.InterfaceLayer.Dtos.Training.TrainingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class TrainingService {
    @Autowired
    private ITrainingRepository trainingRepository;

    @Autowired
    private ICoachRepository coachRepository;

    @Autowired
    private IPlaceRepository placeRepository;

    public TrainingService(ITrainingRepository trainingRepository, ICoachRepository coachRepository, IPlaceRepository placeRepository) {
        this.trainingRepository = trainingRepository;
        this.coachRepository = coachRepository;
        this.placeRepository = placeRepository;
    }

    public TrainingDto saveTraining(CreateTrainingDto trainingDto) {
        Training newTraining = new Training(trainingDto);

        Coach coach = coachRepository.findById(trainingDto.getIdCoach()).orElse(null);
        newTraining.setCoach(coach);

        Place place = placeRepository.findById(trainingDto.getIdPlace()).orElse(null);
        newTraining.setPlace(place);

        if(place == null || coach == null)
            throw new NoSuchElementException("Error: No such place or coach");

        Training ret = trainingRepository.save(newTraining);

        return ret.convertToDto();
    }

    public TrainingDto getTraining(Long id) {
        Training ret = trainingRepository.findById(id).orElse(null);

        if(ret == null)
            throw new NoSuchElementException("Error: Training does not exist. id: " + id);

        return ret.convertToDto();
    }

    public TrainingDto updateTraining(TrainingDto trainingDto) {
        if(!trainingRepository.existsById(trainingDto.getId()))
            throw new NoSuchElementException("Error: Training does not exist. id: " + trainingDto.getId());

        Training currTraining = new Training(trainingDto);

        Coach coach = coachRepository.findById(trainingDto.getIdCoach()).orElse(null);
        currTraining.setCoach(coach);

        Place place = placeRepository.findById(trainingDto.getIdPlace()).orElse(null);
        currTraining.setPlace(place);

        if(place == null || coach == null)
            throw new NoSuchElementException("Error: No such place or coach");

        return trainingRepository.save(currTraining).convertToDto();
    }

    public void deleteTraining(Long id) {
        if(!trainingRepository.existsById(id))
            throw new NoSuchElementException("Error: Training does not exist. id: " + id);

        trainingRepository.deleteById(id);
    }
}
