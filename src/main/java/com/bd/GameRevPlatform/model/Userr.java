package com.bd.GameRevPlatform.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

public class Userr {
    private int    userr_id;
    private String firstName;
    private String lastName;
    private String hashedPassword;
    private String email;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date   joinDate;
    private int    group_id;
    private int    role_id;

    public Userr() {
    }

    public Userr(String hashedPassword, String email) {
        this.hashedPassword = hashedPassword;
        this.email = email;
    }

    public Userr(String firstName, String lastName, String hashedPassword, String email, Date joinDate, int group_id, int role_id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.hashedPassword = hashedPassword;
        this.email = email;
        this.joinDate = joinDate;
        this.group_id = group_id;
        this.role_id = role_id;
    }

    public int getUserr_id() {
        return userr_id;
    }

    public void setUserr_id(int userr_id) {
        this.userr_id = userr_id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    @Override
    public String toString() {
        return "Userr{" +
                "userr_id=" + userr_id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", hashedPassword='" + hashedPassword + '\'' +
                ", email='" + email + '\'' +
                ", joinDate=" + joinDate +
                ", group_id=" + group_id +
                ", role_id=" + role_id +
                '}';
    }
}
