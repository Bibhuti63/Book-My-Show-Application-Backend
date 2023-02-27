package com.example.BookMyShow.Services;

import com.example.BookMyShow.Converters.TheaterConverter;
import com.example.BookMyShow.EntryDtos.TheaterEntryDto;
import com.example.BookMyShow.Enums.SeatType;
import com.example.BookMyShow.Models.Theater;
import com.example.BookMyShow.Models.TheaterSeat;
import com.example.BookMyShow.Repositories.TheaterRepository;
import com.example.BookMyShow.Repositories.TheaterSeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheaterService {
    @Autowired
    TheaterRepository theaterRepository;
    @Autowired
    TheaterSeatRepository theaterSeatRepository;

    public String addTheater(TheaterEntryDto theaterEntryDto) throws Exception {
        Theater theater= TheaterConverter.EntryDtoToEntity(theaterEntryDto);

        List<TheaterSeat>theaterSeatList=createTheaterSeat(theaterEntryDto,theater);

//        theaterRepository.save(theater);
        return "Theater added successfully";
    }
    private List<TheaterSeat> createTheaterSeat(TheaterEntryDto theaterEntryDto, Theater currentTheater){
        int classic=theaterEntryDto.getClassicSeatsCount();
        int premium= theaterEntryDto.getPremiumSeatsCount();
        List<TheaterSeat>list=new ArrayList<>();

        for(int count=1; count<=classic;count++) {
            TheaterSeat theaterSeat = TheaterSeat.builder().seatType(SeatType.CLASSIC)
                    .theater(currentTheater).seatNo(count + "c").build();
            list.add(theaterSeat);
        }

        for(int count=1; count<=premium;count++){
            TheaterSeat theaterSeat=TheaterSeat.builder().seatType(SeatType.PREMIUM)
                    .theater(currentTheater).seatNo(count+"p").build();
            list.add(theaterSeat);
        }
        theaterSeatRepository.saveAll(list);
        return list;
    }
}
