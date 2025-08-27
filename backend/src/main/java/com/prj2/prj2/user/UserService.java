package com.prj2.prj2.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.transaction.Transactional;

@Component
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    public static enum response{
        BAD_REQUEST,
        USER_NOT_FOUND,
        INVALID_PASSWORD,
        OK
    }

    public List<UserDTO> getAll(){
        List<User> users = this.userRepository.findAll();
        return userMapper.listToDTO(users);
    }

    public UserDTO getById(Long id){
        Optional<User> existingUser = this.userRepository.findById(id);
        if (existingUser.isPresent()){
            return userMapper.toDTO(existingUser.get());
        }
        return null;
    }

    public List<UserDTO> getByUserName(String userName){
        List<User> users = this.userRepository.findByUserNameContainingIgnoreCase(userName);
        return userMapper.listToDTO(users);
    }

    public List<UserDTO> getByEmail(String email){
        List<User> users = this.userRepository.findByEmailContainingIgnoreCase(email);
        return userMapper.listToDTO(users);
    }

    public List<UserDTO> getByIsAdmin(Boolean isAdmin){
        List<User> users = this.userRepository.findByIsAdmin(isAdmin);
        return userMapper.listToDTO(users);
    }

    public UserService.response updatePassword(Long id, PwDTO pwDTO){
        Optional<User> existingUser = this.userRepository.findById(id);
        String response = "===== Updating password: ";

        if (existingUser.isPresent() && verifyPw(id, pwDTO.getPassword()).equals(UserService.response.OK)){
            User user = existingUser.get();
            user.updatePw(pwDTO.getNewPassword());
            this.userRepository.save(user);
            System.out.println(response + "Success =====");
            return UserService.response.OK;
        }
        System.out.println(response + "User Not Found =====");
        return UserService.response.USER_NOT_FOUND;
    }

    public UserService.response verifyPw(Long id, String password){
        Optional<User> existingUser = this.userRepository.findById(id);
        String response = "===== Verifying Password: ";

        if (existingUser.isPresent()){
            User user = existingUser.get();
            if (user.verifyPw(password)){
                System.out.println(response + "Success =====");
                return UserService.response.OK;
            }
            System.out.println(response + "Invalid Password =====");
            return UserService.response.INVALID_PASSWORD;
        }
        System.out.println(response + "User Not Found =====");
        return UserService.response.USER_NOT_FOUND;
    }

    public UserDTO addUser(UserDTO userDTO){
        String response = "===== Adding User: ";
        System.out.println("===== New User Data: " + userDTO.toString() + " =====");

        if (!this.userRepository.existsByUserNameAndIdNot(userDTO.getUserName(), userDTO.getId()) 
        && !this.userRepository.existsByEmailAndIdNot(userDTO.getEmail(), userDTO.getId())){
            User newUser = userMapper.toEntity(userDTO);
            System.out.println(response + "Success =====");
            return userMapper.toDTO(this.userRepository.save(newUser));
        }
        System.out.println(response + "User with such User Name and/or Email aready exists =====");
        return null;
    }

    public UserService.response updateUser(UserDTO userDTO){
        Optional<User> user = this.userRepository.findById(userDTO.getId());
        String response = "===== Updating User Data: ";
        System.out.println("===== User Data to Update: id: " + userDTO.getId() + userDTO.toString() + " =====");

        if (user.isPresent()){
            if (this.userRepository.existsByUserName(userDTO.getUserName()) || this.userRepository.existsByEmail(userDTO.getEmail())){
                System.out.println(response + "Email or/and UserName Already Exist/s =====");
                return UserService.response.BAD_REQUEST;
            }

            User userToUpdate = user.get();
            userToUpdate.setEmail(userDTO.getEmail());
            userToUpdate.setUserName(userDTO.getUserName());
            userToUpdate.setIsAdmin(userDTO.getIsAdmin());

            System.out.println(response + "Success =====");
            return UserService.response.OK;
        }
        System.out.println(response + "User Not Found =====");
        return UserService.response.USER_NOT_FOUND;
    }

    @Transactional
    public UserService.response deleteUser(Long id){
        String response = "===== Deleting User with id: " + id + ": ";
        if (this.userRepository.existsById(id)){
            this.userRepository.deleteById(id);
            System.out.println(response + "Success =====");
            return UserService.response.OK;
        }
        System.out.println(response + "User Not Found =====");
        return UserService.response.USER_NOT_FOUND;
    }
}
