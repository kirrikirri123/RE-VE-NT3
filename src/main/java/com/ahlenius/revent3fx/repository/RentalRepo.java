package com.ahlenius.revent3fx.repository;


import com.ahlenius.revent3fx.entity.BouncyCastle;
import com.ahlenius.revent3fx.entity.Member;
import com.ahlenius.revent3fx.entity.Rental;

import java.util.List;

public interface RentalRepo {

    Rental saveRental(Rental rental);

    void removeRental(Rental rental);

    List<Rental> findRentalList();
}
