 package cz.cvut.fit.hajekad3.reservantor.InfrastructureLayer.Storage.Implementations;

import cz.cvut.fit.hajekad3.reservantor.DomainLayer.Trainee;
import cz.cvut.fit.hajekad3.reservantor.InfrastructureLayer.Storage.Abstractions.ITraineeRepositoryExtraMethods;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TraineeRepositoryExtraMethods implements ITraineeRepositoryExtraMethods {
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * finds a viable opponent in selected skill range
     * @param range
     * @param trainee
     * @return
     */
    @Override
    public List<Trainee> findMatch(int range, Trainee trainee) {
        String queryString = "SELECT DISTINCT t FROM Trainee t WHERE t.skillCap >= :traineeSkill - :range AND t.skillCap <= :traineeSkill + :range AND t.id != :traineeId";
        TypedQuery<Trainee> query = entityManager.createQuery(queryString, Trainee.class);
        query.setParameter("traineeId", trainee.getId());
        query.setParameter("traineeSkill", trainee.getSkillCap());
        query.setParameter("range", range);

        return query.getResultList();
    }
}
