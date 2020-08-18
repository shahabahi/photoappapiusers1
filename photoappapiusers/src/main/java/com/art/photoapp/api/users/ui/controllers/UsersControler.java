package com.art.photoapp.api.users.ui.controllers;


import com.art.photoapp.api.users.shared.UserDto;
import com.art.photoapp.api.users.ui.model.request.UserDetailsRequestModel;
import com.art.photoapp.api.users.ui.model.response.UserRest;
import com.art.photoapp.api.users.service.UsersService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UsersControler {
    @Autowired
    private Environment env;

    Map<String, UserRest> userRestMap;

    @Autowired
    UsersService userService;


    @GetMapping("/status/check")
    public String status() {
        return "working" + env.getProperty("local.server.port");
    }

    @PostMapping(consumes =
            {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE
            }, produces =
            {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE
            })
    public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserDetailsRequestModel userDetails) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserDto userDto = modelMapper.map(userDetails, UserDto.class);
        UserDto createdUser = userService.createUser(userDto);
        UserRest userRest = modelMapper.map(createdUser, UserRest.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(userRest);
    }
}
