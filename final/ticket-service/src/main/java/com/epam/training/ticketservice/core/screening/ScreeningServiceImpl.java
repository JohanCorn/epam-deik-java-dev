package com.epam.training.ticketservice.core.screening;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.epam.training.ticketservice.core.screening.persistence.Screening;
import com.epam.training.ticketservice.core.screening.persistence.ScreeningRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScreeningServiceImpl implements ScreeningService {

    private final ScreeningRepository screeningRepository;

    @Override
    public Screening create(Screening screen) {
        return screeningRepository.save(screen);
    }

    @Override
    public List<Screening> getByRoomName(String roomName) {
        return screeningRepository.findScreeningsByRoomName(roomName);
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
