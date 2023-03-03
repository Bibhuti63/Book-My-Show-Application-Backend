package com.example.BookMyShow.Services;

import com.example.BookMyShow.Converters.TicketConverter;
import com.example.BookMyShow.EntryDtos.TicketEntryDto;
import com.example.BookMyShow.Enums.ShowType;
import com.example.BookMyShow.Models.Show;
import com.example.BookMyShow.Models.ShowSeat;
import com.example.BookMyShow.Models.Ticket;
import com.example.BookMyShow.Models.User;
import com.example.BookMyShow.Repositories.ShowRepository;
import com.example.BookMyShow.Repositories.TicketRepository;
import com.example.BookMyShow.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.List;

@Service
public class TicketService {
    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    ShowRepository showRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    JavaMailSender javaMailSender;


    public String bookTicket(TicketEntryDto ticketEntryDto) throws Exception {
        //convert dto-> entity
        Ticket ticket= TicketConverter.convertDtoToEntity(ticketEntryDto);

        //validation
        boolean isValidRequest=checkValidity(ticketEntryDto);

        if(!isValidRequest){
            throw new Exception("Requested Seats not available");
        }

        //calculate total amount
        int showId=ticketEntryDto.getShowId();
        Show show=showRepository.findById(showId).get();
        List<String> requestedSeats=ticketEntryDto.getRequestedSeats();
        List<ShowSeat> showSeatList=show.getShowSeatList();

        int totalAmount=0;
        for(ShowSeat showSeat : showSeatList){
            if(requestedSeats.contains(showSeat.getSeatNo())){
                totalAmount=totalAmount+showSeat.getPrice();
                showSeat.setBooked(true);
                showSeat.setBookedAt(new Date());
            }
        }
        ticket.setTotalAmount(totalAmount);

        //set alloted seats
        String allotedSeats=getAllotedSeats(requestedSeats);
        ticket.setBookedSeats(allotedSeats);

        //setting other attribute of ticket
        ticket.setMovieName(show.getMovie().getMovieName());
        ticket.setShowDate(show.getShowDate());
        ticket.setShowTime(show.getShowTime());
        ticket.setTheatreName(show.getTheater().getName());

        //setting the foreign key attribute
        User user=userRepository.findById(ticketEntryDto.getUserId()).get();
        ticket.setUser(user);
        ticket.setShow(show);

        //save in the parent
        Ticket tempTicket=ticketRepository.save(ticket); //save function returns entity

        show.getBookedTicketList().add(tempTicket);
        showRepository.save(show);

        user.getBookedTickets().add(tempTicket);
        userRepository.save(user);

        //to send mail
        String body="Dear "+tempTicket.getUser().getName()+",\n"+
                "\nThank you for visiting BookMyShow App. Your Ticket has been booked and details are indicated below. \n"+
                "\n\nTicket Id:"+tempTicket.getTicketId()+
                "\nMovie Name:"+tempTicket.getMovieName()+
                "\nTheater Name: "+tempTicket.getTheatreName()+tempTicket.getTotalAmount()+
                "\nPlease find your Seat No : ["+tempTicket.getBookedSeats()+"]"+
                "\nTicket fee: "+tempTicket.getTotalAmount()+
                "\n\nWarm Regards"+
                "\nTeam Book My Show";

        MimeMessage mimeMessage=javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage,true);
        mimeMessageHelper.setFrom("bookmyshow684@gmail.com");
        mimeMessageHelper.setTo(user.getEmail());
        mimeMessageHelper.setText(body);
        mimeMessageHelper.setSubject("Ticket Booking Confirmation at "+tempTicket.getTheatreName());

        javaMailSender.send(mimeMessage);


        return "Ticket Booked Successfully for Seat No"+allotedSeats+" for Movie : "+tempTicket.getMovieName() ;



    }
    private boolean checkValidity(TicketEntryDto ticketEntryDto){
        int showId=ticketEntryDto.getShowId();
        Show show=showRepository.findById(showId).get();
        List<String> requestedSeats=ticketEntryDto.getRequestedSeats();
        List<ShowSeat> showSeatList=show.getShowSeatList();

        //iterating list of seats for particular show
        for(ShowSeat showSeat: showSeatList){
            String seatNo=showSeat.getSeatNo();
            if(requestedSeats.contains(seatNo)){
                if(showSeat.isBooked()){
                    return false;
                }

            }
        }
        return true;
    }
    private String getAllotedSeats(List<String> list){
        String str="";
        for(String s: list){
            str=str+s+", ";
        }
        return str;
    }
}

