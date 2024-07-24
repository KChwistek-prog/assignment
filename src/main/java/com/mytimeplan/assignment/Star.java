package com.mytimeplan.assignment;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


import java.util.Objects;

@Entity
public class Star {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String Name;
    private Long Distance;

    public Star(String name, Long distance) {
        Name = name;
        Distance = distance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Long getDistance() {
        return Distance;
    }

    public void setDistance(Long distance) {
        Distance = distance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Star star = (Star) o;
        return Objects.equals(Name, star.Name) && Objects.equals(Distance, star.Distance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Name, Distance);
    }

    @Override
    public String toString() {
        return "Star{" +
                "Name='" + Name + '\'' +
                ", Distance=" + Distance +
                '}';
    }
}
