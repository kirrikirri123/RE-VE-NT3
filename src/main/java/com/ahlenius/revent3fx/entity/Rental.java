package com.ahlenius.revent3fx.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "rental")
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "rental_id")
    private long rentalId;
    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    @Column(name = "product_id", nullable = false)
    private long productId; // vart ska värdet in i detta?
    @Enumerated(EnumType.STRING)
    @Column(name="rental_type", nullable = false, length = 14)
    private RentalType rentalType;
    @Column(name= "rent_days",nullable = false)
    private int rentDays;
    @Column(name="start_of_rent", nullable = false)
    private LocalDate startOfRent;
    @Column(nullable = false)
    private boolean returned;

    public Rental (){}

    public Rental(Member member, long productId, RentalType rentalType, int rentDays, LocalDate startOfRent, boolean returned) {
        this.member = member;
        this.rentalType = rentalType;
        this.rentDays = rentDays;
        this.startOfRent = startOfRent;
        this.returned = returned;
    }
    // SETTER
    void setMember(Member member) {
        this.member = member;
    }

    public void setRentalType(RentalType rentalType) {
        this.rentalType = rentalType;
    }
    public void setReturned(boolean returned) {
        this.returned = returned;

    }

    //GETTER

    public Long getProductId() {
        return productId;}
    public long getRentalId() {
        return rentalId;
    }
    public Member getMember() {
        return member;
    }
    public RentalType getRentalType() {
        return rentalType;
    }
    public int getRentDays() {
        return rentDays;
    }
    public LocalDate getStartOfRent() {
        return startOfRent;
    }
    public boolean isReturned() {
        return returned;
    }

    @Override
    public String toString() {
        return member+" har hyrt en "+rentalType + ". From." + startOfRent;
    }
}
