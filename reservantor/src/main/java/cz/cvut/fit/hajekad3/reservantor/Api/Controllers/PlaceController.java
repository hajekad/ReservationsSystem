package cz.cvut.fit.hajekad3.reservantor.Api.Controllers;

import cz.cvut.fit.hajekad3.reservantor.ApplicationLayer.Implementations.PlaceService;
import cz.cvut.fit.hajekad3.reservantor.InterfaceLayer.Dtos.Place.PlaceDto;
import cz.cvut.fit.hajekad3.reservantor.InterfaceLayer.Dtos.Place.CreatePlaceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/place")
@ComponentScan(basePackages = "cz.cvut.fit.hajekad3.reservantor.ApplicationLayer.Implementations")
public class PlaceController {
    @Autowired
    private PlaceService placeService;

    public PlaceController() {}

    @PostMapping
    public PlaceDto postPlace(@RequestBody CreatePlaceDto placeDto) {
        System.out.print("Api received a post request for user: ");
        System.out.print(placeDto.getLatitude() + ' ' + placeDto.getLongitude());

        return placeService.savePlace(placeDto);
    }

    @GetMapping
    public ResponseEntity getPlace(@RequestParam Long id) {
        System.out.print("Api received a get request for user_id: ");
        System.out.println(id);

        PlaceDto ret;

        try{
            ret = placeService.getPlace(id);
        }
        catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().body("Error: place not found with id " + id);
        }

        return ResponseEntity.ok(ret);
    }
}
