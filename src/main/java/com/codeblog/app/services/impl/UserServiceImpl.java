package com.codeblog.app.services.impl;

import com.codeblog.app.entity.User;
import com.codeblog.app.exceptions.ResourceNotFoundException;
import com.codeblog.app.payloads.UserDto;
import com.codeblog.app.repository.UserRepo;
import com.codeblog.app.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        User userSaved = this.userRepo.save(dtoToUser(userDto));
        return userToDto(userSaved);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User"," id ",userId));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());

        User userSaved = userRepo.save(user);
        return userToDto(userSaved);
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User"," id ",userId));
        return userToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepo.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        for (User user : users){
            userDtos.add(userToDto(user));
        }
        return userDtos;
    }

    @Override
    public void deleteUser(Integer userId) {

        User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User"," id ",userId));
        userRepo.delete(user);
    }

    private User dtoToUser(UserDto userDto){
        return modelMapper.map(userDto, User.class);
    }

    public UserDto userToDto(User user){
        return modelMapper.map(user, UserDto.class);
    }
}
