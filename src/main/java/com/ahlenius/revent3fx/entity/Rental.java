package com.ahlenius.revent3fx.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long rentalId;
    private Member member;
    private RentalType rentalType;
    private int rentDays;
    private LocalDate startOfRent;
    private boolean returned;

    public Rental(Member member, RentalType rentalType, int rentDays, LocalDate startOfRent, boolean returned) {
        this.member = member;
        this.rentalType = rentalType;
        this.rentDays = rentDays;
        this.startOfRent = startOfRent;
        this.returned = returned;
    }
}
