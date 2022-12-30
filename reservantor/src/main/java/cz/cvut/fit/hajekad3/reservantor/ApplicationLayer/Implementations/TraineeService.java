package cz.cvut.fit.hajekad3.reservantor.ApplicationLayer.Implementations;

import cz.cvut.fit.hajekad3.reservantor.InfrastructureLayer.Storage.Abstractions.ITraineeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TraineeService {
    @Autowired
    private final ITraineeRepository traineeRepository;

    public TraineeService(ITraineeRepository traineeRepository) {
        this.traineeRepository = traineeRepository;
    }

    public String getTrainee() {
        return traineeRepository.getTrainee();
    }
}
