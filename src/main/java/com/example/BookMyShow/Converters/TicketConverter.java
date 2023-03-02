package com.example.BookMyShow.Converters;

import com.example.BookMyShow.EntryDtos.TicketEntryDto;
import com.example.BookMyShow.Models.Ticket;

public class TicketConverter {
    public static Ticket convertDtoToEntity(TicketEntryDto ticketEntryDto){
        Ticket ticket=new Ticket();
        return ticket;
    }
}
