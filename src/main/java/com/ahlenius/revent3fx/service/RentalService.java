package com.ahlenius.revent3fx.service;

import com.ahlenius.revent3fx.entity.*;
import com.ahlenius.revent3fx.exception.InvalidAmountRentingDaysException;
import com.ahlenius.revent3fx.exception.InvalidRentalInfoInputException;
import com.ahlenius.revent3fx.repository.RentalRepoImpl;

import java.math.BigDecimal;
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
         if(rentDays>183||rentDays<1)
        {throw new InvalidAmountRentingDaysException("Stämmer hyresdagarna? Minst 1 dag. Max 6 månader");}
        if(member.equals(null) || rentalType == null)
        {throw new InvalidRentalInfoInputException("Fyll i alla fält för att göra en uthyrning");
    }else{Rental rental = new Rental(member, productId, rentalType, rentDays, startOfRent, returned);
        rentalRepo.saveRental(rental);
        //member.addRental(rental);}
        return rental;
    }}

    public void updateRental(Rental rental){
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

    public String sumAllRevenueFromRentals(){
         BigDecimal sum = rentalRepo.findRevenue(true);
         return  sum+ "kr.     * obs ex. moms.";
    }

}



