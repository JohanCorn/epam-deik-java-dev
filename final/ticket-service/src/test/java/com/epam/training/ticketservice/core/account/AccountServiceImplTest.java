package com.epam.training.ticketservice.core.account;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.epam.training.ticketservice.core.account.persistence.Account;
import com.epam.training.ticketservice.core.account.persistence.AccountRepository;
import org.junit.jupiter.api.Test;

class AccountServiceImplTest {

    private final AccountRepository accountRepository = mock(AccountRepository.class);

    private final AccountService underTest = new AccountServiceImpl(accountRepository);

    @Test
    void testSignInShouldReturnTrueWhenUsernameAndPasswordAreCorrect() {
        // Given
        Account account = new Account("user", "password");
        when(accountRepository.findByUsernameAndPassword(account.getUsername(), account.getPassword()))
                .thenReturn(Optional.of(account));

        // When
        boolean isSignedIn = underTest.signIn(account.getUsername(), account.getPassword());

        // Then
        assertTrue(isSignedIn);
        verify(accountRepository).findByUsernameAndPassword(account.getUsername(), account.getPassword());
    }

    @Test
    void testSignInShouldReturnFalseWhenUsernameOrPasswordAreNotCorrect() {
        // Given
        Account account = new Account("user", "password");
        when(accountRepository.findByUsernameAndPassword(account.getUsername(), account.getPassword()))
                .thenReturn(Optional.empty());

        // When
        boolean isSignedIn = underTest.signIn(account.getUsername(), account.getPassword());

        // Then
        assertFalse(isSignedIn);
        verify(accountRepository).findByUsernameAndPassword(account.getUsername(), account.getPassword());
    }

    @Test
    void testSignOutShouldSetSignedInAccountToEmptyOptional() {
        // Given
        Account account = new Account("user", "password");
        when(accountRepository.findByUsernameAndPassword(account.getUsername(), account.getPassword()))
                .thenReturn(Optional.of(account));

        // When
        underTest.signIn(account.getUsername(), account.getPassword());
        underTest.signOut();

        assertEquals(Optional.empty(), underTest.getSignedInAccount());
        verify(accountRepository).findByUsernameAndPassword(account.getUsername(), account.getPassword());
    }

}
