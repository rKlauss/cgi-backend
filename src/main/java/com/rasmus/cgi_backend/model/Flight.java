package com.rasmus.cgi_backend.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String origin;
    private String destination;
    private LocalDateTime departureTime;
    private double price;

    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL)
    private List<Seat> seats;
}