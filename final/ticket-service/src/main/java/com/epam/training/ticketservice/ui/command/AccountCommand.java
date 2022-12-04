package com.epam.training.ticketservice.ui.command;

import com.epam.training.ticketservice.core.account.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@AllArgsConstructor
public class AccountCommand {

    private final AccountService accountService;

    @ShellMethod(key = "sign in privileged", value = "Sign In Privileged")
    public String signInPrivileged(String username, String password) {
        return accountService.signIn(username, password) ? "You just signed in"
                : "Login failed due to incorrect credentials";
    }

    @ShellMethod(key = "sign out", value = "Sign Out")
    public void signInPrivileged() {
        accountService.signOut();
    }

    @ShellMethod(key = "describe account", value = "Describe Account")
    public String describeAccount() {
        return accountService.getSignedInAccount()
                .map(account -> "Signed in with privileged account '" + account.getUsername() + "'")
                .orElse("You are not signed in");
    }

    //    private Availability isSignedIn() {
    //        return accountService.getSignedInAccount()
    //                .isEmpty() ? Availability.unavailable("You are not signed in") : Availability.available();
    //    }

}
