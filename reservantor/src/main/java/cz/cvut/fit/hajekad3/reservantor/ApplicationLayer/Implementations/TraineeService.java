package cz.cvut.fit.hajekad3.reservantor.ApplicationLayer.Implementations;

import cz.cvut.fit.hajekad3.reservantor.DomainLayer.Trainee;
import cz.cvut.fit.hajekad3.reservantor.InfrastructureLayer.Storage.Abstractions.ITraineeRepository;
import cz.cvut.fit.hajekad3.reservantor.InterfaceLayer.Dtos.Trainee.CreateTraineeDto;
import cz.cvut.fit.hajekad3.reservantor.InterfaceLayer.Dtos.Trainee.TraineeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class TraineeService {
    @Autowired
    private ITraineeRepository traineeRepository;

    public TraineeService(ITraineeRepository traineeRepository) {
        this.traineeRepository = traineeRepository;
    }

    public TraineeDto saveTrainee(CreateTraineeDto traineeDto){
        System.out.print("Service received a post request for user: ");
        System.out.println(traineeDto.getUsername());

        Trainee newTrainee = new Trainee(traineeDto);

        Trainee ret = traineeRepository.save(newTrainee);

        return ret.convertToDto();
    }

    public TraineeDto getTrainee(Long id) {
        System.out.print("Service received a get request for user_id: ");
        System.out.println(id);

        Trainee ret = traineeRepository.findById(id).orElse(null);

        if(ret == null)
            throw new NoSuchElementException("No such id.");

        return ret.convertToDto();
    }
}
