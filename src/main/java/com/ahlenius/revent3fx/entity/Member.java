package com.ahlenius.revent3fx.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long memberId;
    private String fName;
    private String lName;
    private String phone;
    private String email;
    private MemberStatus memberStatus;
    private List<Rental> rentalListMember = new ArrayList<>(); // en medlem kan ha flera uthyrningar kopplat mot uthyrningsid ska det vara en lista med uthyrningar??

}
