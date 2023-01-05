package cz.cvut.fit.hajekad3.reservantor.Helper;

import cz.cvut.fit.hajekad3.reservantor.DomainLayer.Training;
import cz.cvut.fit.hajekad3.reservantor.InterfaceLayer.Dtos.Training.CreateTrainingDto;
import cz.cvut.fit.hajekad3.reservantor.InterfaceLayer.Dtos.Training.TrainingDto;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

public class TrainingHelper {
    private Timestamp now() {
        Calendar calendar = Calendar.getInstance();
        return new Timestamp(calendar.getTimeInMillis());
    }

    public Training fillTraining() {
        Training ret = new Training(fillTrainingDto());

        return ret;
    }

    public TrainingDto fillTrainingDto() {
        TrainingDto ret = new TrainingDto();

        ret.setId(1L);
        ret.setDateOfTraining(now().toString());
        ret.setDescription("Blank");
        ret.setIdCoach(1L);
        ret.setIdPlace(1L);
        ret.setParticipatingTraineesIds(new ArrayList<>());

        return ret;
    }

    public CreateTrainingDto fillCreateTrainingDto() {
        CreateTrainingDto ret = new CreateTrainingDto();

        ret.setDateOfTraining(now().toString());
        ret.setDescription("Blank");
        ret.setIdCoach(1L);
        ret.setIdPlace(1L);

        return ret;
    }

    public Training fillTrainingFromCreateTrainingDto() {
        Training ret = fillTraining();

        return ret;
    }
}
