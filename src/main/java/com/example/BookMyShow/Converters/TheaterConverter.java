package com.example.BookMyShow.Converters;

import com.example.BookMyShow.EntryDtos.TheaterEntryDto;
import com.example.BookMyShow.Models.Theater;

public class TheaterConverter {

    public static Theater EntryDtoToEntity(TheaterEntryDto t) throws Exception{
        Theater theater=Theater.builder().location(t.getLocation())
                        .name(t.getName()).build();
        return theater;
    }
}
