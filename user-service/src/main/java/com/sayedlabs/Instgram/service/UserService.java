package com.sayedlabs.Instgram.service;

import com.sayedlabs.Instgram.model.User;
import com.sayedlabs.Instgram.model.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    ModelMapper modelMapper;

    public UserDto mapToUserDto(User user){
        return modelMapper.map(user,UserDto.class);
    }
}
