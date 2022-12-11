package com.epam.training.ticketservice.core.move.persistence;

import com.epam.training.ticketservice.core.movie.persistence.Movie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MovieTest {

    private static final Movie underTest = new Movie("The Movie", "The Genre", 30);

    @Test
    void testToStringShouldReturnTheFormattedData() {
        // Given
        // When
        // Then
        Assertions.assertEquals(underTest.getTitle() + " (" + underTest.getGenre() + ", " + underTest.getDuration() + " minutes)", underTest.toString());
    }

}
