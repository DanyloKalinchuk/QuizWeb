package com.prj2.prj2.user;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCrypt;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Email
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Length(min = 3, max = 10, message = "User Name must contain between 3 and 10 characters")
    @Column(name = "user_name", nullable = false, unique = true)
    private String userName;

    @Column(name = "is_admin", nullable = false)
    private Boolean isAdmin;

    @Column(name = "hashed_pw", nullable = false)
    @JsonIgnore
    private String hashedPw;

    public User() {}

    public User(Long id, String email, String userName, Boolean isAdmin, String password){
        this.id = id;
        this.email = email;
        this.userName = userName;
        this.isAdmin = isAdmin;

        this.hashedPw = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public Long getId(){
        return this.id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getUserName(){
        return this.userName;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public Boolean getIsAdmin(){
        return this.isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin){
        this.isAdmin = isAdmin;
    }

    public Boolean verifyPw(String password){
        return BCrypt.checkpw(password, this.hashedPw);
    }

    public void updatePw(String password){
        this.hashedPw = BCrypt.hashpw(password, BCrypt.gensalt());
    }
}
