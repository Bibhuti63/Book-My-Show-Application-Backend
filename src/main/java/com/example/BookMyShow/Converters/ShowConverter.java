package com.example.BookMyShow.Converters;

import com.example.BookMyShow.EntryDtos.ShowEntryDto;
import com.example.BookMyShow.Models.Show;

public class ShowConverter {
    public static Show convertEntryDtoToEntity(ShowEntryDto showEntryDto){
        //convert Dto -> Entity
        Show show=Show.builder().showDate(showEntryDto.getLocalDate())
                .showTime(showEntryDto.getLocalTime())
                .showType(showEntryDto.getShowType()).build();

        return show;
    }
}
