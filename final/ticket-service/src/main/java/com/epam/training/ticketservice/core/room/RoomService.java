package com.epam.training.ticketservice.core.room;

import java.util.List;
import java.util.Optional;

import com.epam.training.ticketservice.core.room.persistence.Room;

public interface RoomService {

    Room create(Room room);

    Optional<Room> get(String name);

    List<Room> list();

    void delete(Room room);

}
