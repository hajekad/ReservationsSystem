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

    public TrainingDto saveTraining(CreateTrainingDto trainingDto){
        System.out.print("Service received a post request for training: ");
        System.out.println(trainingDto.getDateOfTraining());

        Training newTraining = new Training(trainingDto);

        Training ret = trainingRepository.save(newTraining);

        return ret.convertToDto();
    }

    public TrainingDto getTraining(Long id) {
        System.out.print("Service received a get request for training_id: ");
        System.out.println(id);

        Training ret = trainingRepository.findById(id).orElse(null);

        if(ret == null)
            throw new NoSuchElementException("No such id.");

        return ret.convertToDto();
    }
}
