package cz.cvut.fit.hajekad3.reservantor.ApplicationLayer.Implementations;

import cz.cvut.fit.hajekad3.reservantor.DomainLayer.Coach;
import cz.cvut.fit.hajekad3.reservantor.InfrastructureLayer.Storage.Abstractions.ICoachRepository;
import cz.cvut.fit.hajekad3.reservantor.InterfaceLayer.Dtos.Coach.CreateCoachDto;
import cz.cvut.fit.hajekad3.reservantor.InterfaceLayer.Dtos.Coach.CoachDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class CoachService {
    @Autowired
    private ICoachRepository coachRepository;

    public CoachService(ICoachRepository coachRepository) {
        this.coachRepository = coachRepository;
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

        return coachRepository.save(currCoach).convertToDto();
    }

    public void deleteCoach(Long id) {
        if(!coachRepository.existsById(id))
            throw new NoSuchElementException("Error: Coach does not exist. id: " + id);

        coachRepository.deleteById(id);
    }
}
