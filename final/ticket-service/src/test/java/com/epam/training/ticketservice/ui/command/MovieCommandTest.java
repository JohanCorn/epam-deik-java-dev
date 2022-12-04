package com.epam.training.ticketservice.ui.command;

import com.epam.training.ticketservice.core.movie.MovieService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.Shell;
import org.springframework.shell.result.DefaultResultHandler;

@SpringBootTest(properties = {"spring.shell.interactive.enabled=false"})
class MovieCommandTest {

    @Autowired
    private Shell shell;

    @Autowired
    private DefaultResultHandler defaultResultHandler;

    @Autowired
    private MovieService movieService;

    @Test
    void testCreateShouldStoreTheGivenMovieWhenInputIsValid() {
        // Given
        defaultResultHandler.handleResult(shell.evaluate(() -> "sign in privileged admin admin"));

        // When
        defaultResultHandler.handleResult(shell.evaluate(() -> "create movie The_Movie The_Genre 30"));

        // Then
        Assertions.assertEquals(1, movieService.list().size());
        defaultResultHandler.handleResult(shell.evaluate(() -> "delete movie The_Movie"));
    }

    @Test
    void testListShouldReturnAStaticListWhenNotEmpty() {
        // Given
        defaultResultHandler.handleResult(shell.evaluate(() -> "sign in privileged admin admin"));
        defaultResultHandler.handleResult(shell.evaluate(() -> "create movie The_Movie The_Genre 30"));

        // When
        defaultResultHandler.handleResult(shell.evaluate(() -> "list movies"));

        // Then
        Assertions.assertEquals(1, movieService.list().size());
        defaultResultHandler.handleResult(shell.evaluate(() -> "delete movie The_Movie"));
    }

    @Test
    void testListShouldReturnAStaticListWhenEmpty() {
        // Given
        defaultResultHandler.handleResult(shell.evaluate(() -> "sign in privileged admin admin"));

        // When
        defaultResultHandler.handleResult(shell.evaluate(() -> "list movies"));

        // Then
        Assertions.assertEquals(0, movieService.list().size());
    }

    @Test
    void testUpdateShouldUpdateTheGivenMovieWhenInputIsValid() {
        // Given
        defaultResultHandler.handleResult(shell.evaluate(() -> "sign in privileged admin admin"));
        defaultResultHandler.handleResult(shell.evaluate(() -> "create movie The_Movie The_Genre 30"));

        // When
        defaultResultHandler.handleResult(shell.evaluate(() -> "update movie The_Movie The_Genre 45"));

        // Then
        Assertions.assertEquals(45, movieService.get("The_Movie").get().getDuration());
        defaultResultHandler.handleResult(shell.evaluate(() -> "delete movie The_Movie"));
    }

    @Test
    void testDeleteShouldDeleteTheGivenMovieWhenInputIsValid() {
        // Given
        defaultResultHandler.handleResult(shell.evaluate(() -> "sign in privileged admin admin"));
        defaultResultHandler.handleResult(shell.evaluate(() -> "create movie The_Movie The_Genre 30"));

        // When
        defaultResultHandler.handleResult(shell.evaluate(() -> "delete movie The_Movie"));

        // Then
        Assertions.assertFalse(movieService.get("The_Movie").isPresent());
    }
}
