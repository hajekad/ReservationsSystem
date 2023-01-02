package cz.cvut.fit.hajekad3.reservantor.InfrastructureLayer.Storage.Abstractions;

import cz.cvut.fit.hajekad3.reservantor.DomainLayer.Training;

import java.sql.Timestamp;
import java.util.List;

public interface ITrainingRepositoryExtraMethods {
    List<Training> trainingsInTimeframe(Timestamp from, Timestamp to);
}
