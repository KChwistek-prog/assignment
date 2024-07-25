package com.mytimeplan.assignment.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mytimeplan.assignment.repository.Star;
import com.mytimeplan.assignment.service.StarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StarController.class)
class StarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StarService starService;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        objectMapper = new ObjectMapper();
    }

    @Test
    void getStarById() throws Exception {
        Star star = new Star("Star A", 10L);
        star.setId(1L);

        Mockito.when(starService.getStarById(anyLong())).thenReturn(star);
        mockMvc.perform(get("/stars/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Star A"))
                .andExpect(jsonPath("$.distance").value(10));
    }

    @Test
    void addNewStar() throws Exception {
        Star star = new Star("Star A", 10L);
        star.setId(1L);

        Mockito.when(starService.addNewStar(any(Star.class))).thenReturn(star);

        mockMvc.perform(post("/stars")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(star)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Star A"))
                .andExpect(jsonPath("$.distance").value(10));
    }

    @Test
    void updateStar() throws Exception {
        Star star = new Star("Star A", 10L);
        star.setId(1L);

        Mockito.when(starService.updateStar(anyLong(), any(Star.class))).thenReturn(star);

        mockMvc.perform(put("/stars/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(star)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Star A"))
                .andExpect(jsonPath("$.distance").value(10));
    }

    @Test
    public void testDeleteStar() throws Exception {
        Mockito.doNothing().when(starService).deleteStar(anyLong());

        mockMvc.perform(delete("/stars/{id}", 1L))
                .andExpect(status().isNoContent());
    }

    @Test
    void findClosestStars() throws Exception {
        Star star1 = new Star("Star A", 5L);
        star1.setId(1L);


        Star star2 = new Star("Star B", 3L);
        star2.setId(2L);

        List<Star> stars = Arrays.asList(star1, star2);

        Mockito.when(starService.findClosestStars(any(List.class), anyInt())).thenReturn(stars);

        mockMvc.perform(post("/stars/closest?size=2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(stars)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].name").value("Star A"))
                .andExpect(jsonPath("$[0].distance").value(5.0))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].name").value("Star B"))
                .andExpect(jsonPath("$[1].distance").value(3.0));
    }

    @Test
    void getNumberOfStarsByDistances() throws Exception {
        Star star1 = new Star("Star A", 50L);
        star1.setId(1L);


        Star star2 = new Star("Star B", 51L);
        star2.setId(2L);

        List<Star> stars = Arrays.asList(star1, star2);

        Map<Long, Integer> distancesMap = Map.of(5L, 2);

        Mockito.when(starService.getNumberOfStarsByDistances(any(List.class))).thenReturn(distancesMap);

        mockMvc.perform(post("/stars/distances")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(stars)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.5").value(2));
    }

    @Test
    void getUniqueStars() throws Exception {
        Star star1 = new Star("Star A", 5L);
        star1.setId(1L);

        Star star2 = new Star("Star B", 3L);
        star2.setId(2L);


        Collection<Star> stars = Arrays.asList(star1, star2);

        Mockito.when(starService.getUniqueStars(any(Collection.class))).thenReturn(stars);

        mockMvc.perform(post("/stars/unique")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(stars)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].name").value("Star A"))
                .andExpect(jsonPath("$[0].distance").value(5.0))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].name").value("Star B"))
                .andExpect(jsonPath("$[1].distance").value(3.0));
    }
}