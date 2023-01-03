package cz.cvut.fit.hajekad3.reservantor.ApplicationLayer.Implementations;

import cz.cvut.fit.hajekad3.reservantor.DomainLayer.Coach;
import cz.cvut.fit.hajekad3.reservantor.DomainLayer.Training;
import cz.cvut.fit.hajekad3.reservantor.InfrastructureLayer.Storage.Abstractions.ICoachRepository;
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
    private ICoachRepository coachRepository;

    @Autowired
    private ITrainingRepositoryJpa trainingRepository;

    public CoachService(ICoachRepository coachRepository, ITrainingRepositoryJpa trainingRepository) {
        this.coachRepository = coachRepository;
        this.trainingRepository = trainingRepository;
    }

    public CoachDto saveCoach(CreateCoachDto coachDto) {
        Coach newCoach = new Coach(coachDto);

        Coach ret = coachRepository.save(newCoach);

        return ret.convertToDto();
    }

    public CoachDto getCoach(Long id) {
        Coach ret = coachRepository.findById(id).orElse(null);

        if(ret == null)
            throw new NoSuchElementException("Error: Coach does not exist. id: " + id);

        return ret.convertToDto();
    }

    public CoachDto updateCoach(CoachDto coachDto) {
        if(!coachRepository.existsById(coachDto.getId()))
            throw new NoSuchElementException("Error: Coach does not exist. id: " + coachDto.getId());

        Coach currCoach = new Coach(coachDto);

        ArrayList<Training> tmp = new ArrayList<Training>();
        Training training = null;
        for (Long i: coachDto.getTrainings()) {
            training = trainingRepository.findById(i).orElse(null);

            if(training == null)
                throw new NoSuchElementException("Error: No such Trainee");

            tmp.add(training);
        }
        currCoach.setTrainings(tmp);

        return coachRepository.save(currCoach).convertToDto();
    }

    public void deleteCoach(Long id) {
        if(!coachRepository.existsById(id))
            throw new NoSuchElementException("Error: Coach does not exist. id: " + id);

        coachRepository.deleteById(id);
    }

    public void updateTraineesSkillCap(Long idCoach, Long idTrainee, int skillCap) {

    }
}
