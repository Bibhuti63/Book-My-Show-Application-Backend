package com.example.BookMyShow.Models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "theater")
@Data
@NoArgsConstructor
public class Theater {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String location;

    //it is parent w.r.t theatreSeat
    @OneToMany(mappedBy = "theater",cascade = CascadeType.ALL)
    private List<TheaterSeat> theaterSeatList=new ArrayList<>();

    //it is parent w.r.t show
    @OneToMany(mappedBy = "theater",cascade = CascadeType.ALL)
    private List<Show>showList=new ArrayList<>();

}
