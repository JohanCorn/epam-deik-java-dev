package com.epam.training.ticketservice.core.room;

import com.epam.training.ticketservice.core.room.persistence.Room;

import java.util.List;
import java.util.Optional;

public interface RoomService {

    Room create(Room room);

    Optional<Room> get(String name);

    List<Room> list();

    void update(Room room);

    void delete(Room room);

}
