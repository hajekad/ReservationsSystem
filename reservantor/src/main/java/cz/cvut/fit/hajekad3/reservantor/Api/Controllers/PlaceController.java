package cz.cvut.fit.hajekad3.reservantor.Api.Controllers;

import cz.cvut.fit.hajekad3.reservantor.ApplicationLayer.Implementations.PlaceService;
import cz.cvut.fit.hajekad3.reservantor.InterfaceLayer.Dtos.Place.PlaceDto;
import cz.cvut.fit.hajekad3.reservantor.InterfaceLayer.Dtos.Place.CreatePlaceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/place")
@ComponentScan(basePackages = "cz.cvut.fit.hajekad3.reservantor.ApplicationLayer.Implementations")
public class PlaceController {
    @Autowired
    private PlaceService placeService;

    public PlaceController() {}

    @PostMapping
    public ResponseEntity postPlace(@RequestBody CreatePlaceDto placeDto) {
        PlaceDto ret = placeService.savePlace(placeDto);

        return ResponseEntity.created(URI.create(ret.getId().toString())).body(ret);
    }

    @GetMapping
    public ResponseEntity getPlace(@RequestParam Long id) {
        PlaceDto ret;

        try{
            ret = placeService.getPlace(id);
        }
        catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().body("Error: place not found with id " + id);
        }

        return ResponseEntity.ok(ret);
    }

    @PutMapping
    public ResponseEntity updatePlace(@RequestBody PlaceDto placeDto) {
        PlaceDto ret;

        try {
            ret = placeService.updatePlace(placeDto);
        }
        catch(NoSuchElementException e) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok().body(ret);
    }

    @DeleteMapping
    ResponseEntity deletePlace(@RequestParam Long id) {
        try {
            placeService.deletePlace(id);
        }
        catch(NoSuchElementException e) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok().build();
    }
}
