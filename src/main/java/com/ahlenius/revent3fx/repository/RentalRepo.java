package com.ahlenius.revent3fx.repository;


import com.ahlenius.revent3fx.entity.BouncyCastle;
import com.ahlenius.revent3fx.entity.Member;
import com.ahlenius.revent3fx.entity.Rental;

public interface RentalRepo {

    void saveRental(Rental rental);

    void removeRental(Rental rental);

}
