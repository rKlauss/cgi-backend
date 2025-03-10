package com.rasmus.cgi_backend.service;

import com.rasmus.cgi_backend.model.Flight;
import com.rasmus.cgi_backend.repository.FlightRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightService {
    private final FlightRepository flightRepository;
    private final List<String> locations = Arrays.asList("Tallinn", "Helsingi", "Saaremaa", "Sofia");

    @PostConstruct
    public void initFlights() {
        List<Flight> flights = new ArrayList<>();
        for (String origin : locations) {
            for (String destination : locations) {
                if (!origin.equals(destination)) {
                    flights.add(new Flight(null, origin, destination, LocalDateTime.now().plusHours((int) (Math.random() * 10)), 50 + Math.random() * 100, null));
                }
            }
        }
        flightRepository.saveAll(flights);
    }

    public List<Flight> searchFlights(String origin, String destination) {
        return flightRepository.findByOriginAndDestination(origin, destination);
    }
}