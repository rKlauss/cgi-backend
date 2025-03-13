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
        return seatService.recommendSeats(flightId, false, false, false, numberOfTickets);
    }

    @GetMapping("/plan")
    public List<Seat> getSeatPlan(@RequestParam Long flightId) {
        return seatService.getSeatPlan(flightId);
    }

    @PostMapping("/book")
    public boolean bookSeats(@RequestBody List<Long> seatIds) {
    return seatService.bookSeats(seatIds);
    }

}
