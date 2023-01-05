package cz.cvut.fit.hajekad3.reservantor.Api.Controllers;

import cz.cvut.fit.hajekad3.reservantor.ApplicationLayer.Implementations.CoachService;
import cz.cvut.fit.hajekad3.reservantor.InterfaceLayer.Dtos.Coach.CoachDto;
import cz.cvut.fit.hajekad3.reservantor.InterfaceLayer.Dtos.Coach.CreateCoachDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/coach")
@ComponentScan(basePackages = "cz.cvut.fit.hajekad3.reservantor.ApplicationLayer.Implementations")
public class CoachController {
    @Autowired
    private CoachService coachService;

    public CoachController() {}

    /**
     * lets coaches update their trainees skillCap
     * @param idCoach
     * @param idTrainee
     * @param skillCap
     * @return
     */
    @PutMapping("/bussiness")
    public ResponseEntity updateTraineesSkillCap(@RequestParam Long idCoach, @RequestParam Long idTrainee, @RequestParam int skillCap) {
        try {
            coachService.updateTraineesSkillCap(idCoach, idTrainee, skillCap);
        }
        catch(NoSuchElementException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok().build();
    }

    /**
     * posts coach
     * @param coachDto
     * @return
     */
    @PostMapping
    public ResponseEntity postCoach(@RequestBody CreateCoachDto coachDto) {
        CoachDto ret = coachService.saveCoach(coachDto);

        if(ret.getId() == null)
            return ResponseEntity.badRequest().build();

        String reti = ret.getId().describeConstable().orElse(0L).toString();

        return ResponseEntity.created(URI.create(reti)).body(ret);
    }

    /**
     * gets coach by id
     * @param id
     * @return
     */
    @GetMapping
    public ResponseEntity getCoach(@RequestParam Long id) {
        CoachDto ret;

        try{
            ret = coachService.getCoach(id);
        }
        catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().body("Error: coach not found with id " + id);
        }

        return ResponseEntity.ok(ret);
    }

    /**
     * updates coach
     * @param coachDto
     * @return
     */
    @PutMapping
    public ResponseEntity updateCoach(@RequestBody CoachDto coachDto) {
        CoachDto ret;

        try {
            ret = coachService.updateCoach(coachDto);
        }
        catch(NoSuchElementException e) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok().body(ret);
    }

    /**
     * deletes coach by id
     * @param id
     * @return
     */
    @DeleteMapping
    public ResponseEntity deleteCoach(@RequestParam Long id) {
        try {
            coachService.deleteCoach(id);
        }
        catch(NoSuchElementException e) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok().build();
    }
}
