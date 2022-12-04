package com.epam.training.ticketservice.ui.command;

import java.util.stream.Collectors;

import com.epam.training.ticketservice.core.account.AccountService;
import com.epam.training.ticketservice.core.room.RoomService;
import com.epam.training.ticketservice.core.room.persistence.Room;
import lombok.AllArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

@ShellComponent
@AllArgsConstructor
public class RoomCommand {

    private final AccountService accountService;

    private final RoomService roomService;

    @ShellMethod(value = "Create Room", key = "create room")
    @ShellMethodAvailability(value = "isSignedIn")
    public String createRoom(String name, int rowsNum, int columnsNum) {
        return roomService.get(name)
                .map(room -> "Room already exists")
                .orElse("Room created: " + roomService.create(new Room(name, rowsNum, columnsNum)));
    }

    @ShellMethod(value = "Update Room", key = "update room")
    @ShellMethodAvailability(value = "isSignedIn")
    public String updateRoom(String name, int rowsNum, int columnsNum) {
        return roomService.get(name)
                .map(room -> {
                    room.setRowsNum(rowsNum);
                    room.setColumnsNum(columnsNum);
                    roomService.update(room);
                    return "Room updated: " + room;
                })
                .orElse("No such room");
    }

    @ShellMethod(value = "Delete Room", key = "delete room")
    @ShellMethodAvailability(value = "isSignedIn")
    public String deleteRoom(String name) {
        return roomService.get(name)
                .map(room -> {
                    roomService.delete(room);
                    return "Room deleted: " + room;
                })
                .orElse("No such room");
    }

    @ShellMethod(value = "List Rooms", key = "list rooms")
    public String listRooms() {
        return roomService.list()
                .stream()
                .map(Room::toString)
                .collect(Collectors.collectingAndThen(Collectors.joining("\n"),
                        rooms -> rooms.isEmpty() ? "There are no rooms at the moment" : rooms));
    }

    private Availability isSignedIn() {
        return accountService.getSignedInAccount()
                .isEmpty() ? Availability.unavailable("You are not signed in") : Availability.available();
    }
}
