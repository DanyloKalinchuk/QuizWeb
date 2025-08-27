package com.prj2.prj2.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDTO toDTO(User userEntity){
        UserDTO userDTO = new UserDTO(userEntity.getId(), userEntity.getEmail(), userEntity.getUserName(), userEntity.getIsAdmin());
        return userDTO;
    }

    public User toEntity(UserDTO userDTO){
        User userEntity = new User(userDTO.getId(), userDTO.getEmail(), userDTO.getUserName(), userDTO.getIsAdmin(), userDTO.getPassword());
        return userEntity;
    }

    public List<UserDTO> listToDTO(List<User> userEntities){
        List<UserDTO> userDTOs = new ArrayList<UserDTO>();
        for (User user : userEntities){
            userDTOs.add(toDTO(user));
        }
        return userDTOs;
    }
}
