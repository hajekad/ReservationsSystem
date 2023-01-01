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

    public CoachDto saveCoach(CreateCoachDto coachDto){
        System.out.print("Service received a post request for coach: ");
        System.out.println(coachDto.getEmail());

        Coach newCoach = new Coach(coachDto);

        Coach ret = coachRepository.save(newCoach);

        return ret.convertToDto();
    }

    public CoachDto getCoach(Long id) {
        System.out.print("Service received a get request for coach_id: ");
        System.out.println(id);

        Coach ret = coachRepository.findById(id).orElse(null);

        if(ret == null)
            throw new NoSuchElementException("No such id.");

        return ret.convertToDto();
    }
}
