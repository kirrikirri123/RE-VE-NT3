package com.ahlenius.revent3fx.service;

import com.ahlenius.revent3fx.repository.*;

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
// sök produkt

//uppdateraprodukt

//tabort produkt

// skapa ny produkt


}
