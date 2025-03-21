package com.rasmus.cgi_backend.controller;

import com.rasmus.cgi_backend.model.Flight;
import com.rasmus.cgi_backend.model.Seat;
import com.rasmus.cgi_backend.service.FlightService;
import com.rasmus.cgi_backend.service.SeatService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/flights")
@RequiredArgsConstructor
public class FlightController {
    private final FlightService flightService;
    private final SeatService seatService;

    @GetMapping("/search")
    public List<Flight> searchFlights(@RequestParam(required = false) String origin,
                                      @RequestParam(required = false) String destination,
                                      @RequestParam(required = false) LocalDate date,
                                      @RequestParam(required = false) Double maxPrice) {
        return flightService.searchFlights(origin, destination, date, maxPrice);
    }    

    @GetMapping("/{flightId}/seats/recommend")
    public List<Seat> recommendSeats(@PathVariable Long flightId,
                                     @RequestParam boolean isWindow,
                                     @RequestParam boolean hasExtraLegroom,
                                     @RequestParam boolean isNearExit,
                                     @RequestParam int count) {
        return seatService.recommendSeats(flightId, isWindow, hasExtraLegroom, isNearExit, count);
    }
}