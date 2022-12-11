package com.epam.training.ticketservice.core.screening.persistence;

import com.epam.training.ticketservice.core.movie.persistence.Movie;
import com.epam.training.ticketservice.core.room.persistence.Room;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreeningTest {

    private static final Movie MOVIE = new Movie("The Movie", "The Genre", 30);

    private static final Room ROOM = new Room("The Room", 10, 10);

    private static final Screening underTest = new Screening(MOVIE, ROOM, new Date());

    @Test
    void testGetEndShouldReturnStartPlusThirtyMinutesWhenTheMovieDurationIsThirtyMinutes() {
        // Given
        // When
        // Then
        Assertions.assertEquals(new Date(underTest.getStart().getTime() + underTest.getMovie().getDuration() * 60_000L), underTest.getEnd());
    }

    @Test
    void testGetDateFormatShouldReturnTheExactDateFormat() {
        // Given
        // When
        // Then
        Assertions.assertEquals("yyyy-MM-dd hh:mm", Screening.getDateFormat());
    }

    @Test
    void testToStringShouldReturnTheFormattedData() {
        // Given
        // When
        // Then
        Assertions.assertEquals(underTest.getMovie() + ", screened in room " + underTest.getRoom().getName() + ", at "
                + new SimpleDateFormat(Screening.getDateFormat()).format(underTest.getStart()), underTest.toString());
    }

}
