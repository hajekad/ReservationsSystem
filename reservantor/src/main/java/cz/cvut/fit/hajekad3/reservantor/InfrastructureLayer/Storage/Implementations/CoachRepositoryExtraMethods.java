package cz.cvut.fit.hajekad3.reservantor.InfrastructureLayer.Storage.Implementations;

import cz.cvut.fit.hajekad3.reservantor.DomainLayer.Coach;
import cz.cvut.fit.hajekad3.reservantor.DomainLayer.Trainee;
import cz.cvut.fit.hajekad3.reservantor.DomainLayer.Training;
import cz.cvut.fit.hajekad3.reservantor.InfrastructureLayer.Storage.Abstractions.ICoachRepositoryExtraMethods;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

@Repository
public class CoachRepositoryExtraMethods implements ICoachRepositoryExtraMethods {
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * decides whether coach has trained trainee
     * @param trainee
     * @param coach
     * @return
     */
    @Override
    public boolean coachTrainedTrainee(Trainee trainee, Coach coach) {
        String jpql = "SELECT t FROM Training t WHERE :coach = t.coach.id AND :trainee MEMBER OF t.participatingTrainees";

        TypedQuery<Training> query = entityManager.createQuery(jpql, Training.class);

        query.setParameter("coach", coach.getId());
        query.setParameter("trainee", trainee);

        return !query.getResultList().isEmpty();
    }
}
