package com.mytimeplan.assignment.service;

import com.mytimeplan.assignment.repository.Star;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class StarServiceTest {
    private final StarService starService = new StarService();


    public List<Star> prepareStars() {
        List<Star> stars = new ArrayList<>();
        stars.add(new Star("Barnard's Star", 56600000000000L));
        stars.add(new Star("Proxima Centauri", 40208000000000L));
        stars.add(new Star("Ross 248", 97720000000000L));
        stars.add(new Star("Procyon", 108460000000000L));
        stars.add(new Star("Luyten 726-8", 82700000000000L));
        stars.add(new Star("Lalande 21185", 78300000000000L));
        stars.add(new Star("Sirius", 81460000000000L));
        stars.add(new Star("Ross 128", 104290000000000L));
        stars.add(new Star("EZ Aquarii", 106490000000000L));
        stars.add(new Star("Wolf 359", 73640000000000L));
        stars.add(new Star("Alfa Centauri (Rigil Kentaurus)", 41320000000000L));
        stars.add(new Star("61 Cygni", 107580000000000L));
        stars.add(new Star("Ross 154", 91690000000000L));
        stars.add(new Star("Epsilon Eridani", 99480000000000L));
        stars.add(new Star("Lacaille 9352", 101400000000000L));
        return stars;
    }

    public List<Star> prepareNotUniqueStars() {
        List<Star> stars = new ArrayList<>();
        stars.add(new Star("Barnard's Star", 56600000000000L));
        stars.add(new Star("Proxima Centauri", 40208000000000L));
        stars.add(new Star("Proxima Centauri", 40208000000000L));
        stars.add(new Star("Ross 248", 97720000000000L));
        stars.add(new Star("Procyon", 108460000000000L));
        stars.add(new Star("Procyon", 108460000000000L));
        stars.add(new Star("Procyon", 108460000000000L));
        stars.add(new Star("Luyten 726-8", 82700000000000L));
        stars.add(new Star("Lalande 21185", 78300000000000L));
        stars.add(new Star("Lalande 21185", 78300000000000L));
        stars.add(new Star("Sirius", 81460000000000L));
        stars.add(new Star("Ross 128", 104290000000000L));
        stars.add(new Star("EZ Aquarii", 106490000000000L));
        stars.add(new Star("Wolf 359", 73640000000000L));
        stars.add(new Star("Wolf 359", 73640000000000L));
        return stars;
    }

    @Test
    void findClosestStars() {
        // Given
        List<Star> stars = prepareStars();

        // When
        var result = starService.findClosestStars(stars, 5);
        var result1 = starService.findClosestStars(stars, 3);
        var result2 = starService.findClosestStars(stars, 6);

        // Then
        assertEquals(5, result.size());
        assertEquals(3, result1.size());
        assertEquals(6, result2.size());
    }

    @Test
    void getNumberOfStarsByDistances() {
        // Given
        List<Star> stars = prepareStars();

        //When
        var result = starService.getNumberOfStarsByDistances(stars);

        //Then
        assertEquals(1, result.get(73640000000000L));
    }

    @Test
    void getUniqueStars() {
        // Given
        List<Star> stars = prepareNotUniqueStars();

        //When
        var result = starService.getUniqueStars(stars);
        System.out.println(result);
        //Then
        assertNotEquals(result, stars);
        assertEquals(10, result.size());
    }
}