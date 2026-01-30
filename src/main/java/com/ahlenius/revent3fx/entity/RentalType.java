package com.ahlenius.revent3fx.entity;

public enum RentalType {
BOUNCYCASTLE ("Hoppborg"),
DISCOMACHINE("Disco"),
MASCOTECOSTUME("Dräkt");

private final String swedish;

RentalType(String swedish){
    this.swedish = swedish;
}

    @Override
    public String toString() {
        return swedish;
    }
}
