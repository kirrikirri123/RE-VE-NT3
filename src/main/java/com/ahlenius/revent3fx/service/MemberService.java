package com.ahlenius.revent3fx.service;

import com.ahlenius.revent3fx.Main;
import com.ahlenius.revent3fx.entity.Member;
import com.ahlenius.revent3fx.entity.MemberStatus;
import com.ahlenius.revent3fx.exception.*;
import com.ahlenius.revent3fx.repository.MemberRepoImpl;
import org.hibernate.HibernateException;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class MemberService {
    private MemberRepoImpl memberRepo;

    public MemberService() {
    }

    public MemberService(MemberRepoImpl memberRepo) {
        this.memberRepo = memberRepo;
    }

    //Skapa, spara ny medlem
    public void newMember(String fname, String lname, String phone, String email, MemberStatus memberStatus) {
        if (!phone.startsWith("07")) // lägga till nått mer??
        { throw new InvalidPhoneInputException("Dubbelkolla ditt mobilnummer. Ex. 070 123 45 67");
        }
        if (fname.equalsIgnoreCase("bajs")) {
            throw new InvalidNameInputException("STOPP! Bajs är inte ett godkänt namn");
        }
        if (fname.isEmpty() || lname.isEmpty() || email.isEmpty()) {
            throw new InvalidMemberInfoInputException("Dubbelkolla att alla fält är ifyllda.");
        } else {
            Member member = new Member(fname, lname, phone, email, memberStatus);
            memberRepo.saveMember(member);
        }
    }

    public MemberStatus createMemberStatus(String status) {
        return switch (status) {
            case "Privatperson" -> MemberStatus.PRIVATEINDIVIDUAL;
            case "Förening" -> MemberStatus.SOCIETY;
            case "Kollega" -> MemberStatus.EMPLOYEE;
            default -> throw new IllegalStateException("Ogiltig medlemsstatus.");
        };

    }

    // Uppdatera medlem

    public void updateMemberName(Member member, String newfName, String newlName) {
        member.setfname(newfName);
        member.setlname(newlName);
        memberRepo.updateMember(member);
    }

    public void updateMemberPhone(Member member, String phone) {
        member.setPhone(phone);
        memberRepo.updateMember(member);
    }

    public void updateMemberStatus(Member member, String status) {
        switch (status) {
            case "Privatperson" -> member.setMemberStatus(MemberStatus.PRIVATEINDIVIDUAL);
            case "Förening" -> member.setMemberStatus(MemberStatus.SOCIETY);
            case "Kollega" -> member.setMemberStatus(MemberStatus.EMPLOYEE);
        }
    }
    public void deleteMember(Member member){ // behöver få in koll om den har några aktiva uthyrningar. Isf får den ej hyra. Vad händer om detta inte funkar? Då vill jag kasta ett exception??
        try {memberRepo.deleteMember(member);}catch (HibernateException e){throw new MemberDeleteException("Medlemskap gick ej att avsluta.");
        }}

    //Sök

    public Member searchAndReturnMemberByEmail(String email) throws NoMemberFoundException {
        return memberRepo.findMemberByEmail(email).orElseThrow(() -> new NoMemberFoundException("Hittade ingen matchande medlem."));}
// returnerar ovan verkligen ett member objekt??

    public List<Member> searchMemberByEmailReturnList(String fname) throws NoMemberFoundException {
        List<Member> ListMembers = memberRepo.findMemberByFname(fname);
        if(ListMembers== null){throw new NoMemberFoundException("Hittade ingen matchande medlem.");}
        return ListMembers;
    }

    public List<Member> findAllMembers() {
        return memberRepo.findAllMembers();
    }





}