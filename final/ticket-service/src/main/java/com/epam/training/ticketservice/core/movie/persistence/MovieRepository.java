package com.epam.training.ticketservice.core.movie.persistence;

import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie, String> {
}
