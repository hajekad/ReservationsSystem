package cz.cvut.fit.hajekad3.reservantor.ApplicationLayer.Implementations;

import cz.cvut.fit.hajekad3.reservantor.DomainLayer.Training;
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

    public TrainingService(ITrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    public TrainingDto saveTraining(CreateTrainingDto trainingDto) {
        Training newTraining = new Training(trainingDto);

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

        return trainingRepository.save(currTraining).convertToDto();
    }

    public void deleteTraining(Long id) {
        if(!trainingRepository.existsById(id))
            throw new NoSuchElementException("Error: Training does not exist. id: " + id);

        trainingRepository.deleteById(id);
    }
}
