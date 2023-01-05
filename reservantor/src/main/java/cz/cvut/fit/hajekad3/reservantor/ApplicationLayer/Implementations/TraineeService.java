package cz.cvut.fit.hajekad3.reservantor.ApplicationLayer.Implementations;

import cz.cvut.fit.hajekad3.reservantor.DomainLayer.Trainee;
import cz.cvut.fit.hajekad3.reservantor.DomainLayer.Training;
import cz.cvut.fit.hajekad3.reservantor.InfrastructureLayer.Storage.Abstractions.ITraineeRepositoryExtraMethods;
import cz.cvut.fit.hajekad3.reservantor.InfrastructureLayer.Storage.Abstractions.ITraineeRepositoryJpa;
import cz.cvut.fit.hajekad3.reservantor.InfrastructureLayer.Storage.Abstractions.ITrainingRepositoryExtraMethods;
import cz.cvut.fit.hajekad3.reservantor.InfrastructureLayer.Storage.Abstractions.ITrainingRepositoryJpa;
import cz.cvut.fit.hajekad3.reservantor.InterfaceLayer.Dtos.Trainee.CreateTraineeDto;
import cz.cvut.fit.hajekad3.reservantor.InterfaceLayer.Dtos.Trainee.TraineeDto;
import cz.cvut.fit.hajekad3.reservantor.InterfaceLayer.Dtos.Training.TrainingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class TraineeService {
    @Autowired
    private ITraineeRepositoryJpa traineeRepositoryJpa;

    @Autowired
    private ITraineeRepositoryExtraMethods traineeRepositoryExtraMethods;

    @Autowired
    private ITrainingRepositoryJpa trainingRepositoryJpa;

    @Autowired
    private ITrainingRepositoryExtraMethods trainingRepositoryExtraMethods;

    private Trainee dtoToTrainee(TraineeDto traineeDto) {
        if(traineeDto.getId() == null)
            throw new NoSuchElementException();

        Trainee ret = new Trainee(traineeDto);

        ArrayList<Training> tmp = new ArrayList<Training>();
        Training training = null;
        for (Long i: traineeDto.getTrainings()) {
            training = trainingRepositoryJpa.findById(i).orElse(null);

            if(training == null)
                throw new NoSuchElementException("Error: No such Training");

            tmp.add(training);
        }
        ret.setTrainings(tmp);

        return ret;
    }

    private boolean validateTimestamp(String timestampString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            sdf.parse(timestampString);
        }
        catch (ParseException e) {
            return false;
        }

        return true;
    }

    public TraineeService(ITraineeRepositoryJpa traineeRepositoryJpa, ITraineeRepositoryExtraMethods traineeRepositoryExtraMethods, ITrainingRepositoryJpa trainingRepositoryJpa, ITrainingRepositoryExtraMethods trainingRepositoryExtraMethods) {
        this.traineeRepositoryJpa = traineeRepositoryJpa;
        this.traineeRepositoryExtraMethods = traineeRepositoryExtraMethods;
        this.trainingRepositoryJpa = trainingRepositoryJpa;
        this.trainingRepositoryExtraMethods = trainingRepositoryExtraMethods;
    }

    public TrainingDto assignTraining(String fromString, String toString, TraineeDto traineeDto) {
        if(!validateTimestamp(fromString) || !validateTimestamp(toString))
            throw new NoSuchElementException();

        Timestamp from = Timestamp.valueOf(fromString);
        Timestamp to = Timestamp.valueOf(toString);
        Trainee trainee = dtoToTrainee(traineeDto);
        List<Training> availableTrainings = trainingRepositoryExtraMethods.trainingsInTimeframe(from, to);

        if(availableTrainings.isEmpty())
            throw new NoSuchElementException();

        Collections.sort(availableTrainings, new Comparator<Training>() {
            @Override
            public int compare(Training o1, Training o2) {
                return o1.getParticipatingTrainees().size() - o2.getParticipatingTrainees().size();
            }
        });

        Training chosenTraining = availableTrainings.get(0);

        return chosenTraining.convertToDto();
    }

    public TraineeDto saveTrainee(CreateTraineeDto traineeDto) {
        Trainee newTrainee = new Trainee(traineeDto);

        Trainee ret = traineeRepositoryJpa.save(newTrainee);

        return ret.convertToDto();
    }

    public TraineeDto getTrainee(Long id) {
        Trainee ret = traineeRepositoryJpa.findById(id).orElse(null);

        if(ret == null)
            throw new NoSuchElementException("Error: Trainee does not exist. id: " + id);

        return ret.convertToDto();
    }

    public TraineeDto updateTrainee(TraineeDto traineeDto) {
        Trainee currTrainee = dtoToTrainee(traineeDto);

        if(!traineeRepositoryJpa.existsById(traineeDto.getId()))
            throw new NoSuchElementException("Error: Trainee does not exist. id: " + traineeDto.getId());

        return traineeRepositoryJpa.save(currTrainee).convertToDto();
    }

    public void deleteTrainee(Long id) {
        if(!traineeRepositoryJpa.existsById(id))
            throw new NoSuchElementException("Error: Trainee does not exist. id: " + id);

        traineeRepositoryJpa.deleteById(id);
    }

    public TraineeDto findMatch(int range, TraineeDto challengerDto) {
        Trainee challenger = dtoToTrainee(challengerDto);

        List<Trainee> opponents = traineeRepositoryExtraMethods.findMatch(range, challenger);

        if(opponents.isEmpty())
            throw new NoSuchElementException("Error: No other users share the skill range.");

        return opponents.stream().findFirst().get().convertToDto();
    }
}
