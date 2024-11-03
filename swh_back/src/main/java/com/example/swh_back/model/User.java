package com.example.swh_back.model;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import java.sql.Date;

@MappedSuperclass
public class User {

    public User(User user) {
        this.email = user.getEmail();
        this.salutation = user.getSalutation();
        this.firstName = user.getFirstName();
        this.middleName = user.getMiddleName();
        this.lastName = user.getLastName();
        this.phoneNumber = user.getPhoneNumber();
        this.password = user.getPassword();
        this.confirmPassword = user.getConfirmPassword();
        this.dateOfBirth = user.getDateOfBirth();
        this.registeringAs = user.getRegisteringAs();
        this.check1 = user.getCheck1();
    }

    public User() {
    }

    @Id
    String email;
    String salutation;
    @Column(name = "fname")
    String firstName;

    @Column(name = "mname")
    String middleName;

    @Column(name = "lname")
    String lastName;

    @Column(name = "phone_number")
    String phoneNumber;

    String password;
    @Column(name = "confirm_password")
    String confirmPassword;

    @Column(name = "dob")
    Date dateOfBirth;

    @Column(name="registering_as")
    String registeringAs;

    Boolean check1;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSalutation() {
        return salutation;
    }

    public void setSalutation(String salutation) {
        this.salutation = salutation;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getRegisteringAs() {
        return registeringAs;
    }

    public void setRegisteringAs(String registeringAs) {
        this.registeringAs = registeringAs;
    }

    public Boolean getCheck1() {
        return check1;
    }

    public void setCheck1(Boolean check1) {
        this.check1 = check1;
    }
}
