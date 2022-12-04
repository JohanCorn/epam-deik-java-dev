package com.epam.training.ticketservice.core.screening;

import java.util.Date;
import java.util.List;

import com.epam.training.ticketservice.core.screening.persistence.Screening;

public interface ScreeningService {

    Screening create(Screening screen);

    List<Screening> listByRoomName(String roomName);

    List<Screening> listByMovieTitleAndRoomNameAndStart(String movieTitle, String roomName, Date start);

    List<Screening> list();

    void delete(Screening screen);

}
