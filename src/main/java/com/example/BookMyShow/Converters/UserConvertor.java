package com.example.BookMyShow.Converters;

import com.example.BookMyShow.EntryDtos.UserEntryDto;
import com.example.BookMyShow.Models.User;

public class UserConvertor {
    //static function bcz its not dependent on object, rather depends on class
    public static User convertDtoToEntity(UserEntryDto u) throws Exception{
        User user= User.builder().age(u.getAge()).mobNo(u.getMobNo()).name(u.getName())
                .email(u.getEmail()).address(u.getAddress()).build();

        return user;
    }
}
