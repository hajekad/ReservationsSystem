package cz.cvut.fit.hajekad3.reservantor.ApplicationLayer.Implementations;

import cz.cvut.fit.hajekad3.reservantor.DomainLayer.Trainee;
import cz.cvut.fit.hajekad3.reservantor.DomainLayer.Training;
import cz.cvut.fit.hajekad3.reservantor.InfrastructureLayer.Storage.Abstractions.ITraineeRepository;
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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TraineeService {
    @Autowired
    private ITraineeRepository traineeRepository;

    @Autowired
    private ITrainingRepositoryJpa trainingRepositoryJpa;

    @Autowired
    private ITrainingRepositoryExtraMethods trainingRepositoryExtraMethods;

    private Trainee dtoToTrainee(TraineeDto traineeDto) {
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

    public TraineeService(ITraineeRepository traineeRepository, ITrainingRepositoryJpa trainingRepositoryJpa, ITrainingRepositoryExtraMethods trainingRepositoryExtraMethods) {
        this.traineeRepository = traineeRepository;
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

        Training chosenTraining = availableTrainings.get(0);

        Collection<Training> newTrainingList = trainee.getTrainings();
        Collection<Trainee> newTraineeList = chosenTraining.getParticipatingTrainees();

        newTrainingList.add(chosenTraining);
        newTraineeList.add(trainee);

        chosenTraining.setParticipatingTrainees(newTraineeList);
        trainee.setTrainings(newTrainingList);

        trainingRepositoryJpa.save(chosenTraining);
        traineeRepository.save(trainee);

        return chosenTraining.convertToDto();
    }

    public TraineeDto saveTrainee(CreateTraineeDto traineeDto) {
        Trainee newTrainee = new Trainee(traineeDto);

        Trainee ret = traineeRepository.save(newTrainee);

        return ret.convertToDto();
    }

    public TraineeDto getTrainee(Long id) {
        Trainee ret = traineeRepository.findById(id).orElse(null);

        if(ret == null)
            throw new NoSuchElementException("Error: Trainee does not exist. id: " + id);

        return ret.convertToDto();
    }

    public TraineeDto updateTrainee(TraineeDto traineeDto) {
        if(!traineeRepository.existsById(traineeDto.getId()))
            throw new NoSuchElementException("Error: Trainee does not exist. id: " + traineeDto.getId());

        Trainee currTrainee = dtoToTrainee(traineeDto);

        return traineeRepository.save(currTrainee).convertToDto();
    }

    public void deleteTrainee(Long id) {
        if(!traineeRepository.existsById(id))
            throw new NoSuchElementException("Error: Trainee does not exist. id: " + id);

        traineeRepository.deleteById(id);
    }
}
