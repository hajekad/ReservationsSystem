package cz.cvut.fit.hajekad3.reservantor.ApplicationLayer.Implementations;

import cz.cvut.fit.hajekad3.reservantor.DomainLayer.Trainee;
import cz.cvut.fit.hajekad3.reservantor.InfrastructureLayer.Storage.Abstractions.ITraineeRepository;
import cz.cvut.fit.hajekad3.reservantor.InfrastructureLayer.Storage.Implementations.TraineeRepository;
import cz.cvut.fit.hajekad3.reservantor.InterfaceLayer.Dtos.Trainee.CreateTraineeDto;
import cz.cvut.fit.hajekad3.reservantor.InterfaceLayer.Dtos.Trainee.TraineeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@ComponentScan(basePackages = "cz.cvut.fit.hajekad3.reservantor.InfrastructureLayer.Storage.Implementations")
public class TraineeService {
    @Autowired
    private ITraineeRepository traineeRepository;

    public TraineeService(ITraineeRepository traineeRepository) {
        this.traineeRepository = traineeRepository;
    }

    public TraineeDto saveTrainee(CreateTraineeDto traineeDto){
        Trainee newTrainee = new Trainee((traineeDto));

        Trainee ret = traineeRepository.saveTrainee(newTrainee);

        return ret.convertToDto();
    }

    public TraineeDto getTrainee(Long id) {
        Trainee ret;

        try {
            ret = traineeRepository.getTrainee(id);
        }
        catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
            ret =  new Trainee();
            ret.fill();
        }

        return ret.convertToDto();
    }
}
