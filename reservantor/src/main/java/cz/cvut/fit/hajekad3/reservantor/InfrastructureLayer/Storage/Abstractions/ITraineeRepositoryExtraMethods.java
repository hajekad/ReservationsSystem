package cz.cvut.fit.hajekad3.reservantor.InfrastructureLayer.Storage.Abstractions;

import cz.cvut.fit.hajekad3.reservantor.DomainLayer.Trainee;

import java.util.List;

public interface ITraineeRepositoryExtraMethods {
    List<Trainee> findMatch(int range, Trainee trainee);
}
