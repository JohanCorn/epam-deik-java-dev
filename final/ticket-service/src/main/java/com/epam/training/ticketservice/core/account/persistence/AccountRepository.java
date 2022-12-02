package com.epam.training.ticketservice.account.repository;

import java.util.Optional;

import com.epam.training.ticketservice.account.entity.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, String> {
    Optional<Account> findAccountByUsernameAndPassword(String username, String password);
}
