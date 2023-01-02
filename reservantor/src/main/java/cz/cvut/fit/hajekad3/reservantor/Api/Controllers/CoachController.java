package cz.cvut.fit.hajekad3.reservantor.Api.Controllers;

import cz.cvut.fit.hajekad3.reservantor.ApplicationLayer.Implementations.CoachService;
import cz.cvut.fit.hajekad3.reservantor.InterfaceLayer.Dtos.Coach.CoachDto;
import cz.cvut.fit.hajekad3.reservantor.InterfaceLayer.Dtos.Coach.CreateCoachDto;
import cz.cvut.fit.hajekad3.reservantor.InterfaceLayer.Dtos.Coach.CreateCoachDto;
import cz.cvut.fit.hajekad3.reservantor.InterfaceLayer.Dtos.Coach.CoachDto;
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

    @PostMapping
    public ResponseEntity postCoach(@RequestBody CreateCoachDto coachDto) {
        CoachDto ret = coachService.saveCoach(coachDto);

        return ResponseEntity.created(URI.create(ret.getId().toString())).body(ret);
    }

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

    @DeleteMapping
    ResponseEntity deleteCoach(@RequestParam Long id) {
        try {
            coachService.deleteCoach(id);
        }
        catch(NoSuchElementException e) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok().build();
    }
}
