package com.example.BookMyShow.Models;

import com.example.BookMyShow.Enums.ShowType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "shows") //naming the table "show" will not work bcz show is a predefined command.
@Data
@NoArgsConstructor
public class Show {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate showDate;
    private LocalTime showTime;
    @Enumerated(value = EnumType.STRING)
    private ShowType showType;
    @CreationTimestamp
    private Date createdOn;
    @UpdateTimestamp
    private Date updatedOn;


    //this is child w.r.t movie
    @ManyToOne
    @JoinColumn
    private Movie movie;

    //this is child w.r.t Theater
    @ManyToOne
    @JoinColumn
    private Theater theater;

    //this is parent w.r.t ticket
    @OneToMany(mappedBy = "show",cascade = CascadeType.ALL)
    private List<Ticket>bookedTicketList=new ArrayList<>();

    //this is parent w.r.t. showseats
    @OneToMany(mappedBy = "show",cascade = CascadeType.ALL)
    private List<ShowSeat>showSeatList=new ArrayList<>();
}
