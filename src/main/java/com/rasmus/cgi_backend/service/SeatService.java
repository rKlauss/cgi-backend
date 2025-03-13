package com.rasmus.cgi_backend.service;

import com.rasmus.cgi_backend.model.Seat;
import com.rasmus.cgi_backend.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatService {
    private final SeatRepository seatRepository;

    // Soovituste põhjal istekohtade valimine
    public List<Seat> recommendSeats(Long flightId, boolean isWindow, boolean hasExtraLegroom, boolean isNearExit, int count) {
        if (!isWindow && !hasExtraLegroom && !isNearExit) {
            return Collections.emptyList();
        }
    
        return seatRepository.findByFlightIdAndIsOccupiedFalse(flightId).stream()
                .filter(seat -> (!isWindow || seat.isWindow()) &&
                                (!hasExtraLegroom || seat.isHasExtraLegroom()) &&
                                (!isNearExit || seat.isNearExit()))
                .toList();
    }
       
    

    public List<Seat> getSeatPlan(Long flightId) {
        return seatRepository.findByFlightId(flightId);
    }

    public boolean bookSeats(List<Long> seatIds) {
        List<Seat> seats = seatRepository.findAllById(seatIds);
        if (seats.stream().anyMatch(Seat::isOccupied)) {
            return false; 
        }
        seats.forEach(seat -> seat.setOccupied(true));
        seatRepository.saveAll(seats);
        return true;
    }
}
