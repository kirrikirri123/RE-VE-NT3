package com.ahlenius.revent3fx.service;

import com.ahlenius.revent3fx.entity.Member;
import com.ahlenius.revent3fx.entity.MemberStatus;
import com.ahlenius.revent3fx.entity.Rental;
import com.ahlenius.revent3fx.entity.RentalType;
import com.ahlenius.revent3fx.repository.RentalRepoImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RentalServiceTest {
    @Mock
    private RentalRepoImpl rentalRepo;
    @InjectMocks
    private RentalService service;
    private PricingService priceService = new PricingService();

    @Test
    void calculateDay_shouldCalculateAndReturnPrice() {
        BigDecimal test =priceService.calculateDay(new BigDecimal(350),3);
        assertEquals(BigDecimal.valueOf(1050),test);
    }
    @Test
    void calculateDay_shouldUsePriceMonthDiscountMethodAndReturnPrice() {
        //kollar gränsvärden om den går in och ger rebatt pga. hyrning  30 dagar eller längre.
        BigDecimal testPrice =priceService.calculateDay(new BigDecimal(350),40);
         assertEquals(BigDecimal.valueOf(9800.0),testPrice);
    }
    @Test
    void newRental_shouldCreateAndSaveNewRental(){
        Member member = new Member("Emmy","Lou","095014841","EM@msn.com", MemberStatus.PRIVATEINDIVIDUAL);
        Rental rental = new Rental(member,2L, RentalType.DISCOMACHINE,5, LocalDate.now(),false);
        when(rentalRepo.saveRental(any(Rental.class))).thenReturn(rental);

        Rental testRental = service.newRental(member,2L, RentalType.DISCOMACHINE,5, LocalDate.now(),false);

        assertNotNull(testRental);
        assertEquals(2L,testRental.getProductId());
        assertEquals(RentalType.DISCOMACHINE,testRental.getRentalType());
        verify(rentalRepo).saveRental(testRental);

    }


}