package com.example.BookMyShow.Services;

import com.example.BookMyShow.Converters.ShowConverter;
import com.example.BookMyShow.EntryDtos.ShowEntryDto;
import com.example.BookMyShow.Enums.SeatType;
import com.example.BookMyShow.Models.*;
import com.example.BookMyShow.Repositories.MovieRepository;
import com.example.BookMyShow.Repositories.ShowRepository;
import com.example.BookMyShow.Repositories.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowService {
    @Autowired
    ShowRepository showRepository;
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    TheaterRepository theaterRepository;


    public String addShow(ShowEntryDto showEntryDto) {
        //convert EntryDto->Entity
        Show show= ShowConverter.convertEntryDtoToEntity(showEntryDto);

        //fetching movie and theater from given id
        Movie movie=movieRepository.findById(showEntryDto.getMovieId()).get();
        Theater theater=theaterRepository.findById(showEntryDto.getTheaterId()).get();

        //setting attributes of foreign key
        show.setMovie(movie);
        show.setTheater(theater);

        //create show_seats
        List<ShowSeat>showSeatList=createShowSeat(showEntryDto,show);
        show.setShowSeatList(showSeatList);

        //we also need to update parent entities
        movie.getShowList().add(show);
        theater.getShowList().add(show);

        theaterRepository.save(theater);


        return "The show has been added successfully";

    }
    //helper function to create showSeat
    private List<ShowSeat> createShowSeat(ShowEntryDto showEntryDto, Show show){
        List<ShowSeat>showSeatList=new ArrayList<>();

        List<TheaterSeat>theaterSeatList=show.getTheater().getTheaterSeatList();

        for(TheaterSeat theaterSeat: theaterSeatList){
            //traversing each theaterSeat and creating showSeat accordingly.
            ShowSeat showSeat=new ShowSeat();
            showSeat.setSeatNo(theaterSeat.getSeatNo());
            showSeat.setSeatType(theaterSeat.getSeatType());

            if(theaterSeat.getSeatType().equals(SeatType.CLASSIC)){
                showSeat.setPrice(showEntryDto.getClassicPrice());
            }
            else{
                showSeat.setPrice(showEntryDto.getPremiumPrice());
            }
            showSeat.setBooked(false);
            //adding the foreign key
            showSeat.setShow(show);


            //add to the list
            showSeatList.add(showSeat);

        }
        return showSeatList;

    }
}
