package com.ahlenius.revent3fx.service;

import com.ahlenius.revent3fx.entity.*;
import com.ahlenius.revent3fx.exception.NoPriceFoundException;
import com.ahlenius.revent3fx.pricePolicy.Employee;
import com.ahlenius.revent3fx.pricePolicy.PrivateIndividual;
import com.ahlenius.revent3fx.pricePolicy.Society;
import com.ahlenius.revent3fx.repository.BouncyCastleRepoImpl;
import com.ahlenius.revent3fx.repository.DiscoMachineRepoImpl;
import com.ahlenius.revent3fx.repository.MascoteCostumeRepoImpl;

import java.math.BigDecimal;

public class PricingService {
    private Employee employee = new Employee();
    private PrivateIndividual privateIndividual = new PrivateIndividual();
    private Society society = new Society();
    private BouncyCastleRepoImpl bouncyCastleRepo;
    private DiscoMachineRepoImpl discoMachineRepo;
    private MascoteCostumeRepoImpl mascoteCostumeRepo;


    public PricingService(){}

    public PricingService(BouncyCastleRepoImpl bouncyCastleRepo, DiscoMachineRepoImpl discoMachineRepo, MascoteCostumeRepoImpl mascoteCostumeRepo) {
        this.bouncyCastleRepo = bouncyCastleRepo;
        this.discoMachineRepo = discoMachineRepo;
        this.mascoteCostumeRepo = mascoteCostumeRepo;}


    public String pricePolicyCalc(Rental rental) { // Ska returnera priset för att visa i VIEW!
        BigDecimal totalBasePrice = calculateBasePrice(rental);
        String totalPrice = "";
        switch (rental.getMember().getMemberStatus()) {
            case MemberStatus.PRIVATEINDIVIDUAL -> totalPrice = privateIndividual.priceVAT(privateIndividual.discount(totalBasePrice));
            case MemberStatus.SOCIETY  -> totalPrice = society.priceVAT(privateIndividual.discount(totalBasePrice));
            case MemberStatus.EMPLOYEE ->totalPrice = employee.priceVAT(privateIndividual.discount(totalBasePrice));
        }                return totalPrice;
    }

    public BigDecimal calculateBasePrice(Rental rental) {
        return calculateDay(dayPriceFromRental(rental), rentalCountDays(rental));
    }

    public BigDecimal dayPriceFromRental(Rental rental) {
        return switch (rental.getRentalType()) {
            case BOUNCYCASTLE -> bouncyCastleRepo.findById(rental.getProductId()).orElseThrow(() -> new NoPriceFoundException("Inget pris hittat. Kontakta din säljare.")).getDayPrice();
            case DISCOMACHINE -> discoMachineRepo.findById(rental.getProductId()).orElseThrow(() -> new NoPriceFoundException("Inget pris hittat. Kontakta din säljare.")).getDayPrice();
            case MASCOTECOSTUME -> mascoteCostumeRepo.findById(rental.getProductId()).orElseThrow(() -> new NoPriceFoundException("Inget pris hittat. Kontakta din säljare.")).getDayPrice();
        };
    }

    public BigDecimal calculateDay(BigDecimal dayPrice, int days) {
        BigDecimal price = dayPrice.multiply(BigDecimal.valueOf(days));
        if (days >= 30) {
            price = price.multiply(BigDecimal.valueOf(0.7));
        }
        return price;
    }
    public int rentalCountDays(Rental rental) {
        return  rental.getRentDays();
    }





}
