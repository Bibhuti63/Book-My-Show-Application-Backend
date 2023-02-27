package com.example.BookMyShow.EntryDtos;


import com.example.BookMyShow.Enums.Genre;
import com.example.BookMyShow.Enums.Language;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class MovieEntryDto {

    private String movieName;
    private double rating;
    private int duration;
    private Language language;
    private Genre genre;
}
