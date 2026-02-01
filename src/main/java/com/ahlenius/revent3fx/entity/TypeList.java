package com.ahlenius.revent3fx.entity;

import java.util.ArrayList;
import java.util.List;

public class TypeList {
    private final List<RentalType> RentalInString = new ArrayList<>();

    public TypeList(){}


    public void listOfRentalType(){
        RentalInString.add(RentalType.BOUNCYCASTLE);
        RentalInString.add(RentalType.MASCOTECOSTUME);
        RentalInString.add(RentalType.DISCOMACHINE);
    }

    public List<RentalType> getRentalInString() {
        return RentalInString;
    }
}
