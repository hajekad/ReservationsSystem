package cz.cvut.fit.hajekad3.reservantor.ApplicationLayer.Implementations;

import cz.cvut.fit.hajekad3.reservantor.DomainLayer.Trainee;
import cz.cvut.fit.hajekad3.reservantor.InfrastructureLayer.Storage.Abstractions.ITraineeRepository;
import cz.cvut.fit.hajekad3.reservantor.InterfaceLayer.Dtos.Trainee.CreateTraineeDto;
import cz.cvut.fit.hajekad3.reservantor.InterfaceLayer.Dtos.Trainee.TraineeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class TraineeService {
    @Autowired
    private ITraineeRepository traineeRepository;

    public TraineeService(ITraineeRepository traineeRepository) {
        this.traineeRepository = traineeRepository;
    }

    public TraineeDto saveTrainee(CreateTraineeDto traineeDto) {
        Trainee newTrainee = new Trainee(traineeDto);

        Trainee ret = traineeRepository.save(newTrainee);

        return ret.convertToDto();
    }

    public TraineeDto getTrainee(Long id) {
        Trainee ret = traineeRepository.findById(id).orElse(null);

        if(ret == null)
            throw new NoSuchElementException("Error: Trainee does not exist. id: " + id);

        return ret.convertToDto();
    }

    public TraineeDto updateTrainee(TraineeDto traineeDto) {
        if(!traineeRepository.existsById(traineeDto.getId()))
            throw new NoSuchElementException("Error: Trainee does not exist. id: " + traineeDto.getId());

        Trainee currTrainee = new Trainee(traineeDto);

        return traineeRepository.save(currTrainee).convertToDto();
    }

    public void deleteTrainee(Long id) {
        if(!traineeRepository.existsById(id))
            throw new NoSuchElementException("Error: Trainee does not exist. id: " + id);

        traineeRepository.deleteById(id);
    }
}
