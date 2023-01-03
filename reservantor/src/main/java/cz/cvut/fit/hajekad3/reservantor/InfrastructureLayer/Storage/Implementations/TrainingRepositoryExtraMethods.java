package cz.cvut.fit.hajekad3.reservantor.InfrastructureLayer.Storage.Implementations;

import cz.cvut.fit.hajekad3.reservantor.DomainLayer.Training;
import cz.cvut.fit.hajekad3.reservantor.InfrastructureLayer.Storage.Abstractions.ITrainingRepositoryExtraMethods;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class TrainingRepositoryExtraMethods implements ITrainingRepositoryExtraMethods {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Training> trainingsInTimeframe(Timestamp from, Timestamp to) {
        System.out.println(from.toString() + " " + to.toString() );

        String queryString = "SELECT DISTINCT t FROM Training t WHERE t.dateOfTraining >= :from AND t.dateOfTraining <= :to";
        TypedQuery<Training> query = entityManager.createQuery(queryString, Training.class);
        query.setParameter("from", from);
        query.setParameter("to", to);

        System.out.println(query.toString());

        return query.getResultList();
    }
}
