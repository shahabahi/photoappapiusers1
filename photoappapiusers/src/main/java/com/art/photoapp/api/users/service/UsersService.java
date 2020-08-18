package com.art.photoapp.api.users.service;


import com.art.photoapp.api.users.shared.UserDto;
import com.art.photoapp.api.users.ui.model.request.UserDetailsRequestModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UsersService extends UserDetailsService {
    UserDto createUser(UserDto userDetails);
    UserDto getUserDetailsByEmail(String email);
}
