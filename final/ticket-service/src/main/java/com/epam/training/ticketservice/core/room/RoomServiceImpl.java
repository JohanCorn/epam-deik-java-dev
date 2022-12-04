package com.epam.training.ticketservice.core.room;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.epam.training.ticketservice.core.room.persistence.Room;
import com.epam.training.ticketservice.core.room.persistence.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    @Override
    public Room create(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public Optional<Room> get(String title) {
        return roomRepository.findById(title);
    }

    @Override
    public List<Room> list() {
        return StreamSupport.stream(roomRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public void delete(Room room) {
        roomRepository.delete(room);
    }

}
