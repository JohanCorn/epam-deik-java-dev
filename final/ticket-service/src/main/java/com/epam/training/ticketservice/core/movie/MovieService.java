package com.epam.training.ticketservice.core.movie;

import java.util.List;
import java.util.Optional;

import com.epam.training.ticketservice.core.movie.persistence.Movie;

public interface MovieService {

    Movie create(Movie movie);

    Optional<Movie> get(String title);

    List<Movie> list();

    void update(Movie movie);

    void delete(Movie movie);

}
