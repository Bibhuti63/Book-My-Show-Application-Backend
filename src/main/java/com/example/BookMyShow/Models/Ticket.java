package com.example.BookMyShow.Models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "ticket")
@Data
@NoArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String movieName;
    private LocalDate showDate;
    private LocalTime showTime;
    private int totalAmount;
    private String ticketId= UUID.randomUUID().toString();
    private String theatreName;


    //this is child w.r.t user
    @ManyToOne
    @JoinColumn
    private User user;

    //this is child w.r.t show
    @ManyToOne
    @JoinColumn
    private Show show;
}
