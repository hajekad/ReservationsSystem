package cz.cvut.fit.hajekad3.reservantor.Api.Controllers;

import cz.cvut.fit.hajekad3.reservantor.ApplicationLayer.Implementations.CoachService;
import cz.cvut.fit.hajekad3.reservantor.InterfaceLayer.Dtos.Coach.CoachDto;
import cz.cvut.fit.hajekad3.reservantor.InterfaceLayer.Dtos.Coach.CreateCoachDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/coach")
@ComponentScan(basePackages = "cz.cvut.fit.hajekad3.reservantor.ApplicationLayer.Implementations")
public class CoachController {
    @Autowired
    private CoachService coachService;

    public CoachController() {}

    @PostMapping
    public CoachDto postCoach(@RequestBody CreateCoachDto coachDto) {
        System.out.print("Api received a post request for coach: ");
        System.out.println(coachDto.getEmail());

        return coachService.saveCoach(coachDto);
    }

    @GetMapping
    public ResponseEntity getCoach(@RequestParam Long id) {
        System.out.print("Api received a get request for coach_id: ");
        System.out.println(id);

        CoachDto ret;

        try {
            ret = coachService.getCoach(id);
        }
        catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().body("Error: coach not found with id " + id);
        }

        return ResponseEntity.ok(ret);
    }
}
