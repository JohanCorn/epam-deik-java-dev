package com.epam.training.ticketservice.ui.command;

import java.util.stream.Collectors;

import com.epam.training.ticketservice.core.account.AccountService;
import com.epam.training.ticketservice.core.movie.MovieService;
import com.epam.training.ticketservice.core.movie.persistence.Movie;
import lombok.AllArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

@ShellComponent
@AllArgsConstructor
public class MovieCommand {

    private final AccountService accountService;

    private final MovieService movieService;

    @ShellMethod(value = "Create Movie", key = "create movie")
    @ShellMethodAvailability(value = "isSignedIn")
    public String createMovie(String title, String genre, int duration) {
        return movieService.get(title)
                .map(movie -> "Movie already exists")
                .orElse("Movie created: " + movieService.create(new Movie(title, genre, duration)));
    }

    @ShellMethod(value = "Update Movie", key = "update movie")
    @ShellMethodAvailability(value = "isSignedIn")
    public String updateMovie(String title, String genre, int duration) {
        return movieService.get(title)
                .map(movie -> {
                    movie.setGenre(genre);
                    movie.setDuration(duration);
                    movieService.update(movie);
                    return "Movie updated: " + movie;
                })
                .orElse("No such movie");
    }

    @ShellMethod(value = "Delete Movie", key = "delete movie")
    @ShellMethodAvailability(value = "isSignedIn")
    public String deleteMovie(String title) {
        return movieService.get(title)
                .map(movie -> {
                    movieService.delete(movie);
                    return "Movie deleted: " + movie;
                })
                .orElse("No such movie");
    }

    @ShellMethod(value = "List Movies", key = "list movies")
    public String listMovies() {
        return movieService.list()
                .stream()
                .map(Movie::toString)
                .collect(Collectors.collectingAndThen(Collectors.joining("\n"),
                        movies -> movies.isEmpty() ? "There are no movies at the moment" : movies));
    }

    private Availability isSignedIn() {
        return accountService.getSignedInAccount()
                .isEmpty() ? Availability.unavailable("You are not signed in") : Availability.available();
    }
}
