package com.epam.training.ticketservice.core.account;

import java.util.Optional;

import com.epam.training.ticketservice.core.account.persistence.Account;

public interface AccountService {

    boolean signIn(String username, String password);

    void signOut();

    Optional<Account> getSignedInAccount();

}
