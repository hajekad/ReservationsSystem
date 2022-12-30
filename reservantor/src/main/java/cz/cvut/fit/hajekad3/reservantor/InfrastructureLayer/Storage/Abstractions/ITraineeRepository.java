package cz.cvut.fit.hajekad3.reservantor.InfrastructureLayer.Storage.Abstractions;

import cz.cvut.fit.hajekad3.reservantor.DomainLayer.Trainee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface ITraineeRepository extends JpaRepository<Trainee, Long>, ITraineeRepositoryExtraMethods {
}
