package com.rasmus.cgi_backend.repository;

import com.rasmus.cgi_backend.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findByFlightIdAndIsOccupiedFalse(Long flightId);
}