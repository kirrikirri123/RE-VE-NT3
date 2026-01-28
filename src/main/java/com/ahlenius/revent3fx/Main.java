package com.ahlenius.revent3fx;

import com.ahlenius.revent3fx.userInterface.ReventApp;
import javafx.application.Application;

public class Main {

public static void main(String [] args){
    Application.launch(ReventApp.class);
/*
    System.out.println("This is the rental-app of your dreams.");
    boolean run = true;

    while(run){
    System.out.println("Skriv namn på den du söker: ");
    Scanner scan = new Scanner(System.in);
    String name = scan.nextLine().trim();

    var memberFound= memberRepo.findMemberByFname(name);
    if(memberFound!=null){
    memberFound.forEach(System.out::println);}
    else {System.out.println("Hittade ingen sådan medlem");}


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
        System.out.println(discoProd);} else {System.out.println("Hittade ingen sådan produkt i lager just nu.");}


System.out.println("Vill du avsluta? Skriv JA");
    String ja = scan.nextLine();
    if(ja.equalsIgnoreCase("JA")){run=false;}



}*/
}
}
