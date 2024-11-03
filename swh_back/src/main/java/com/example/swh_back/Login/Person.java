
package com.example.swh_back.Login;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import jakarta.persistence.Column;

@Entity
public class Person {

    @Id
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    // Constructors
    public Person() {
        // Default constructor for JPA
    }

    public Person(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // Getters and setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}