package com.rasmus.cgi_backend.service;

import com.rasmus.cgi_backend.model.Seat;
import com.rasmus.cgi_backend.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SeatService {
    private final SeatRepository seatRepository;

    public List<Seat> recommendSeats(Long flightId, int numberOfTickets) {
        List<Seat> availableSeats = seatRepository.findByFlightIdAndIsOccupiedFalse(flightId);
        return availableSeats.stream().limit(numberOfTickets).collect(Collectors.toList());
    }
}