package com.example.swh_back.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.sql.Date;

@Entity
@Table(name = "mentor_reg")
public class MentorReg extends User{

    public MentorReg(User user) {
        super(user);
    }

    public MentorReg() {
    }

    private String college;

    private Boolean isAllotted;

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public Boolean getIsAllotted() {
        return isAllotted;
    }

    public void setIsAllotted(Boolean isAllotted) {
        this.isAllotted = isAllotted;
    }

}

