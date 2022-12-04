package com.epam.training.ticketservice.core.move;

import static org.mockito.Mockito.mock;

import java.util.List;
import java.util.Optional;

import com.epam.training.ticketservice.core.movie.MovieService;
import com.epam.training.ticketservice.core.movie.MovieServiceImpl;
import com.epam.training.ticketservice.core.movie.persistence.Movie;
import com.epam.training.ticketservice.core.movie.persistence.MovieRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class MovieServiceImplTest {

    private static final Movie MOVIE = new Movie("The Movie", "The Genre", 30);

    private final MovieRepository movieRepository = mock(MovieRepository.class);

    private final MovieService underTest = new MovieServiceImpl(movieRepository);

    @Test
    void testCreateShouldStoreTheGivenMovieWhenInputIsValid() {
        // Given
        Mockito.when(movieRepository.save(MOVIE))
                .thenReturn(MOVIE);

        // When
        underTest.create(MOVIE);

        // Then
        Mockito.verify(movieRepository).save(MOVIE);
    }

    @Test
    void testGetShouldReturnOptionalMovieWhenInputIsValid() {
        // Given
        Mockito.when(movieRepository.findById(MOVIE.getTitle()))
                .thenReturn(Optional.of(MOVIE));

        // When
        Optional<Movie> actual = underTest.get("The Movie");
        Optional<Movie> expected = Optional.of(MOVIE);

        // Then
        Assertions.assertEquals(expected, actual);
        Mockito.verify(movieRepository).findById(MOVIE.getTitle());
    }

    @Test
    void testGetShouldReturnEmptyOptionalWhenInputIsNotValid() {
        // Given
        Mockito.when(movieRepository.findById("dummy"))
                .thenReturn(Optional.empty());

        // When
        Optional<Movie> actual = underTest.get("dummy");
        Optional<Movie> expected = Optional.empty();

        // Then
        Assertions.assertTrue(actual.isEmpty());
        Assertions.assertEquals(expected, actual);
        Mockito.verify(movieRepository).findById("dummy");
    }

    @Test
    void testListShouldReturnAStaticListOfMovies() {
        // Given
        Mockito.when(movieRepository.findAll())
                .thenReturn(List.of(MOVIE));

        // When
        List<Movie> actual = underTest.list();
        List<Movie> expected = List.of(MOVIE);

        // Then
        Assertions.assertEquals(expected, actual);
        Mockito.verify(movieRepository).findAll();
    }

    @Test
    void testUpdateShouldUpdateTheGivenMovieWhenInputIsValid() {
        // Given
        Mockito.when(movieRepository.findById(MOVIE.getTitle()))
                .thenReturn(Optional.of(MOVIE));

        // When
        underTest.update(MOVIE);
        Optional<Movie> actual = underTest.get(MOVIE.getTitle());
        Optional<Movie> expected = Optional.of(MOVIE);

        // Then
        Assertions.assertEquals(expected, actual);
        Mockito.verify(movieRepository).findById(MOVIE.getTitle());
    }

    @Test
    void testDeleteShouldDeleteTheGivenMovieWhenInputIsValid() {
        // Given
        Mockito.when(movieRepository.findById(MOVIE.getTitle()))
                .thenReturn(Optional.empty());

        // When
        underTest.delete(MOVIE);
        Optional<Movie> actual = underTest.get(MOVIE.getTitle());
        Optional<Movie> expected = Optional.empty();

        // Then
        Assertions.assertEquals(expected, actual);
        Mockito.verify(movieRepository).findById(MOVIE.getTitle());
    }

}
