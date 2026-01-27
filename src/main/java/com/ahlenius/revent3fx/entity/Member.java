package com.ahlenius.revent3fx.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "member_id")
    private long memberId;
    @Column(name= "first_name",nullable = false,length = 30)
    private String fname;
    @Column(name= "last_name",nullable = false,length = 30)
    private String lname;
    @Column(length = 15)
    private String phone;
    @Column(nullable = false,length = 120, unique = true)
    private String email;
    @Enumerated(EnumType.STRING)
    @Column(name = "member_status", length = 18, nullable = false)
    private MemberStatus memberStatus;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Rental> rentalListMember = new ArrayList<>(); // Vilka uthyrningar är kopplade till medlemmen

    protected Member (){}

    public Member(String fName, String lName, String phone, String email, MemberStatus memberStatus) {
        this.fname = fName;
        this.lname = lName;
        this.phone = phone;
        this.email = email;
        this.memberStatus = memberStatus;}

    //SETTER
    public void setfName(String fName) {
        this.fname = fName;
    }
    public void setlName(String lName) {
        this.lname = lName;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setMemberStatus(MemberStatus memberStatus) {
        this.memberStatus = memberStatus;
    }

    //GETTER
    public long getMemberId() {
        return memberId;
    }
    public String getfName() {
        return fname;
    }
    public String getlName() {
        return lname;
    }
    public String getPhone() {
        return phone;
    }
    public String getEmail() {
        return email;
    }
    public MemberStatus getMemberStatus() {
        return memberStatus;
    }
    public List<Rental> getRentalListMember() {
        return rentalListMember;
    }

    //HJÄLPMETOD
    public void addRental(Rental rental) {
        rentalListMember.add(rental);
        rental.setMember(this);
    }

    @Override
    public String toString() {
        return "Medlemsnr: "+ memberId  + "\nNamn: "+ fname +" "+lname+ "\nMail:  "+ email+"\nStatus: "+ memberStatus;
    }

}
