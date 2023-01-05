package cz.cvut.fit.hajekad3.reservantor.Unit;

import cz.cvut.fit.hajekad3.reservantor.DomainLayer.Coach;
import cz.cvut.fit.hajekad3.reservantor.DomainLayer.Trainee;
import cz.cvut.fit.hajekad3.reservantor.DomainLayer.Training;
import cz.cvut.fit.hajekad3.reservantor.InfrastructureLayer.Storage.Implementations.CoachRepositoryExtraMethods;
import jakarta.persistence.EntityManager;
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

@RunWith(SpringRunner.class)
@SpringBootTest
public class CoachUnitTest {
    @Mock
    private EntityManager entityManager;

    @InjectMocks
    private CoachRepositoryExtraMethods coachRepositoryExtraMethods;

    @Test
    public void coachHasTrainedTrainee() {
        // Setup
        Trainee trainee = new Trainee();
        Coach coach = new Coach();
        TypedQuery<Training> query = Mockito.mock(TypedQuery.class);
        Mockito.when(query.getResultList()).thenReturn(Arrays.asList(new Training()));
        Mockito.when(entityManager.createQuery(Mockito.anyString(), Mockito.eq(Training.class)))
                .thenReturn(query);

        // Execute
        boolean result = coachRepositoryExtraMethods.coachTrainedTrainee(trainee, coach);

        // Assert
        assert(result == true);
    }

    @Test
    public void coachHasNotTrainedTrainee() {
        // Setup
        Trainee trainee = new Trainee();
        Coach coach = new Coach();
        TypedQuery<Training> query = Mockito.mock(TypedQuery.class);
        Mockito.when(query.getResultList()).thenReturn(Collections.emptyList());
        Mockito.when(entityManager.createQuery(Mockito.anyString(), Mockito.eq(Training.class)))
                .thenReturn(query);

        // Execute
        boolean result = coachRepositoryExtraMethods.coachTrainedTrainee(trainee, coach);

        // Assert
        assert(result == false);
    }
}
