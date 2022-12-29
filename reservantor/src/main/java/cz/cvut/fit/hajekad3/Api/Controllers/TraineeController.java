package cz.cvut.fit.hajekad3.Api.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trainee")
public class TraineeController {
    public TraineeController() {}

    @PostMapping
    public void postTrainee()
    {
        System.out.println("Hello world!");
    }

    @GetMapping
    public String getTrainee()
    {
        return "Hello world!";
    }
}
