package com.rasmus.cgi_backend;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.rasmus.cgi_backend.model.Flight;
import com.rasmus.cgi_backend.model.Seat;
import com.rasmus.cgi_backend.repository.SeatRepository;
import com.rasmus.cgi_backend.service.SeatService;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
class CgiBackendApplicationTests {

    @Spy
    private SeatRepository seatRepository;

    @InjectMocks
    private SeatService seatService;

    @Test
    public void testBookSeats() {
        Flight flight = new Flight(1L, "Tallinn", "Saaremaa", LocalDate.now(), LocalDateTime.now(), 0.0, null);
    
        List<Seat> seats = List.of(
            new Seat(1L, "1A", false, false, false, false, false, 100.0, flight),
            new Seat(2L, "1B", false, false, false, false, false, 100.0, flight)
        );
    
        when(seatRepository.findAllById(any())).thenReturn(seats);
        
        System.out.println("Before booking: " + seats);
        
        boolean result = seatService.bookSeats(List.of(1L, 2L));
    
        System.out.println("After booking: " + seats);
        
        assertTrue(result);
        assertTrue(seats.get(0).isOccupied(), "Seat 1A should be marked as occupied");
        assertTrue(seats.get(1).isOccupied(), "Seat 1B should be marked as occupied");
    }
}    