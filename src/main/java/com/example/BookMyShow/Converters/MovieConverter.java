package com.example.BookMyShow.Converters;

import com.example.BookMyShow.EntryDtos.MovieEntryDto;
import com.example.BookMyShow.Models.Movie;

public class MovieConverter {

    public static Movie convertEntryDtoToEntity(MovieEntryDto m)throws Exception{
        Movie movie=Movie.builder().movieName(m.getMovieName())
                .genre(m.getGenre()).language(m.getLanguage())
                .rating(m.getRating()).duration(m.getDuration())
                .build();
        return movie;

    }
}
