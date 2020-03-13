package com.sayedlabs.Instgram.controller;

import com.sayedlabs.Instgram.exception.ResourceNotFoundException;
import com.sayedlabs.Instgram.model.RegistrationDto;
import com.sayedlabs.Instgram.model.User;
import com.sayedlabs.Instgram.model.UserDto;
import com.sayedlabs.Instgram.repository.UserRepository;
import com.sayedlabs.Instgram.service.SequenceGeneratorService;
import com.sayedlabs.Instgram.service.UserService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User registerUser(@Valid @RequestBody RegistrationDto registrationDto){
        User user = new User();
        user.setId(sequenceGeneratorService.generateSequence(User.SEQUENCE_NAME));
        user.setName(registrationDto.getName());
        user.setEmail(registrationDto.getEmail());
        user.setUserName(registrationDto.getUserName());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        return userRepository.save(user);
    }

    @PostMapping("/completeProfile")
    public User updateUserProfile(@Valid @RequestBody RegistrationDto registrationDto){
        User user = new User();
        user.setId(sequenceGeneratorService.generateSequence(User.SEQUENCE_NAME));
        user.setName(registrationDto.getName());
        user.setEmail(registrationDto.getEmail());
        user.setUserName(registrationDto.getUserName());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        return userRepository.save(user);
    }

    @GetMapping(value = "/validate/email/{email}")
    public Map<String,Boolean> validateByEmail(@PathVariable(value = "email") String email){
        Boolean exist = userRepository.existsByEmail(email);
        Map<String, Boolean> map = new HashMap<>();
        if (exist) {
            map.put("valid", false);
        } else {
            map.put("valid",true);
        }
        return map;
    }

    @GetMapping(value = "/validate/mobile/{mobileNo}")
    public Map<String,Boolean> validateByMobileNo(@PathVariable(value = "mobileNo") String mobileNo){
        Map<String, Boolean> map = new HashMap<>();
        Boolean exist = userRepository.existsByPhone(mobileNo);
        if (exist) {
            map.put("valid",false);
        } else {
            map.put("valid",true);
        }
        return map;
    }

    @GetMapping(value = "/")
    public List<UserDto> getUsers(){
        return userRepository.findAll().stream().map(user->userService.mapToUserDto(user)).collect(Collectors.toList());
    }

    @GetMapping(value = "/{userName}")
    public UserDto getUser(@PathVariable(value = "userName") String userName) throws ResourceNotFoundException{
        User user = userRepository.findByUserName(userName).orElseThrow(() -> new ResourceNotFoundException("User doesn't exist"));
        return userService.mapToUserDto(user);
    }

}

