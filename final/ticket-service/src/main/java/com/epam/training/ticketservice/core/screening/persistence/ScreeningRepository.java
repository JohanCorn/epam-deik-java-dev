package com.epam.training.ticketservice.core.screening.persistence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ScreeningRepository extends CrudRepository<Screening, String> {
    List<Screening> findScreeningsByRoomName(String roomName);
}
