package cz.cvut.fit.hajekad3.reservantor.Unit;

import cz.cvut.fit.hajekad3.reservantor.DomainLayer.Trainee;
import cz.cvut.fit.hajekad3.reservantor.InfrastructureLayer.Storage.Implementations.TraineeRepositoryExtraMethods;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TraineeUnitTest {
    @Mock
    private EntityManager entityManager;

    @InjectMocks
    private TraineeRepositoryExtraMethods traineeRepositoryExtraMethods;

    @Test
    public void testFindMatch() {
        // Setup
        Trainee trainee = new Trainee();
        trainee.setId(1L);
        trainee.setSkillCap(10);
        TypedQuery<Trainee> query = Mockito.mock(TypedQuery.class);
        Mockito.when(query.getResultList()).thenReturn(Arrays.asList(new Trainee()));
        String queryString = "SELECT DISTINCT t FROM Trainee t WHERE t.skillCap >= :traineeSkill - :range AND t.skillCap <= :traineeSkill + :range AND t.id != :traineeId";
        Mockito.when(entityManager.createQuery(queryString, Trainee.class)).thenReturn(query);

        // Execute
        List<Trainee> result = traineeRepositoryExtraMethods.findMatch(100, trainee);

        // Assert
        assert(result.size() == 1);
    }

    @Test
    public void testFindMatchNoMatches() {
        // Setup
        Trainee trainee = new Trainee();
        trainee.setId(1L);
        trainee.setSkillCap(10);
        TypedQuery<Trainee> query = Mockito.mock(TypedQuery.class);
        Mockito.when(query.getResultList()).thenReturn(Collections.emptyList());
        String queryString = "SELECT DISTINCT t FROM Trainee t WHERE t.skillCap >= :traineeSkill - :range AND t.skillCap <= :traineeSkill + :range AND t.id != :traineeId";
        Mockito.when(entityManager.createQuery(queryString, Trainee.class)).thenReturn(query);

        // Execute
        List<Trainee> result = traineeRepositoryExtraMethods.findMatch(5, trainee);

        // Assert
        assert(result.size() == 0);
    }

}
