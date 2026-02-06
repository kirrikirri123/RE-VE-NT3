package com.ahlenius.revent3fx.service;

import com.ahlenius.revent3fx.entity.Member;
import com.ahlenius.revent3fx.entity.Rental;
import com.ahlenius.revent3fx.entity.RentalType;
import com.ahlenius.revent3fx.pricePolicy.MonthDiscountPricePolicy;
import com.ahlenius.revent3fx.repository.RentalRepoImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class RentalService {
    private RentalRepoImpl rentalRepo;
    private MonthDiscountPricePolicy monthDiscount;

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

    // Prissättning
    public void countActualDays(LocalDate stopRent, Rental rental){
        LocalDate theStartOfRent = rental.getStartOfRent();
        long actualDaysLong = stopRent.toEpochDay() - theStartOfRent.toEpochDay();
        int actualDays =(int) actualDaysLong;
        System.out.println("I metoden räkna om dagar.");
        changeRentDays(rental,actualDays);
    }

    public int rentalCountDays(Rental rental) {
        return  rental.getRentDays();
    }
/*
    public double returnRentalDayPrice(Rental rental) {
    return  rental.getProductId();

        getDayPrice();
        // få fram dagspris
    }
        public double calculateBasePrice(Rental rental) {
        return monthDiscount.calculateDay(returnRentalDayPrice(rental), rentalCountDays(rental));
    }


    public String pricePolicyCalc(Rental rental) {
        double totalBasePrice = calculateBasePrice(rental);
        String totalPrice;
        if (rental.getMember().getMemberStatus().equals(MemberStatus.PRIVATEINDIVIDUAL)) {
            totalPrice = privateIndividual.priceVAT(privateIndividual.discount(totalBasePrice));
        } else if {
            totalPrice = society.priceVAT(society.discount(totalBasePrice));
        }
        return totalPrice;
    }*/

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



