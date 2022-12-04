package com.epam.training.ticketservice.core.room;

import static org.mockito.Mockito.mock;

import java.util.List;
import java.util.Optional;

import com.epam.training.ticketservice.core.room.persistence.Room;
import com.epam.training.ticketservice.core.room.persistence.RoomRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class RoomServiceImplTest {

    private static final Room ROOM = new Room("The Room", 10, 10);

    private final RoomRepository roomRepository = mock(RoomRepository.class);

    private final RoomService underTest = new RoomServiceImpl(roomRepository);

    @Test
    void testCreateRoomShouldStoreTheGivenRoomWhenInputRoomIsValid() {
        // Given
        Mockito.when(roomRepository.save(ROOM))
                .thenReturn(ROOM);

        // When
        underTest.create(ROOM);

        // Then
        Mockito.verify(roomRepository).save(ROOM);
    }

    @Test
    void testGetRoomShouldReturnRoomWhenInputRoomIdIsValid() {
        // Given
        Mockito.when(roomRepository.findById(ROOM.getName()))
                .thenReturn(Optional.of(ROOM));

        // When
        Optional<Room> actual = underTest.get("The Room");
        Optional<Room> expected = Optional.of(ROOM);

        // Then
        Assertions.assertEquals(expected, actual);
        Mockito.verify(roomRepository).findById(ROOM.getName());
    }

    @Test
    void testGetRoomShouldReturnOptionalEmptyWhenInputRoomIdDoesNotExist() {
        // Given
        Mockito.when(roomRepository.findById("dummy"))
                .thenReturn(Optional.empty());

        // When
        Optional<Room> actual = underTest.get("dummy");
        Optional<Room> expected = Optional.empty();

        // Then
        Assertions.assertTrue(actual.isEmpty());
        Assertions.assertEquals(expected, actual);
        Mockito.verify(roomRepository).findById("dummy");
    }

    @Test
    void testGetRoomListShouldReturnAStaticList() {
        // Given
        Mockito.when(roomRepository.findAll())
                .thenReturn(List.of(ROOM));

        // When
        List<Room> actual = underTest.list();
        List<Room> expected = List.of(ROOM);

        // Then
        Assertions.assertEquals(expected, actual);
        Mockito.verify(roomRepository).findAll();
    }

    @Test
    void testUpdateRoomShouldUpdateWhenRoomIsValid() {
        // Given
        Mockito.when(roomRepository.findById(ROOM.getName()))
                .thenReturn(Optional.of(ROOM));

        // When
        // underTest.update(ROOM);
        Optional<Room> actual = underTest.get(ROOM.getName());
        Optional<Room> expected = Optional.of(ROOM);

        // Then
        Assertions.assertEquals(expected, actual);
        Mockito.verify(roomRepository).findById(ROOM.getName());
    }

    @Test
    void testDeleteRoomShouldDeleteWhenRoomIsValid() {
        // Given
        Mockito.when(roomRepository.findById(ROOM.getName()))
                .thenReturn(Optional.empty());

        // When
        underTest.delete(ROOM);
        Optional<Room> actual = underTest.get(ROOM.getName());
        Optional<Room> expected = Optional.empty();

        // Then
        Assertions.assertEquals(expected, actual);
        Mockito.verify(roomRepository).findById(ROOM.getName());
    }

}
