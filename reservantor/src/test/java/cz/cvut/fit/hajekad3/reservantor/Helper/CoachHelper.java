package cz.cvut.fit.hajekad3.reservantor.Helper;

import cz.cvut.fit.hajekad3.reservantor.DomainLayer.Coach;
import cz.cvut.fit.hajekad3.reservantor.InterfaceLayer.Dtos.Coach.CreateCoachDto;
import cz.cvut.fit.hajekad3.reservantor.InterfaceLayer.Dtos.Coach.CoachDto;

import java.util.ArrayList;

public class CoachHelper {
    public Coach fillCoach() {
        Coach ret = new Coach(fillCoachDto());

        return ret;
    }

    public CoachDto fillCoachDto() {
        CoachDto ret = new CoachDto();

        ret.setId(1L);
        ret.setCostRate(50);
        ret.setTrainings(new ArrayList<>());
        ret.setPassword("pass");
        ret.setEmail("mail@mail.com");
        ret.setFirstName("Jan");
        ret.setSecondName("Novak");

        return ret;
    }

    public CreateCoachDto fillCreateCoachDto() {
        CreateCoachDto ret = new CreateCoachDto();

        ret.setPassword("pass");
        ret.setCostRate(50);
        ret.setEmail("mail@mail.com");
        ret.setFirstName("Jan");
        ret.setSecondName("Novak");

        return ret;
    }

    public Coach fillCoachFromCreateCoachDto() {
        Coach ret = fillCoach();

        return ret;
    }
}
