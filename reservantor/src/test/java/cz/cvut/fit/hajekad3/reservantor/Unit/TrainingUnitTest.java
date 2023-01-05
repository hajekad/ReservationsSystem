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
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrainingUnitTest {
    @Mock
    private EntityManager entityManager;
    @InjectMocks
    private TraineeRepositoryExtraMethods traineeRepositoryExtraMethods;

    @Test
    public void testFindMatch() {
        // Setup
        Trainee trainee = new Trainee();
        trainee.setId(1L);
        trainee.setSkillCap(5);

        Trainee matchingTrainee1 = new Trainee();
        matchingTrainee1.setId(2L);
        matchingTrainee1.setSkillCap(4);

        Trainee matchingTrainee2 = new Trainee();
        matchingTrainee2.setId(3L);
        matchingTrainee2.setSkillCap(6);

        Trainee nonMatchingTrainee = new Trainee();
        nonMatchingTrainee.setId(4L);
        nonMatchingTrainee.setSkillCap(1);

        List<Trainee> expectedResult = Arrays.asList(matchingTrainee1, matchingTrainee2);

        String queryString = "SELECT DISTINCT t FROM Trainee t WHERE t.skillCap >= :traineeSkill - :range AND t.skillCap <= :traineeSkill + :range AND t.id != :traineeId";
        TypedQuery<Trainee> query = Mockito.mock(TypedQuery.class);
        Mockito.when(query.getResultList()).thenReturn(expectedResult);
        Mockito.when(entityManager.createQuery(queryString, Trainee.class)).thenReturn(query);
        Mockito.when(query.setParameter("traineeId", trainee.getId())).thenReturn(query);
        Mockito.when(query.setParameter("traineeSkill", trainee.getSkillCap())).thenReturn(query);
        Mockito.when(query.setParameter("range", 5)).thenReturn(query);

        // Execute
        List<Trainee> result = traineeRepositoryExtraMethods.findMatch(5, trainee);

        // Assert
        assert(expectedResult == result);
    }
}
