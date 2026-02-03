package com.ahlenius.revent3fx.service;

import com.ahlenius.revent3fx.entity.*;
import com.ahlenius.revent3fx.exception.NoItemFoundException;
import com.ahlenius.revent3fx.repository.*;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class ItemService {
    private BouncyCastleRepoImpl bouncyCastleRepo;
    private DiscoMachineRepoImpl discoMachineRepo;
    private MascoteCostumeRepoImpl mascoteCostumeRepo;

    public ItemService() {
    }

    public ItemService(BouncyCastleRepoImpl bouncyCastleRepo, DiscoMachineRepoImpl discoMachineRepo, MascoteCostumeRepoImpl mascoteCostumeRepo) {
        this.bouncyCastleRepo = bouncyCastleRepo;
        this.discoMachineRepo = discoMachineRepo;
        this.mascoteCostumeRepo = mascoteCostumeRepo;
    }
// skapa ny produkt
public void newCostumeItem(String name, String description, double dayPrice, String season){
    Costume costume = new Costume(name, description, new BigDecimal(dayPrice), season);
    mascoteCostumeRepo.saveItem(costume);
    }
public void newBouncyItem(String name, String description, double dayprice,boolean indoor){
    BouncyCastle bouncyCastle = new BouncyCastle(name, description,new BigDecimal(dayprice),indoor);
    bouncyCastleRepo.saveItem(bouncyCastle);
    }
public void newDiscoItem(String name, String description, double dayPrice) {
    DiscoMachine discoMachine = new DiscoMachine(name, description,new BigDecimal(dayPrice));
    discoMachineRepo.saveItem(discoMachine);
    }

 // delete
    public void deleteItem(Costume costume){
    mascoteCostumeRepo.removeItem(costume);
    }
    public void deleteItem(BouncyCastle bouncyCastle){
    bouncyCastleRepo.removeItem(bouncyCastle);
    }
    public void deleteItem(DiscoMachine discoMachine){
    discoMachineRepo.removeItem(discoMachine);
    }
//Uppdateringar
//Pris
    public Costume updateItemPrice(Costume costume, double newPrice){
        costume.setDayPrice(new BigDecimal(newPrice));
        return mascoteCostumeRepo.updateItem(costume);
    }
    public BouncyCastle updateItemPrice (BouncyCastle bouncyCastle,double newPrice){
        bouncyCastle.setDayPrice(new BigDecimal(newPrice));
        return bouncyCastleRepo.updateItem(bouncyCastle);
            }
    public DiscoMachine updateItemPrice (DiscoMachine discoMachine,double newPrice){
        discoMachine.setDayPrice(new BigDecimal(newPrice));
        return discoMachineRepo.updateItem(discoMachine);
    }
//Prodnamn
public Costume updateItemName(Costume costume, String newName){
        costume.setProductName(newName);
    return mascoteCostumeRepo.updateItem(costume);
}
    public BouncyCastle updateItemName(BouncyCastle bouncyCastle, String newName){
        bouncyCastle.setProductName(newName);
        return bouncyCastleRepo.updateItem(bouncyCastle);
    }
    public DiscoMachine updateItemName (DiscoMachine discoMachine, String newName){
        discoMachine.setProductName(newName);
        return discoMachineRepo.updateItem(discoMachine);
    }
// Beskrivning
    public Costume updateItemDesc(Costume costume,String descript){
        costume.setDescription(descript);
    return mascoteCostumeRepo.updateItem(costume);}

    public BouncyCastle updateItemDesc(BouncyCastle bouncyCastle,String descript){
        bouncyCastle.setDescription(descript);
       return bouncyCastleRepo.updateItem(bouncyCastle);}

    public DiscoMachine updateItemDesc(DiscoMachine discoMachine,String descript){
        discoMachine.setDescription(descript);
        return discoMachineRepo.updateItem(discoMachine);}

// sök produkt

    public Object sortByRentalType(RentalType rentalType,String name){
         return switch(rentalType){
            case RentalType.BOUNCYCASTLE  -> findBCByName(name);
            case RentalType.MASCOTECOSTUME -> findCByName(name);
            case RentalType.DISCOMACHINE ->  findDMByName(name);
        };}

    public BouncyCastle findBCByName(String name){
     return   bouncyCastleRepo.findProductByName(name).orElseThrow(() -> new NoItemFoundException("Hittade ingen hoppborg med det namnet."));
    }
    public Costume findCByName(String name){
        return mascoteCostumeRepo.findProductByName(name).orElseThrow(() -> new NoItemFoundException("Hittade ingen maskeraddräkt med det namnet."));
    }
    public DiscoMachine findDMByName(String name){
        return discoMachineRepo.findProductByName(name).orElseThrow(() -> new NoItemFoundException("Hittade ingen disco produkt med det namnet."));
    }
    public void checkItem(String pNameHolder)
    { }

    public  Optional connectItemAndRentalByRentaType(Rental rental){ // returnerar denna hela objektet?
        return switch(rental.getRentalType()){
            case BOUNCYCASTLE -> bouncyCastleRepo.findById(rental.getProductId());
            case DISCOMACHINE -> discoMachineRepo.findById(rental.getProductId());
            case MASCOTECOSTUME -> mascoteCostumeRepo.findById(rental.getProductId());
        };
     }

     public List<BouncyCastle> returnListBouncyItem(){
      return  bouncyCastleRepo.findAllItems();
     }
    public List<Costume> returnListCostumeItem(){
        return  mascoteCostumeRepo.findAllItems();
    }
    public List<DiscoMachine> returnListDiscoItem(){
        return  discoMachineRepo.findAllItems();
    }



}
