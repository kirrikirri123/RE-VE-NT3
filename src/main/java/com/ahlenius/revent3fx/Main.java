package com.ahlenius.revent3fx;

import com.ahlenius.revent3fx.repository.MemberRepoImpl;
import com.ahlenius.revent3fx.util.HibernateUtil;
import org.hibernate.SessionFactory;

import java.util.Scanner;

public class Main {

public static void main(String [] args){
    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    var memberRepo = new MemberRepoImpl(sessionFactory);

    System.out.println("This is the rental-app of your dreams.");


    System.out.println("Skriv namnet på den du söker: ");
    Scanner scan = new Scanner(System.in);
    String name = scan.nextLine().trim();

    var memberFound= memberRepo.findMemberByName(name);

    if (memberFound!= null){ System.out.println("Hittade i registret :\n"+ memberFound );}
    else {System.out.println("Hittade ingen sådan medlem");}




}

}
