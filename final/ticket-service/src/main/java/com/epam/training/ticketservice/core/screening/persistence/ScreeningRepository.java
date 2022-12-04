package com.epam.training.ticketservice.core.screening.persistence;

import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface ScreeningRepository extends CrudRepository<Screening, String> {

    List<Screening> findByRoomName(String roomName);

    List<Screening> findByMovieTitleAndRoomNameAndStart(String movieTitle, String roomName, Date start);
}
