package cz.cvut.fit.hajekad3.reservantor.InfrastructureLayer.Storage.Abstractions;

import cz.cvut.fit.hajekad3.reservantor.DomainLayer.Trainee;

public interface ITraineeRepositoryExtraMethods {
    <S extends Trainee> S save(S entity);

    String getTrainee();
}
