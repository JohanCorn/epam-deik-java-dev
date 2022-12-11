package com.epam.training.ticketservice.core.screening;

import com.epam.training.ticketservice.core.movie.persistence.Movie;
import com.epam.training.ticketservice.core.room.persistence.Room;
import com.epam.training.ticketservice.core.screening.persistence.Screening;
import com.epam.training.ticketservice.core.screening.persistence.ScreeningRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.mock;

class ScreeningServiceImplTest {

    private static final Movie MOVIE = new Movie("The Movie", "The Genre", 30);

    private static final Room ROOM = new Room("The Room", 10, 10);

    private static final Screening SCREENING = new Screening(MOVIE, ROOM, new Date());

    private final ScreeningRepository screeningRepository = mock(ScreeningRepository.class);

    private final ScreeningService underTest = new ScreeningServiceImpl(screeningRepository);

    @Test
    void testCreateShouldStoreTheGivenScreeningWhenInputIsValid() {
        // Given
        Mockito.when(screeningRepository.save(SCREENING))
                .thenReturn(SCREENING);

        // When
        underTest.create(SCREENING);

        // Then
        Mockito.verify(screeningRepository).save(SCREENING);
    }

    @Test
    void testListByRoomNameShouldReturnAStaticListOfScreenings() {
        // Given
        Mockito.when(screeningRepository.findByRoomName(ROOM.getName()))
                .thenReturn(List.of(SCREENING));

        // When
        List<Screening> actual = underTest.listByRoomName(ROOM.getName());
        List<Screening> expected = List.of(SCREENING);

        // Then
        Assertions.assertEquals(expected, actual);
        Mockito.verify(screeningRepository).findByRoomName(ROOM.getName());
    }

    @Test
    void testListByMovieTitleAndRoomNameAndStart() {
        // Given
        Mockito.when(screeningRepository.findByMovieTitleAndRoomNameAndStart(MOVIE.getTitle(), ROOM.getName(), SCREENING.getStart()))
                .thenReturn(List.of(SCREENING));

        // When
        List<Screening> actual = underTest.listByMovieTitleAndRoomNameAndStart(MOVIE.getTitle(), ROOM.getName(), SCREENING.getStart());
        List<Screening> expected = List.of(SCREENING);

        // Then
        Assertions.assertEquals(expected, actual);
        Mockito.verify(screeningRepository).findByMovieTitleAndRoomNameAndStart(MOVIE.getTitle(), ROOM.getName(), SCREENING.getStart());
    }

    @Test
    void testListShouldReturnAStaticListOfScreenings() {
        // Given
        Mockito.when(screeningRepository.findAll())
                .thenReturn(List.of(SCREENING));

        // When
        List<Screening> actual = underTest.list();
        List<Screening> expected = List.of(SCREENING);

        // Then
        Assertions.assertEquals(expected, actual);
        Mockito.verify(screeningRepository).findAll();
    }

    @Test
    void testDeleteShouldDeleteTheGivenScreeningWhenInputIsValid() {
        // Given
        // When
        underTest.delete(SCREENING);

        // Then
        Assertions.assertTrue(true);
        Mockito.verify(screeningRepository).delete(SCREENING);
    }

}
