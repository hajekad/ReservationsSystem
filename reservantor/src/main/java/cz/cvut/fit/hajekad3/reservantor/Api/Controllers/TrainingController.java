package cz.cvut.fit.hajekad3.reservantor.Api.Controllers;

import cz.cvut.fit.hajekad3.reservantor.ApplicationLayer.Implementations.TrainingService;
import cz.cvut.fit.hajekad3.reservantor.InterfaceLayer.Dtos.Training.CreateTrainingDto;
import cz.cvut.fit.hajekad3.reservantor.InterfaceLayer.Dtos.Training.TrainingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/training")
@ComponentScan(basePackages = "cz.cvut.fit.hajekad3.reservantor.ApplicationLayer.Implementations")
public class TrainingController {
    @Autowired
    private TrainingService trainingService;

    public TrainingController() {}

    @PostMapping
    public TrainingDto postTraining(@RequestBody CreateTrainingDto trainingDto) {
        System.out.print("Api received a post request for training: ");
        System.out.print(trainingDto.getDateOfTraining());

        return trainingService.saveTraining(trainingDto);
    }

    @GetMapping
    public ResponseEntity getTraining(@RequestParam Long id) {
        System.out.print("Api received a get request for training: ");
        System.out.println(id);

        TrainingDto ret;

        try {
            ret = trainingService.getTraining(id);
        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().body("Error: training not found with id " + id);
        }

        return ResponseEntity.ok(ret);
    }
}
