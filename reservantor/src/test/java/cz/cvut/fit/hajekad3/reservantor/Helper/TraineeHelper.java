package cz.cvut.fit.hajekad3.reservantor.Helper;

import cz.cvut.fit.hajekad3.reservantor.DomainLayer.Trainee;
import cz.cvut.fit.hajekad3.reservantor.InterfaceLayer.Dtos.Trainee.CreateTraineeDto;
import cz.cvut.fit.hajekad3.reservantor.InterfaceLayer.Dtos.Trainee.TraineeDto;

import java.util.ArrayList;

public class TraineeHelper {
    public Trainee fillTrainee() {
        Trainee ret = new Trainee(fillTraineeDto());

        return ret;
    }

    public TraineeDto fillTraineeDto() {
        TraineeDto ret = new TraineeDto();

        ret.setId(1L);
        ret.setSkillCap(50);
        ret.setTrainings(new ArrayList<>());
        ret.setUsername("user");
        ret.setPassword("pass");
        ret.setEmail("mail@mail.com");
        ret.setFirstName("Jan");
        ret.setSecondName("Novak");

        return ret;
    }

    public CreateTraineeDto fillCreateTraineeDto() {
        CreateTraineeDto ret = new CreateTraineeDto();

        ret.setSkillCap(50);
        ret.setUsername("user");
        ret.setPassword("pass");
        ret.setEmail("mail@mail.com");
        ret.setFirstName("Jan");
        ret.setSecondName("Novak");

        return ret;
    }

    public Trainee fillTraineeFromCreateTraineeDto() {
        Trainee ret = fillTrainee();

        return ret;
    }
}
