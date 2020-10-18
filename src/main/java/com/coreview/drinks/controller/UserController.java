package com.coreview.drinks.controller;

import com.coreview.drinks.data.user.User;
import com.coreview.drinks.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/user")
    public ResponseEntity<User> getAllDrinks(@RequestParam Integer id) {
        User user = this.userService.getUserById(id).get();
        return new ResponseEntity(user, new HttpHeaders(), HttpStatus.OK);
    }
}
