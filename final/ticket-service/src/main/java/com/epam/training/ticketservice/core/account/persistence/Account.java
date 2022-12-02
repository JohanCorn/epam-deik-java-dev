package com.epam.training.ticketservice.account.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Account {
    @Id
    private String username;

    private String password;

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
