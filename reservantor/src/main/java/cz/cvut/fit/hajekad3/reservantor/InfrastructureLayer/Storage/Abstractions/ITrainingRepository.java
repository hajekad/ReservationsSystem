package cz.cvut.fit.hajekad3.reservantor.InfrastructureLayer.Storage.Abstractions;

import cz.cvut.fit.hajekad3.reservantor.DomainLayer.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITrainingRepository extends ITrainingRepositoryExtraMethods, JpaRepository<Training, Long> {
}
