package com.ahlenius.revent3fx;

import com.ahlenius.revent3fx.entity.BouncyCastle;
import com.ahlenius.revent3fx.entity.DiscoMachine;
import com.ahlenius.revent3fx.entity.Member;
import com.ahlenius.revent3fx.entity.MemberStatus;
import com.ahlenius.revent3fx.repository.BouncyCastleRepoImpl;
import com.ahlenius.revent3fx.repository.DiscoMachineRepoImpl;
import com.ahlenius.revent3fx.repository.MascoteCostumeRepoImpl;
import com.ahlenius.revent3fx.repository.MemberRepoImpl;
import com.ahlenius.revent3fx.util.HibernateUtil;
import org.hibernate.SessionFactory;

import java.math.BigDecimal;
import java.util.Scanner;

public class Main {

public static void main(String [] args){
    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    var memberRepo = new MemberRepoImpl(sessionFactory);
    var bouncyRepo = new BouncyCastleRepoImpl(sessionFactory);
    var discoRepo = new DiscoMachineRepoImpl(sessionFactory);
    var mascoteRepo = new MascoteCostumeRepoImpl(sessionFactory);







    System.out.println("This is the rental-app of your dreams.");
    boolean run = true;

    while(run){
    System.out.println("Skriv namnet på den du söker: ");
    Scanner scan = new Scanner(System.in);
    String name = scan.nextLine().trim();

    var memberFound= memberRepo.findMemberByName(name);
    if (memberFound!= null){ System.out.println("Hittade i registret :\n"+ memberFound );}
    else {System.out.println("Hittade ingen sådan medlem");}

    memberFound.setlName("Braxbralla");
    var memberReturned=memberRepo.updateMember(memberFound);

    System.out.println("\nNu med updaterat efternamn: "+ memberReturned);

        /*var bouncyFound = bouncyRepo.findProductByName(" ");
        if(bouncyFound!= null) {
            System.out.println("Ur skattkistan: " + bouncyFound);
            System.out.println("Ska vi ta bort produkten?");
            String answer = scan.nextLine();
            if (answer.equalsIgnoreCase("JA")) {
                bouncyRepo.removeItem(bouncyFound);
            }else{System.out.println("Ok, den får vara kvar.");}
        }
        var afterDelete = bouncyRepo.findProductByName("Ultimate Extreme");
         if(afterDelete==null){System.out.println("Produkten är raderad.");}


        DiscoMachine discoProd = discoRepo.findProductByName("Discokula");
         if(discoProd!= null){
        System.out.println(discoProd);} else {System.out.println("Hittade ingen sådan produkt i lager just nu.");}*/


System.out.println("Vill du avsluta? Skriv JA");
    String ja = scan.nextLine();
    if(ja.equalsIgnoreCase("JA")){run=false;}



}
}
}
