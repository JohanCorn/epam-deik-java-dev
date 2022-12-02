package com.epam.training.ticketservice.core.account;

import com.epam.training.ticketservice.account.entity.Account;
import com.epam.training.ticketservice.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    private Account signedInAccount;

    @Override
    public boolean signIn(String username, String password) {
        accountRepository.findAccountByUsernameAndPassword(username, password)
                .ifPresent(account -> signedInAccount = account);

        return signedInAccount != null;
    }

    @Override
    public void signOut() {
        this.signedInAccount = null;
    }

}
