package com.example.BookMyShow.Services;

import com.example.BookMyShow.Converters.UserConvertor;
import com.example.BookMyShow.EntryDtos.UserEntryDto;
import com.example.BookMyShow.Models.User;
import com.example.BookMyShow.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public String addUser(UserEntryDto userEntryDto)throws Exception {
        //convert EntryDto -> Entity
        /*
          1.Traditional Way:
            Create a new object, set all its attribute, by using setter function.
          2. Efficient way
            User Builder annotation i.e @Builder
            @Builder helps to create object easily.
            It requires allArgsConstructor.
            @Builder need to written at top of the class whose object to be created (here:User)

         */

//        User user=User.builder().age(userEntryDto.getAge()).mobNo(userEntryDto.getMobNo()).email(userEntryDto.getEmail())
//                .address(userEntryDto.getAddress()).name(userEntryDto.getName()).build();

        User user= UserConvertor.convertDtoToEntity(userEntryDto);

        userRepository.save(user);

        return "User added to successfully";
    }
}
