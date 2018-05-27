/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import model.customAnnotation.MyId;

/**
 *
 * @author dongvu
 */
public class User {

    @MyId
    private String username;
    private String password, phone, fullname, email, birthday, gender;
    private int status;

    public User() {
    }

    public User(String username, String password, String phone, String fullname, String email, String birthday, String gender) {
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.fullname = fullname;
        this.email = email;
        this.birthday = birthday;
        this.gender = gender;
        this.status = 1; // 1.active, 0.banned
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
