package com.mytimeplan.assignment;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class StarService {

    List<Star> findClosestStars(List<Star> stars, int size) {
        return stars.stream().sorted(Comparator.comparingLong(Star::getDistance)).limit(size).toList();
    }

    Map<Long, Integer> getNumberOfStarsByDistances(List<Star> stars) {
        return Map.of();
    }

    Collection<Star> getUniqueStars(Collection<Star> stars) {

        return stars;
    }
}
