package com.test.lifung.todolist.service;

import com.test.lifung.todolist.entity.User;
import com.test.lifung.todolist.dto.UserDto;
import com.test.lifung.todolist.exception.BadParameterException;
import com.test.lifung.todolist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDto createUser(UserDto dto) {
        if (dto.getUserName() == null || "".equals(dto.getUserName())) {
            throw new BadParameterException("Username is invalid");
        }
        if (userRepository.findByUsername(dto.getUserName()) != null) {
            throw new BadParameterException("Username is already exists");
        }
        User user = new User();
        user.setUsername(dto.getUserName());
        userRepository.save(user);
        return mapUserToDto(user);
    }

    private UserDto mapUserToDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setUserName(user.getUsername());
        return dto;
    }

}
