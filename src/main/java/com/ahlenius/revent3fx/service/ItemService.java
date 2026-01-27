package com.ahlenius.revent3fx.service;

import com.ahlenius.revent3fx.entity.BouncyCastle;
import com.ahlenius.revent3fx.entity.Costume;
import com.ahlenius.revent3fx.entity.DiscoMachine;
import com.ahlenius.revent3fx.repository.*;

import java.io.IOException;
import java.math.BigDecimal;

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

// Lägg in här sorteringen vid hämtande av rentaltype och koppla mot rentalid för att bedömma vilken produkt som det avser.
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
//Uppdatera

    public Costume updateItem (Costume costume){
        return mascoteCostumeRepo.updateItem(costume);
    }
    public BouncyCastle updateItem (BouncyCastle bouncyCastle){
        return bouncyCastleRepo.updateItem(bouncyCastle);
    }
    public DiscoMachine updateItem (DiscoMachine discoMachine){
        return discoMachineRepo.updateItem(discoMachine);
    }

//tabort produkt

// sök produkt


}
