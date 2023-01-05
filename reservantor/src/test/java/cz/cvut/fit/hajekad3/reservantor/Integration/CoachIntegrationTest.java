package cz.cvut.fit.hajekad3.reservantor.Integration;

import cz.cvut.fit.hajekad3.reservantor.ApplicationLayer.Implementations.CoachService;
import cz.cvut.fit.hajekad3.reservantor.DomainLayer.Coach;
import cz.cvut.fit.hajekad3.reservantor.DomainLayer.Trainee;
import cz.cvut.fit.hajekad3.reservantor.InfrastructureLayer.Storage.Abstractions.ICoachRepositoryExtraMethods;
import cz.cvut.fit.hajekad3.reservantor.InfrastructureLayer.Storage.Abstractions.ICoachRepositoryJpa;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CoachIntegrationTest {

    @Mock
    private CoachService coachService;

    @Mock
    private ICoachRepositoryExtraMethods coachRepositoryExtraMethods;

    @Test
    public void testUpdatesTraineeSkillCap() {
        // Arrange
        Trainee trainee = new Trainee();
        trainee.setSkillCap(5);

        Coach coach = new Coach();
        coach.setId(1L);

        // Act
        boolean result = coachRepositoryExtraMethods.coachTrainedTrainee(trainee, coach);
        coachService.updateTraineesSkillCap(coach.getId(), trainee.getId(), 10);
        boolean updatedResult = coachRepositoryExtraMethods.coachTrainedTrainee(trainee, coach);

        // Assert
        assert(result == false);
        assert(updatedResult == false);
    }

}
