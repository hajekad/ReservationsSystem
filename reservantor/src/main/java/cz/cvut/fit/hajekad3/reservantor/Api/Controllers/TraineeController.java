package cz.cvut.fit.hajekad3.reservantor.Api.Controllers;

import cz.cvut.fit.hajekad3.reservantor.ApplicationLayer.Implementations.TraineeService;
import cz.cvut.fit.hajekad3.reservantor.InterfaceLayer.Dtos.Trainee.CreateTraineeDto;
import cz.cvut.fit.hajekad3.reservantor.InterfaceLayer.Dtos.Trainee.TraineeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/trainee")
@ComponentScan(basePackages = "cz.cvut.fit.hajekad3.reservantor.ApplicationLayer.Implementations")
public class TraineeController {
    @Autowired
    private TraineeService traineeService;

    public TraineeController() {}

    @PostMapping
    public TraineeDto postTrainee(@RequestBody CreateTraineeDto traineeDto) {
        System.out.print("Api received a post request for user: ");
        System.out.println(traineeDto.getUsername());

        return traineeService.saveTrainee(traineeDto);
    }

    @GetMapping
    public ResponseEntity getTrainee(@RequestParam Long id) {
        System.out.print("Api received a get request for user_id: ");
        System.out.println(id);

        TraineeDto ret;

        try{
            ret = traineeService.getTrainee(id);
        }
        catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().body("Error: trainee not found with id " + id);
        }

        return ResponseEntity.ok(ret);
    }
}
