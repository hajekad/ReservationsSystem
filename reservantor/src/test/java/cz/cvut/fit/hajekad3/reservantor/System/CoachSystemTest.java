package cz.cvut.fit.hajekad3.reservantor.System;

import cz.cvut.fit.hajekad3.reservantor.Api.Controllers.CoachController;
import cz.cvut.fit.hajekad3.reservantor.Helper.CoachHelper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

import static java.lang.Math.random;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CoachSystemTest {
    private CoachHelper coachHelper;
    @Mock
    private CoachController coachController;

    public CoachSystemTest() {
        this.coachHelper = new CoachHelper();
    }

    @Test
    public void testPost() {
        for(int i = 0; i < 100; i++) {
            coachController.postCoach(coachHelper.fillCreateCoachDto());
        }
    }

    @Test
    public void coachGet() {
        Random r = new Random();
        long randomLong = r.nextLong();
        for(int i = 0; i < 100; i++) {
            coachController.getCoach(r.nextLong() % 100);
        }
    }

    @Test
    public void coachDelete() {
        Random r = new Random();
        long randomLong = r.nextLong();
        for(int i = 0; i < 100; i++) {
            coachController.deleteCoach(r.nextLong() % 100);
        }
    }
}
