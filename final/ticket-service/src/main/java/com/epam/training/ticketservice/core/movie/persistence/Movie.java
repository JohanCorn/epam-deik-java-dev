package com.epam.training.ticketservice.core.movie.persistence;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Movie {

    @Id
    private String title;

    private String genre;

    private int duration;

    public Movie(String title, String genre, int duration) {
        this.title = title;
        this.genre = genre;
        this.duration = duration;
    }

    @Override
    public String toString() {
        return title + " (" + genre + ", " + duration + " minutes)";
    }

}
