package com.mytimeplan.assignment.service;

import com.mytimeplan.assignment.repository.Star;
import com.mytimeplan.assignment.repository.StarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StarService {
    private static final long DISTANCE_INTERVAL = 10000000000L;

    private StarRepository starRepository;

    @Autowired
    public StarService(StarRepository starRepository) {
        this.starRepository = starRepository;
    }

    public StarService() {
    }

    public Star getStarById(Long id) {
        return starRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Star not found"));
    }

    public Star addNewStar(Star star) {
        return starRepository.save(star);
    }

    public Star updateStar(Long id, Star star) {
        if (!starRepository.existsById(id)) {
            throw new NoSuchElementException("Star now found");
        }
        star.setId(id);
        return starRepository.save(star);
    }

    public void deleteStar(Long id) {
        if (!starRepository.existsById(id)) {
            throw new NoSuchElementException("Star not found");
        }
        starRepository.deleteById(id);
    }

    public List<Star> findClosestStars(List<Star> stars, int size) {
        return stars.stream().sorted(Comparator.comparingLong(Star::getDistance)).limit(size).toList();
    }

    public Map<Long, Integer> getNumberOfStarsByDistances(List<Star> stars) {
        Map<Long, Integer> starCountByDistance = new HashMap<>();

        for (Star star : stars) {
            long distanceInterval = (star.getDistance() / DISTANCE_INTERVAL) * DISTANCE_INTERVAL;
            starCountByDistance.put(distanceInterval, starCountByDistance.getOrDefault(distanceInterval, 0) + 1);
        }

        return starCountByDistance;
    }

    public Collection<Star> getUniqueStars(Collection<Star> stars) {
        Map<String, Star> uniqueStarsMap = new HashMap<>();

        for (Star star : stars) {
            uniqueStarsMap.put(star.getName(), star);
        }

        return uniqueStarsMap.values();
    }
}
