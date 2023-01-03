package cz.cvut.fit.hajekad3.reservantor.InfrastructureLayer.Storage.Abstractions;

import cz.cvut.fit.hajekad3.reservantor.DomainLayer.Coach;
import cz.cvut.fit.hajekad3.reservantor.DomainLayer.Trainee;

public interface ICoachRepositoryExtraMethods {
    boolean coachTrainedTrainee(Trainee trainee, Coach coach);
}
