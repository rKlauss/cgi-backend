package com.rasmus.cgi_backend.service;

import com.rasmus.cgi_backend.model.Flight;
import com.rasmus.cgi_backend.model.Seat;
import com.rasmus.cgi_backend.repository.FlightRepository;
import com.rasmus.cgi_backend.repository.SeatRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.*;
import java.util.*;


@Service
@RequiredArgsConstructor
public class FlightService {
    private final FlightRepository flightRepository;
    private final SeatRepository seatRepository;
    private final List<String> locations = Arrays.asList("Tallinn", "Helsingi", "Saaremaa", "Sofia");

    @PostConstruct
    public void initFlights() {
        seatRepository.deleteAll();
        flightRepository.deleteAll();
        List<Flight> flights = new ArrayList<>();

    for (String origin : locations) {
        for (String destination : locations) {
            if (!origin.equals(destination)) {
                for (int i = 0; i < 2; i++ ) { 
                    double basePrice = Math.round((50 + Math.random() * 100) * 10) / 10.0;
                    LocalDate departureDate = LocalDate.now().plusDays((int) (Math.random() * 10));
                    LocalDateTime departureTime = departureDate.atTime(LocalTime.of((int) (Math.random() * 24), (int) (Math.random() * 60)));
                    
                    Flight flight = new Flight(null, origin, destination, departureDate, departureTime, basePrice, new ArrayList<>());
                    flights.add(flight);
                    flightRepository.save(flight);
                    generateSeatsForFlight(flight, basePrice);
                    }
                }
            }
        }
    }

    private void generateSeatsForFlight(Flight flight, double basePrice) {
        List<Seat> seats = new ArrayList<>();
        String[] seatColumns = {"A", "B", "C", "D", "E", "F"};
        
        for (int row = 1; row <= 10; row++) {
            boolean isBusinessClass = row <= 2;
            boolean hasExtraLegroom = isBusinessClass || row == 3; 
            boolean isNearExit = isBusinessClass || row == 10; 
            double seatPrice = isBusinessClass ? basePrice * 2.5 : basePrice;
    
            for (String column : seatColumns) {
                boolean isWindow = column.equals("A") || column.equals("F");
    
                seats.add(new Seat(
                    null, 
                    row + column, 
                    Math.random() < 0.3,
                    isWindow,
                    hasExtraLegroom,
                    isNearExit, 
                    isBusinessClass, 
                    Math.round(seatPrice * 10) / 10.0, 
                    flight
                ));
            }
        }
        seatRepository.saveAll(seats);
    }
    

    public List<Flight> searchFlights(String origin, String destination, LocalDate date, Double maxPrice) {
        return flightRepository.findAll().stream()
                .filter(f -> (origin == null || f.getOrigin().equals(origin)) &&
                             (destination == null || f.getDestination().equals(destination)) &&
                             (date == null || f.getDepartureDate().equals(date)) &&
                             (maxPrice == null || f.getPrice() <= maxPrice))
                .toList();
    }
}