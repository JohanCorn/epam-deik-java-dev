package com.epam.training.ticketservice.ui.command;

import com.epam.training.ticketservice.core.screening.ScreeningService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.Shell;
import org.springframework.shell.result.DefaultResultHandler;

@SpringBootTest(properties = {"spring.shell.interactive.enabled=false"})
class ScreeningCommandTest {

    @Autowired
    private Shell shell;

    @Autowired
    private DefaultResultHandler defaultResultHandler;

    @Autowired
    private ScreeningService screeningService;

    @Test
    void testCreateShouldStoreTheGivenRoomWhenInputIsValid() {
        // Given
        defaultResultHandler.handleResult(shell.evaluate(() -> "sign in privileged admin admin"));
        defaultResultHandler.handleResult(shell.evaluate(() -> "create movie The_Movie The_Genre 30"));
        defaultResultHandler.handleResult(shell.evaluate(() -> "create room The_Room 10 10"));

        // When
        defaultResultHandler.handleResult(shell.evaluate(() -> "create screening The_Room The_Room \"2022-12-12 12:00\""));

        // Then
        Assertions.assertEquals(0, screeningService.list().size());
        defaultResultHandler.handleResult(shell.evaluate(() -> "delete movie The_Movie"));
        defaultResultHandler.handleResult(shell.evaluate(() -> "delete room The_Room"));
    }

    @Test
    void testListShouldReturnAStaticListWhenNotEmpty() {
        // Given
        defaultResultHandler.handleResult(shell.evaluate(() -> "sign in privileged admin admin"));
        defaultResultHandler.handleResult(shell.evaluate(() -> "create room The_Room 10 10"));
        defaultResultHandler.handleResult(shell.evaluate(() -> "create screening The_Room The_Room \"2022-12-12 12:00\""));

        // When
        defaultResultHandler.handleResult(shell.evaluate(() -> "list screenings"));

        // Then
        Assertions.assertEquals(0, screeningService.list().size());
        defaultResultHandler.handleResult(shell.evaluate(() -> "delete room The_Room"));
    }

    @Test
    void testListShouldReturnAStaticListWhenEmpty() {
        // Given
        defaultResultHandler.handleResult(shell.evaluate(() -> "sign in privileged admin admin"));

        // When
        defaultResultHandler.handleResult(shell.evaluate(() -> "list screenings"));

        // Then
        Assertions.assertEquals(0, screeningService.list().size());
    }

    @Test
    void testDeleteShouldDeleteTheGivenRoomWhenInputIsValid() {
        // Given
        defaultResultHandler.handleResult(shell.evaluate(() -> "sign in privileged admin admin"));
        defaultResultHandler.handleResult(shell.evaluate(() -> "create movie The_Movie The_Genre 30"));
        defaultResultHandler.handleResult(shell.evaluate(() -> "create room The_Room 10 10"));
        defaultResultHandler.handleResult(shell.evaluate(() -> "create screening The_Room The_Room \"2022-12-12 12:00\""));

        // When
        defaultResultHandler.handleResult(shell.evaluate(() -> "delete screening The_Room The_Room \"2022-12-12 12:00\""));

        // Then
        Assertions.assertEquals(0, screeningService.list().size());
        defaultResultHandler.handleResult(shell.evaluate(() -> "delete movie The_Movie"));
        defaultResultHandler.handleResult(shell.evaluate(() -> "delete room The_Room"));
    }
}
