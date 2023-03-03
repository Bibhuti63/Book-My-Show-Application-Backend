package com.example.BookMyShow.Models;

import javax.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@Data //combination of @Getter,@Setter,@EqualsAndHashCode,@ToString,@RequiredArgsConstructor
@NoArgsConstructor
@Builder //helps to create object//it requires all args constructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int age;
    @Column(unique = true,nullable = false)
    private String email;
    @NonNull //same as 'nullable=false';
    @Column(unique = true)
    private String mobNo;
    private String address;

    //this is parent w.r.t ticket
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Ticket> bookedTickets=new ArrayList<>();
}
