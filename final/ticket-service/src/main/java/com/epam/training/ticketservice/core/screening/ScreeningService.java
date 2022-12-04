package com.epam.training.ticketservice.core.screening;

import java.util.List;

import com.epam.training.ticketservice.core.screening.persistence.Screening;

public interface ScreeningService {

    Screening create(Screening screen);

    List<Screening> getByRoomName(String roomName);

    List<Screening> list();

    void delete(Screening screen);

}
