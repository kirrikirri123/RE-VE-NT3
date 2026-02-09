package com.ahlenius.revent3fx.repository;

import com.ahlenius.revent3fx.entity.*;
import com.ahlenius.revent3fx.util.HibernateUtil;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MemberRepoImplTest {
    //Integrationstest

    private static SessionFactory sessionfactory;

    private MemberRepoImpl memberRepo;
    private MascoteCostumeRepoImpl mascoteCostumeRepo;
    private RentalRepoImpl rentalRepo;

    @BeforeAll
    static void beforeAll(){
        sessionfactory = HibernateUtil.getSessionFactory();
    }
    // configurerar fel databas. Test Integrationstest fungerar ej.

    @AfterAll
    static void afterAll(){
        HibernateUtil.shutdown();
    }

    @BeforeEach
    void setUp(){
        memberRepo = new MemberRepoImpl(sessionfactory);
        mascoteCostumeRepo = new MascoteCostumeRepoImpl(sessionfactory);
        rentalRepo = new RentalRepoImpl(sessionfactory);
    }
    @Test
    void save_and_find_rental(){

        Member member = new Member("Förnamn","Efternamn", "0701234578","mail@mail.com", MemberStatus.EMPLOYEE);
        memberRepo.saveMember(member);

        Costume costume = new Costume("Clown","Rödnäsa, stora skor",new BigDecimal(450),"Vår");
        mascoteCostumeRepo.saveItem(costume);

        Rental rental = new Rental(member, costume.getProductId(), RentalType.MASCOTECOSTUME,5, LocalDate.now(),false);
        rentalRepo.saveRental(rental);

        List<Rental> rentalList = rentalRepo.findRentalList();
        Rental foundRental = rentalList.getFirst();

        assertNotNull(foundRental,"Borde inte vara null");
        assertEquals("Förnamn", foundRental.getMember().getfname(), "Värden ska matcha");
        assertEquals(costume.getProductId(),foundRental.getProductId(),"Värden ska matcha");

    }

    @Test
    void update_member_save_and_find_changes(){

        Member member = new Member("Test","test","0703215485","test@testmail.com",MemberStatus.EMPLOYEE);
        memberRepo.saveMember(member);

        assertNotNull(member.getMemberId(),"Ska ha ett id");
        assertNotNull(member,"Ska innehålla medlem");

        member.setMemberStatus(MemberStatus.PRIVATEINDIVIDUAL);
        member.setlname("Testsson");
        memberRepo.updateMember(member);

        List<Member> memberList = memberRepo.findAllMembers();
        Member foundMember = memberList.getFirst();
        assertEquals(member.getMemberId(),foundMember.getMemberId(),"Värden ska matcha");
        assertEquals(member.getlname(),foundMember.getlname(), "Värden ska matcha");
        assertEquals(member.getMemberStatus(),foundMember.getMemberStatus(), "Värden ska matcha");

    }



}