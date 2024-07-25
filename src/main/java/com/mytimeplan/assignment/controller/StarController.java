package com.mytimeplan.assignment.controller;

import com.mytimeplan.assignment.repository.Star;
import com.mytimeplan.assignment.service.StarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/stars")
public class StarController {

    private final StarService starService;

    @Autowired
    public StarController(StarService starService) {
        this.starService = starService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Star> getStarById(@PathVariable Long id) {
        try {
            Star star = starService.getStarById(id);
            return new ResponseEntity<>(star, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Star> addNewStar(@RequestBody Star star) {
        var newStar = starService.addNewStar(star);
        return new ResponseEntity<>(newStar, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Star> updateStar(@PathVariable Long id, @RequestBody Star star) {
        try {
            var updatedStar = starService.updateStar(id, star);
            return new ResponseEntity<>(updatedStar, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteStar(@PathVariable Long id) {
        try {
            starService.deleteStar(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/closest")
    public ResponseEntity<List<Star>> findClosestStars(@RequestBody List<Star> stars, @RequestParam int size) {
        List<Star> closestStars = starService.findClosestStars(stars, size);
        return new ResponseEntity<>(closestStars, HttpStatus.OK);
    }

    @PostMapping("/distances")
    public ResponseEntity<Map<Long, Integer>> getNumberOfStarsByDistances(@RequestBody List<Star> stars) {
        Map<Long, Integer> result = starService.getNumberOfStarsByDistances(stars);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/unique")
    public ResponseEntity<Collection<Star>> getUniqueStars(@RequestBody Collection<Star> stars) {
        Collection<Star> uniqueStars = starService.getUniqueStars(stars);
        return new ResponseEntity<>(uniqueStars, HttpStatus.OK);
    }

}
