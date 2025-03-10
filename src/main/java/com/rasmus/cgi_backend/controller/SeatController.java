package com.rasmus.cgi_backend.controller;

import com.rasmus.cgi_backend.model.Seat;
import com.rasmus.cgi_backend.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seats")
@RequiredArgsConstructor
public class SeatController {
    private final SeatService seatService;

    @GetMapping("/recommend")
    public List<Seat> recommendSeats(@RequestParam Long flightId, @RequestParam int numberOfTickets) {
        return seatService.recommendSeats(flightId, numberOfTickets);
    }
}
