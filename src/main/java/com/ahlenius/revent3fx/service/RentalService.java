package com.ahlenius.revent3fx.service;

import com.ahlenius.revent3fx.entity.Member;
import com.ahlenius.revent3fx.entity.RentalType;
import com.ahlenius.revent3fx.repository.RentalRepoImpl;

import java.time.LocalDate;

public class RentalService {
private RentalRepoImpl rentalRepo;

public RentalService(){}

    public RentalService(RentalRepoImpl rentalRepo) {
        this.rentalRepo = rentalRepo;
            }
//skapa uthyrningsdatum

    // skapa ny rental
    public void newRental(Member member, long productId,RentalType rentalType, int rentDays, LocalDate startOfRent, boolean returned){

    // byta antal uthyrda dagar
// hämta rental

}}

