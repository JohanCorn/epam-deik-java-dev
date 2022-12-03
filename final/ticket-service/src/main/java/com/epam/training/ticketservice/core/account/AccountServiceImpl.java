package com.epam.training.ticketservice.core.account;

import java.util.Optional;

import com.epam.training.ticketservice.core.account.persistence.Account;
import com.epam.training.ticketservice.core.account.persistence.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    private Account signedInAccount;

    @Override
    public boolean signIn(String username, String password) {
        accountRepository.findByUsernameAndPassword(username, password)
                .ifPresent(account -> signedInAccount = account);

        return signedInAccount != null;
    }

    @Override
    public void signOut() {
        this.signedInAccount = null;
    }

    @Override
    public Optional<Account> getSignedInAccount() {
        return Optional.ofNullable(signedInAccount);
    }

}
