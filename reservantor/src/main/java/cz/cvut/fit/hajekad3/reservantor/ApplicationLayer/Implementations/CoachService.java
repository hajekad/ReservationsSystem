package cz.cvut.fit.hajekad3.reservantor.ApplicationLayer.Implementations;

import cz.cvut.fit.hajekad3.reservantor.DomainLayer.Coach;
import cz.cvut.fit.hajekad3.reservantor.DomainLayer.Trainee;
import cz.cvut.fit.hajekad3.reservantor.DomainLayer.Training;
import cz.cvut.fit.hajekad3.reservantor.InfrastructureLayer.Storage.Abstractions.ICoachRepositoryExtraMethods;
import cz.cvut.fit.hajekad3.reservantor.InfrastructureLayer.Storage.Abstractions.ICoachRepositoryJpa;
import cz.cvut.fit.hajekad3.reservantor.InfrastructureLayer.Storage.Abstractions.ITraineeRepositoryJpa;
import cz.cvut.fit.hajekad3.reservantor.InfrastructureLayer.Storage.Abstractions.ITrainingRepositoryJpa;
import cz.cvut.fit.hajekad3.reservantor.InterfaceLayer.Dtos.Coach.CreateCoachDto;
import cz.cvut.fit.hajekad3.reservantor.InterfaceLayer.Dtos.Coach.CoachDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.NoSuchElementException;

@Service
public class CoachService {
    @Autowired
    private ICoachRepositoryJpa coachRepositoryJpa;

    @Autowired
    private ITrainingRepositoryJpa trainingRepositoryJpa;
    @Autowired
    private ITraineeRepositoryJpa traineeRepository;

    @Autowired
    private ICoachRepositoryExtraMethods coachRepositoryExtraMethods;

    public CoachService(ICoachRepositoryJpa coachRepositoryJpa, ITrainingRepositoryJpa trainingRepositoryJpa, ITraineeRepositoryJpa traineeRepository, ICoachRepositoryExtraMethods coachRepositoryExtraMethods) {
        this.coachRepositoryJpa = coachRepositoryJpa;
        this.trainingRepositoryJpa = trainingRepositoryJpa;
        this.traineeRepository = traineeRepository;
        this.coachRepositoryExtraMethods = coachRepositoryExtraMethods;
    }

    public CoachDto saveCoach(CreateCoachDto coachDto) {
        Coach newCoach = new Coach(coachDto);

        Coach ret = coachRepositoryJpa.save(newCoach);

        return ret.convertToDto();
    }

    public CoachDto getCoach(Long id) {
        Coach ret = coachRepositoryJpa.findById(id).orElse(null);

        if(ret == null)
            throw new NoSuchElementException("Error: Coach does not exist. id: " + id);

        return ret.convertToDto();
    }

    public CoachDto updateCoach(CoachDto coachDto) {
        if(!coachRepositoryJpa.existsById(coachDto.getId()))
            throw new NoSuchElementException("Error: Coach does not exist. id: " + coachDto.getId());

        Coach currCoach = new Coach(coachDto);

        ArrayList<Training> tmp = new ArrayList<Training>();
        Training training = null;
        for (Long i: coachDto.getTrainings()) {
            training = trainingRepositoryJpa.findById(i).orElse(null);

            if(training == null)
                throw new NoSuchElementException("Error: No such Trainee");

            tmp.add(training);
        }
        currCoach.setTrainings(tmp);

        return coachRepositoryJpa.save(currCoach).convertToDto();
    }

    public void deleteCoach(Long id) {
        if(!coachRepositoryJpa.existsById(id))
            throw new NoSuchElementException("Error: Coach does not exist. id: " + id);

        coachRepositoryJpa.deleteById(id);
    }

    public void updateTraineesSkillCap(Long idCoach, Long idTrainee, int skillCap) {
        Coach coach = coachRepositoryJpa.findById(idCoach).orElse(null);

        if(coach == null)
            throw new NoSuchElementException("Error: Coach does not exist. id: " + idCoach);

        Trainee trainee = traineeRepository.findById(idTrainee).orElse(null);

        if(trainee == null)
            throw new NoSuchElementException("Error: trainee does not exist. id: " + idTrainee);

        if(!coachRepositoryExtraMethods.coachTrainedTrainee(trainee, coach))
            throw new NoSuchElementException("Error: coach did not train trainee");

        trainee.setSkillCap(skillCap);

        traineeRepository.save(trainee);
    }
}
