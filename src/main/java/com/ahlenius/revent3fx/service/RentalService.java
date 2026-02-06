package com.ahlenius.revent3fx.service;

import com.ahlenius.revent3fx.entity.*;
import com.ahlenius.revent3fx.repository.RentalRepoImpl;

import java.time.LocalDate;
import java.util.List;

public class RentalService {
    private RentalRepoImpl rentalRepo;


    public RentalService() {
    }

    public RentalService(RentalRepoImpl rentalRepo) {
        this.rentalRepo = rentalRepo;
    }

    public Rental newRental(Member member, long productId, RentalType rentalType, int rentDays, LocalDate startOfRent, boolean returned) {
        Rental rental = new Rental(member, productId, rentalType, rentDays, startOfRent, returned);
        rentalRepo.saveRental(rental);
        return rental;
    }

    public void updateReturnedStatus(Rental rental){
        rental.setReturned(true);
        rentalRepo.updateRental(rental);
    }

    // Hyresdagar - sätta, byta, räkna
    public void changeRentDays(Rental rental, int x) {
        rental.setRentDays(x);
        rentalRepo.updateRental(rental);
    }
    public void countActualDays(LocalDate stopRent, Rental rental){
        LocalDate theStartOfRent = rental.getStartOfRent();
        long actualDaysLong = stopRent.toEpochDay() - theStartOfRent.toEpochDay();
        int actualDays =(int) actualDaysLong;
        changeRentDays(rental,actualDays);
    }

    public List<Rental> getRentalList() {
        return rentalRepo.findRentalList();
    }
    public List<Rental> getReturnedRentalList(){
        return rentalRepo.findAvailibaleRentalList(true);
    }

     public List<Rental> getNotReturnedRentalList(){
        return rentalRepo.findAvailibaleRentalList(false);
     }



}



