package com.epam.training.ticketservice.ui.command;

import com.epam.training.ticketservice.core.account.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@AllArgsConstructor
public class AccountCommand {

    private final AccountService accountService;

    @ShellMethod(key = "sign in privileged", value = "Sign in Privileged")
    public String signInPrivileged() {
    }

}
