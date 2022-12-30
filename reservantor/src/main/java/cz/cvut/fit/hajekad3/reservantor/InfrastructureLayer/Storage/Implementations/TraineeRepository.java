package cz.cvut.fit.hajekad3.reservantor.InfrastructureLayer.Storage.Implementations;

import cz.cvut.fit.hajekad3.reservantor.DomainLayer.Trainee;
import cz.cvut.fit.hajekad3.reservantor.InfrastructureLayer.Storage.Abstractions.ITraineeRepositoryExtraMethods;
import org.springframework.stereotype.Repository;

@Repository
public class TraineeRepository implements ITraineeRepositoryExtraMethods {
    public TraineeRepository() {}
    @Override
    public <S extends Trainee> S save(S entity) {
        return null;
    }

    @Override
    public String getTrainee()
    {
        return "Hello World!";
    }
}
