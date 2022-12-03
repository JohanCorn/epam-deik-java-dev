package com.epam.training.ticketservice.ui.command;

import java.util.Optional;

import com.epam.training.ticketservice.core.account.AccountService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.Shell;
import org.springframework.shell.result.DefaultResultHandler;

@SpringBootTest(properties = { "spring.shell.interactive.enabled=false" })
class AccountCommandTest {

    @Autowired
    private Shell shell;

    @Autowired
    private DefaultResultHandler defaultResultHandler;

    @Autowired
    private AccountService accountService;

    @Test
    void testSignInPrivilegedShouldSetSignedInAccountWhenUsernameAndPasswordAreCorrect() {
        // Given
        // When
        defaultResultHandler.handleResult(shell.evaluate(() -> "sign in privileged admin admin"));

        // Then
        Assertions.assertNotEquals(Optional.empty(), accountService.getSignedInAccount());
    }

    @Test
    void testSignInPrivilegedShouldNotSetSignedInAccountWhenUsernameAndPasswordAreNotCorrect() {
        // Given
        defaultResultHandler.handleResult(shell.evaluate(() -> "sign out"));

        // When
        defaultResultHandler.handleResult(shell.evaluate(() -> "sign in privileged admin 12345"));

        // Then
        Assertions.assertEquals(Optional.empty(), accountService.getSignedInAccount());
    }

    @Test
    void testSignOutShouldSetSignedInAccountToEmptyOptional() {
        // Given
        defaultResultHandler.handleResult(shell.evaluate(() -> "sign in privileged admin admin"));

        // When
        defaultResultHandler.handleResult(shell.evaluate(() -> "sign out"));

        // Then
        Assertions.assertEquals(Optional.empty(), accountService.getSignedInAccount());
    }

    @Test
    void testGetSignedInAccountShouldNotReturnEmptyOptionWhenSignedIn() {
        // Given
        defaultResultHandler.handleResult(shell.evaluate(() -> "sign in privileged admin admin"));

        // When
        // Then
        Assertions.assertNotEquals(Optional.empty(), accountService.getSignedInAccount());
    }

    @Test
    void testGetSignedInAccountShouldReturnEmptyOptionWhenNotSignedIn() {
        // Given
        defaultResultHandler.handleResult(shell.evaluate(() -> "sign out"));

        // When
        // Then
        Assertions.assertEquals(Optional.empty(), accountService.getSignedInAccount());
    }

    @Test
    void testDescribeAccountShouldDescribeAccountWhenSignedIn() {
        // Given
        defaultResultHandler.handleResult(shell.evaluate(() -> "sign in privileged admin admin"));

        // When
        defaultResultHandler.handleResult(shell.evaluate(() -> "describe account"));

        // Then
        Assertions.assertNotEquals(Optional.empty(), accountService.getSignedInAccount());
    }


    @Test
    void testDescribeAccountShouldNotDescribeAccountWhenSignedOut() {
        // Given
        defaultResultHandler.handleResult(shell.evaluate(() -> "sign out"));

        // When
        defaultResultHandler.handleResult(shell.evaluate(() -> "describe account"));

        // Then
        Assertions.assertEquals(Optional.empty(), accountService.getSignedInAccount());
    }
}
