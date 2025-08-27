package com.prj2.prj2.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id){
        UserDTO user = this.userService.getById(id);

        if (user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping
    public List<UserDTO> getUsers(
        @RequestParam(required = false) String email,
        @RequestParam(required = false) String userName,
        @RequestParam(required = false) Boolean isAdmin
    ){
        if (email != null){
            return this.userService.getByEmail(email);
        }else if (userName != null){
            return this.userService.getByUserName(userName);
        }else if (isAdmin != null){
            return this.userService.getByIsAdmin(isAdmin);
        }else{
            return this.userService.getAll();
        }
    }

    @PostMapping
    public ResponseEntity<UserDTO> addUser(
        @RequestBody UserDTO userDTO
    ){
        UserDTO user = this.userService.addUser(userDTO);
        
        if (user != null){
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/{id}/verify")
    public ResponseEntity<String> verifyPw(
        @PathVariable Long id,
        @RequestBody PwDTO pwDTO
    ){
        UserService.response status = this.userService.verifyPw(id, pwDTO.getPassword());

        if (status.equals(UserService.response.OK)){
            return new ResponseEntity<>("Password is Correct", HttpStatus.OK);
        }else if (status.equals(UserService.response.USER_NOT_FOUND)){
            return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Incorrect Password", HttpStatus.UNAUTHORIZED); 
    }

    @PutMapping
    public ResponseEntity<UserDTO> updateUser(
        @RequestBody UserDTO userDTO
    ){
        UserService.response status = this.userService.updateUser(userDTO);

        if (status.equals(UserService.response.OK)){
            return new ResponseEntity<>(this.userService.getById(userDTO.getId()), HttpStatus.OK);
        }else if (status.equals(UserService.response.USER_NOT_FOUND)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}/change-password")
    public ResponseEntity<String> changePassword(
        @PathVariable Long id,
        @RequestBody PwDTO pwDTO
    ){
        UserService.response status = this.userService.updatePassword(id, pwDTO);

        if (status.equals(UserService.response.OK)){
            return new ResponseEntity<>("Password Successfully Changed", HttpStatus.OK);
        }
        return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        UserService.response status = this.userService.deleteUser(id);

        if (status.equals(UserService.response.OK)){
            return new ResponseEntity<>("Password Successfully Deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
    }
}
