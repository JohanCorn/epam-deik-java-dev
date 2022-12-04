package com.epam.training.ticketservice.ui.command;

import com.epam.training.ticketservice.core.room.RoomService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.Shell;
import org.springframework.shell.result.DefaultResultHandler;

@SpringBootTest(properties = {"spring.shell.interactive.enabled=false"})
class RoomCommandTest {

    @Autowired
    private Shell shell;

    @Autowired
    private DefaultResultHandler defaultResultHandler;

    @Autowired
    private RoomService roomService;

    @Test
    void testCreateShouldStoreTheGivenRoomWhenInputIsValid() {
        // Given
        defaultResultHandler.handleResult(shell.evaluate(() -> "sign in privileged admin admin"));

        // When
        defaultResultHandler.handleResult(shell.evaluate(() -> "create room The_Room 10 10"));

        // Then
        Assertions.assertEquals(1, roomService.list().size());
        defaultResultHandler.handleResult(shell.evaluate(() -> "delete room The_Room"));
    }

    @Test
    void testListShouldReturnAStaticListWhenNotEmpty() {
        // Given
        defaultResultHandler.handleResult(shell.evaluate(() -> "sign in privileged admin admin"));
        defaultResultHandler.handleResult(shell.evaluate(() -> "create room The_Room 10 10"));

        // When
        defaultResultHandler.handleResult(shell.evaluate(() -> "list rooms"));

        // Then
        Assertions.assertEquals(1, roomService.list().size());
        defaultResultHandler.handleResult(shell.evaluate(() -> "delete room The_Room"));
    }

    @Test
    void testListShouldReturnAStaticListWhenEmpty() {
        // Given
        defaultResultHandler.handleResult(shell.evaluate(() -> "sign in privileged admin admin"));

        // When
        defaultResultHandler.handleResult(shell.evaluate(() -> "list rooms"));

        // Then
        Assertions.assertEquals(0, roomService.list().size());
    }

    @Test
    void testUpdateShouldUpdateTheGivenRoomWhenInputIsValid() {
        // Given
        defaultResultHandler.handleResult(shell.evaluate(() -> "sign in privileged admin admin"));
        defaultResultHandler.handleResult(shell.evaluate(() -> "create room The_Room 10 10"));

        // When
        defaultResultHandler.handleResult(shell.evaluate(() -> "update room The_Room 10 20"));

        // Then
        Assertions.assertEquals(20, roomService.get("The_Room").get().getColumnsNum());
        defaultResultHandler.handleResult(shell.evaluate(() -> "delete room The_Room"));
    }

    @Test
    void testDeleteShouldDeleteTheGivenRoomWhenInputIsValid() {
        // Given
        defaultResultHandler.handleResult(shell.evaluate(() -> "sign in privileged admin admin"));
        defaultResultHandler.handleResult(shell.evaluate(() -> "create room The_Room 10 10"));

        // When
        defaultResultHandler.handleResult(shell.evaluate(() -> "delete room The_Room"));

        // Then
        Assertions.assertFalse(roomService.get("The_Room").isPresent());
    }
}
