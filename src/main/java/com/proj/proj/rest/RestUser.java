package com.proj.proj.rest;

import com.proj.proj.data.User;
import com.proj.proj.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class RestUser {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/newUser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public User newUser(@RequestParam("email") String _email, @RequestParam String name,
                        @RequestParam String secondName, @RequestParam String password,
                        @RequestParam String birthday) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date parsed = format.parse(birthday);
        return userService.addNewUser(_email, name, secondName, parsed, password);
    }

    @RequestMapping(value = "/find/{login}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public User findByEmail(@PathVariable("login") String _email) {
        return userService.findUser(_email);
    }

    @RequestMapping(value = "/remove/{login}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean removeUser(@PathVariable("login") String _email) {
        return userService.removeUser(_email);
    }
}
