package com.prj2.prj2.user;

public class PwDTO {
    private String newPassword;
    private String password;

    public PwDTO() {}

    public PwDTO(String newPassword, String password){
        this.newPassword = newPassword;
        this.password = password;
    }

    public String getNewPassword(){
        return this.newPassword;
    }

    public void setNewPassword(String newPassword){
        this.newPassword = newPassword;
    }

    public String getPassword(){
        return this.password;
    }

    public void setPassword(String password){
        this.password = password;
    }
}
