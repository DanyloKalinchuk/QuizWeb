package com.prj2.prj2.user;

public class UserDTO {
    private Long id;

    private String email;

    private String userName;

    private Boolean isAdmin;

    private String password;

    public UserDTO() {}

    public UserDTO(Long id, String email, String userName, Boolean isAdmin){
        this.id = id;
        this.email = email;
        this.userName = userName;
        this.isAdmin = isAdmin;
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

    public String getPassword(){
        return this.password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    @Override 
    public String toString(){
        return "email: " + this.email + " userName: " + this.userName + " isAdmin: " + this.isAdmin;
    }
}
