package cz.cvut.fit.hajekad3.reservantor.InfrastructureLayer.Storage.Abstractions;

import cz.cvut.fit.hajekad3.reservantor.DomainLayer.Trainee;
import org.springframework.stereotype.Repository;

@Repository
public interface ITraineeRepository {
    Trainee saveTrainee(Trainee trainee);

    Trainee getTrainee(Long id);
}
