package com.epam.training.ticketservice.core.screening;

import com.epam.training.ticketservice.core.screening.persistence.Screening;
import com.epam.training.ticketservice.core.screening.persistence.ScreeningRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class ScreeningServiceImpl implements ScreeningService {

    private final ScreeningRepository screeningRepository;

    @Override
    public Screening create(Screening screen) {
        return screeningRepository.save(screen);
    }

    @Override
    public List<Screening> listByRoomName(String roomName) {
        return screeningRepository.findByRoomName(roomName);
    }

    @Override
    public List<Screening> listByMovieTitleAndRoomNameAndStart(String movieTitle, String roomName, Date start) {
        return screeningRepository.findByMovieTitleAndRoomNameAndStart(movieTitle, roomName, start);
    }

    @Override
    public List<Screening> list() {
        return StreamSupport.stream(screeningRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public void delete(Screening screen) {
        screeningRepository.delete(screen);
    }

}
