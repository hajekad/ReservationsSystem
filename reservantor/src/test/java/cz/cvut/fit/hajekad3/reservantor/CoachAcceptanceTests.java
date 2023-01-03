package cz.cvut.fit.hajekad3.reservantor;

import cz.cvut.fit.hajekad3.reservantor.Api.Controllers.CoachController;
import cz.cvut.fit.hajekad3.reservantor.ApplicationLayer.Implementations.CoachService;
import cz.cvut.fit.hajekad3.reservantor.InterfaceLayer.Dtos.Coach.CoachDto;
import cz.cvut.fit.hajekad3.reservantor.InterfaceLayer.Dtos.Coach.CreateCoachDto;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import static org.mockito.Mockito.*;

@SpringBootTest
public class CoachAcceptanceTests {
    @Mock
    private CoachService coachService;

    @InjectMocks
    private CoachController coachController;

    @Test
    public void testUpdateTraineesSkillCap() {
        Long idCoach = 1L;
        Long idTrainee = 1L;
        int skillCap = 98;

        coachController.updateTraineesSkillCap(idCoach, idTrainee, skillCap);

        verify(coachService, times(1)).updateTraineesSkillCap(idCoach, idTrainee, skillCap);
    }

    @Test
    public CoachDto testPostCoach() {
        CreateCoachDto createCoachDto = new CreateCoachDto();
        createCoachDto.setCostRate(100);
        createCoachDto.setSport("Basketball");
        createCoachDto.setPassword("password");
        createCoachDto.setEmail("coach@gmail.com");
        createCoachDto.setFirstName("John");
        createCoachDto.setSecondName("Doe");

        CoachDto coachDto = new CoachDto();
        coachDto.setCostRate(100);
        coachDto.setSport("Basketball");
        coachDto.setPassword("password");
        coachDto.setEmail("coach@gmail.com");
        coachDto.setFirstName("John");
        coachDto.setSecondName("Doe");
        coachDto.setTrainings(new ArrayList<>());

        when(coachService.saveCoach(createCoachDto)).thenReturn(coachDto);

        ResponseEntity response = coachController.postCoach(createCoachDto);

        CoachDto ret = (CoachDto) response.getBody();

        if(!response.hasBody()) {
            ret = new CoachDto();
            ret.setId(0L);
        }

        coachDto.setId(ret.getId().describeConstable().orElse(0L));

        if(HttpStatus.BAD_REQUEST == response.getStatusCode())
            throw new NoSuchElementException("Coach not created");

        assert(HttpStatus.CREATED == response.getStatusCode());
        assert(coachDto == response.getBody());

        return coachDto;
    }

    @Test
    public void testDeleteCoach() {
        CreateCoachDto createCoachDto = new CreateCoachDto();
        createCoachDto.setCostRate(100);
        createCoachDto.setSport("Basketball");
        createCoachDto.setPassword("password");
        createCoachDto.setEmail("coach@gmail.com");
        createCoachDto.setFirstName("John");
        createCoachDto.setSecondName("Doe");

        CoachDto coachDto = new CoachDto();
        coachDto.setCostRate(100);
        coachDto.setSport("Basketball");
        coachDto.setPassword("password");
        coachDto.setEmail("coach@gmail.com");
        coachDto.setFirstName("John");
        coachDto.setSecondName("Doe");
        coachDto.setTrainings(new ArrayList<>());

        when(coachService.saveCoach(createCoachDto)).thenReturn(coachDto);

        ResponseEntity response = coachController.postCoach(createCoachDto);

        CoachDto ret = (CoachDto) response.getBody();

        if(!response.hasBody()) {
            ret = new CoachDto();
            ret.setId(0L);
        }

        Long id = ret.getId().describeConstable().orElse(0L);

        // Set up the mock service response for deleting a coach
        doNothing().when(coachService).deleteCoach(id);

        // Send the request to the controller to delete the coach
        ResponseEntity deleteResponse = coachController.deleteCoach(id);

        // Verify the response status for deleting a coach
        assert(HttpStatus.OK == deleteResponse.getStatusCode());

        // Set up the mock service response for getting a coach
        when(coachService.getCoach(id)).thenThrow(new NoSuchElementException());

        // Send the request to the controller to get the deleted coach
        ResponseEntity getResponse = coachController.getCoach(id);
        assert(HttpStatus.BAD_REQUEST == getResponse.getStatusCode());
    }

    @Test
    public void testGetCoach() {
        CreateCoachDto createCoachDto = new CreateCoachDto();
        createCoachDto.setCostRate(100);
        createCoachDto.setSport("Basketball");
        createCoachDto.setPassword("password");
        createCoachDto.setEmail("coach@gmail.com");
        createCoachDto.setFirstName("John");
        createCoachDto.setSecondName("Doe");

        CoachDto coachDto = new CoachDto();
        coachDto.setCostRate(100);
        coachDto.setSport("Basketball");
        coachDto.setPassword("password");
        coachDto.setEmail("coach@gmail.com");
        coachDto.setFirstName("John");
        coachDto.setSecondName("Doe");
        coachDto.setTrainings(new ArrayList<>());

        when(coachService.saveCoach(createCoachDto)).thenReturn(coachDto);

        ResponseEntity response = coachController.postCoach(createCoachDto);

        CoachDto ret = (CoachDto) response.getBody();

        if(!response.hasBody()) {
            ret = new CoachDto();
            ret.setId(0L);
        }

        Long id = ret.getId().describeConstable().orElse(0L);

        // Set up the expected response data
        CoachDto coachDtoTwo = new CoachDto();
        coachDtoTwo.setId(id);
        coachDtoTwo.setCostRate(100);
        coachDtoTwo.setSport("Basketball");
        coachDtoTwo.setPassword("password");
        coachDtoTwo.setEmail("coach@gmail.com");
        coachDtoTwo.setFirstName("John");
        coachDtoTwo.setSecondName("Doe");
        coachDtoTwo.setTrainings(new ArrayList<>());

        // Set up the mock service response
        when(coachService.getCoach(id)).thenReturn(coachDtoTwo);

        // Send the request to the controller
        ResponseEntity responseTwo = coachController.getCoach(id);

        // Verify the response status and body
        assert(HttpStatus.OK == responseTwo.getStatusCode());
        assert(coachDtoTwo == responseTwo.getBody());
    }
}