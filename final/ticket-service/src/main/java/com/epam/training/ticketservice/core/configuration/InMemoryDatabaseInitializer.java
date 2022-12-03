package com.epam.training.ticketservice.core.configuration;

import javax.annotation.PostConstruct;

import com.epam.training.ticketservice.core.account.persistence.Account;
import com.epam.training.ticketservice.core.account.persistence.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("!prod")
@RequiredArgsConstructor
public class InMemoryDatabaseInitializer {

    private final AccountRepository accountRepository;

    @PostConstruct
    public void initAccounts() {
        Account adminAccount = new Account("admin", "admin");
        accountRepository.save(adminAccount);
    }

}
