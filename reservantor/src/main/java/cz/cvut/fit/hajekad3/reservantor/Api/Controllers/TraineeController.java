package cz.cvut.fit.hajekad3.reservantor.Api.Controllers;

import cz.cvut.fit.hajekad3.reservantor.ApplicationLayer.Implementations.TraineeService;
import cz.cvut.fit.hajekad3.reservantor.InterfaceLayer.Dtos.Trainee.CreateTraineeDto;
import cz.cvut.fit.hajekad3.reservantor.InterfaceLayer.Dtos.Trainee.TraineeDto;
import cz.cvut.fit.hajekad3.reservantor.InterfaceLayer.Dtos.Training.TrainingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/trainee")
@ComponentScan(basePackages = "cz.cvut.fit.hajekad3.reservantor.ApplicationLayer.Implementations")
public class TraineeController {
    @Autowired
    private TraineeService traineeService;

    public TraineeController() {}

    @PutMapping("/bussiness")
    public ResponseEntity getTrainingsInTimeFrame(@RequestParam String from, @RequestParam String to, @RequestBody TraineeDto traineeDto) {
        TrainingDto ret = null;

        try {
            ret = traineeService.assignTraining(from, to, traineeDto);
        }
        catch(NoSuchElementException e) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok().body(ret);
    }

    @PostMapping
    public ResponseEntity postTrainee(@RequestBody CreateTraineeDto traineeDto) {
        TraineeDto ret = traineeService.saveTrainee(traineeDto);

        return ResponseEntity.created(URI.create(ret.getId().toString())).body(ret);
    }

    @GetMapping
    public ResponseEntity getTrainee(@RequestParam Long id) {
        TraineeDto ret;

        try{
            ret = traineeService.getTrainee(id);
        }
        catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().body("Error: trainee not found with id " + id);
        }

        return ResponseEntity.ok(ret);
    }

    @PutMapping
    public ResponseEntity updateTrainee(@RequestBody TraineeDto traineeDto) {
        TraineeDto ret;

        try {
            ret = traineeService.updateTrainee(traineeDto);
        }
        catch(NoSuchElementException e) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok().body(ret);
    }

    @DeleteMapping
    ResponseEntity deleteTrainee(@RequestParam Long id) {
        try {
            traineeService.deleteTrainee(id);
        }
        catch(NoSuchElementException e) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok().build();
    }
}
