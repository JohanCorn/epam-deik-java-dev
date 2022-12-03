package com.epam.training.ticketservice.core.movie;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.epam.training.ticketservice.core.movie.persistence.Movie;
import com.epam.training.ticketservice.core.movie.persistence.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    @Override
    public Movie create(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public Optional<Movie> get(String title) {
        return movieRepository.findById(title);
    }

    @Override
    public List<Movie> list() {
        return StreamSupport.stream(movieRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public void update(Movie movie) {
        movieRepository.save(movie);
    }

    @Override
    public void delete(Movie movie) {
        movieRepository.delete(movie);
    }

}
