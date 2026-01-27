package com.ahlenius.revent3fx.service;

import com.ahlenius.revent3fx.entity.Member;
import com.ahlenius.revent3fx.entity.MemberStatus;
import com.ahlenius.revent3fx.exception.InvalidMemberInfoInputException;
import com.ahlenius.revent3fx.exception.InvalidNameInputException;
import com.ahlenius.revent3fx.exception.InvalidPhoneInputException;
import com.ahlenius.revent3fx.repository.MemberRepoImpl;

import java.io.IOException;
import java.util.List;

public class MemberService {
    private MemberRepoImpl memberRepo;

    public MemberService() {
    }
    public MemberService(MemberRepoImpl memberRepo){
        this.memberRepo = memberRepo;
    }
    //Skapa, spara ny medlem
    public void newMember(String lname, String fname, String phone, String email, MemberStatus memberStatus) throws IOException {
       if(!phone.startsWith("07")) // lägga till nått mer??
        {throw new InvalidPhoneInputException("Dubbelkolla ditt mobilnummer. Ex. 070 123 45 67");}
        if(fname.equalsIgnoreCase("bajs"))
        {throw new InvalidNameInputException("STOPP! Bajs är inte ett godkänt namn");}
        if(fname.isEmpty()||lname.isEmpty()||email.isEmpty())
        {throw new InvalidMemberInfoInputException("Dubbelkolla att alla fält är ifyllda.");}
        else {Member member = new Member(fname,lname,phone,email,memberStatus);
            memberRepo.saveMember(member); }}

    // Söka och ändra medlem.

    public void updateMemberName(Member member,String newfName, String newlName){
        member.setlName(newfName);
        member.setfName(newlName);
        memberRepo.updateMember(member);
    }
    public void updateMemberPhone(Member member, String phone){
        member.setPhone(phone);
        memberRepo.updateMember(member);
    }
    public void updateMemberStatus(Member member, String status) {
        switch(status){
            case "Privatperson"  -> member.setMemberStatus(MemberStatus.PRIVATEINDIVIDUAL);
            case "Förening" -> member.setMemberStatus(MemberStatus.SOCIETY);
            case "Anställd" -> member.setMemberStatus(MemberStatus.EMPLOYEE);
        }
    }
    public Member searchAndReturnMemberByEmail(String email){
        return memberRepo.findMemberByEmail(email);
    }

    public List<Member> searchMemberByEmailReturnList(String fname){
        return memberRepo.findMemberByFname(fname);
    }

}
