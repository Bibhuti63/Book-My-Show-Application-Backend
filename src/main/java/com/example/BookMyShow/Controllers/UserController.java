package com.example.BookMyShow.Controllers;

import com.example.BookMyShow.EntryDtos.UserEntryDto;
import com.example.BookMyShow.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    public String addUser(@RequestBody UserEntryDto userEntryDto){
        return userService.addUser(userEntryDto);
    }
}
