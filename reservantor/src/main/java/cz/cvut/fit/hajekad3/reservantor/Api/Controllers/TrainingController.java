package cz.cvut.fit.hajekad3.reservantor.Api.Controllers;

import cz.cvut.fit.hajekad3.reservantor.ApplicationLayer.Implementations.TrainingService;
import cz.cvut.fit.hajekad3.reservantor.InterfaceLayer.Dtos.Training.CreateTrainingDto;
import cz.cvut.fit.hajekad3.reservantor.InterfaceLayer.Dtos.Training.TrainingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/training")
@ComponentScan(basePackages = "cz.cvut.fit.hajekad3.reservantor.ApplicationLayer.Implementations")
@CrossOrigin(origins = "*")
public class TrainingController {
    @Autowired
    private TrainingService trainingService;

    public TrainingController() {}

    /**
     * Creates an instantion of training in the database, idCoach has to exist and same goes for idPlace
     * @param trainingDto
     * @return
     */
    @PostMapping
    public ResponseEntity postTraining(@RequestBody CreateTrainingDto trainingDto) {
        TrainingDto ret;

        try {
            ret = trainingService.saveTraining(trainingDto);
        }
        catch(NoSuchElementException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }

        return ResponseEntity.created(URI.create(ret.getId().toString())).body(ret);
    }

    /**
     * Gets Training by id
     * @param id
     * @return
     */
    @GetMapping
    public ResponseEntity getTraining(@RequestParam Long id) {
        TrainingDto ret;

        try{
            ret = trainingService.getTraining(id);
        }
        catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().body("Error: training not found with id " + id);
        }

        return ResponseEntity.ok(ret);
    }

    /**
     * updates training, all ids have to already exist
     * @param trainingDto
     * @return
     */
    @PutMapping
    public ResponseEntity updateTraining(@RequestBody TrainingDto trainingDto) {
        TrainingDto ret;

        try {
            ret = trainingService.updateTraining(trainingDto);
        }
        catch(NoSuchElementException e) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok().body(ret);
    }

    /**
     * deletes training by id
     * @param id
     * @return
     */
    @DeleteMapping
    ResponseEntity deleteTraining(@RequestParam Long id) {
        try {
            trainingService.deleteTraining(id);
        }
        catch(NoSuchElementException e) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok().build();
    }
}
