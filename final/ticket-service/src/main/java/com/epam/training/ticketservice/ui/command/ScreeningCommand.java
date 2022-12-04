package com.epam.training.ticketservice.ui.command;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Collectors;

import com.epam.training.ticketservice.core.account.AccountService;
import com.epam.training.ticketservice.core.movie.MovieService;
import com.epam.training.ticketservice.core.room.RoomService;
import com.epam.training.ticketservice.core.screening.ScreeningService;
import com.epam.training.ticketservice.core.screening.persistence.Screening;
import lombok.AllArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

@ShellComponent
@AllArgsConstructor
public class ScreeningCommand {

    private final AccountService accountService;

    private final MovieService movieService;

    private final RoomService roomService;

    private final ScreeningService screeningService;

    @ShellMethod(value = "Create Screening", key = "create screening")
    @ShellMethodAvailability(value = "isSignedIn")
    public String createScreening(String movieTitle, String roomName, String start) throws ParseException {
        Date startDate = new SimpleDateFormat(Screening.getDateFormat()).parse(start);
        return movieService.get(movieTitle)
                .map(movie -> roomService.get(roomName)
                        .map(room -> {

                            Screening newScreening = new Screening(movie, room, startDate);

                            if (screeningService.getByRoomName(roomName)
                                    .stream()
                                    .anyMatch(screening -> screening.isOverlapping(newScreening))) {
                                return "There is an overlapping screening";
                            }

                            return "Screen created: " + screeningService.create(newScreening);
                        })
                        .orElse("No such room"))
                .orElse("No such movie");
    }

    @ShellMethod(value = "List Screenings", key = "list screenings")
    public String listScreenings() {
        return screeningService.list()
                .stream()
                .map(Screening::toString)
                .collect(Collectors.collectingAndThen(Collectors.joining("\n"),
                        screenings -> screenings.isEmpty() ? "There are no screenings" : screenings));
    }

    private Availability isSignedIn() {
        return accountService.getSignedInAccount()
                .isEmpty() ? Availability.unavailable("You are not signed in") : Availability.available();
    }
}
