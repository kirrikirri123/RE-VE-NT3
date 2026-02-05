package com.ahlenius.revent3fx.service;

import com.ahlenius.revent3fx.entity.Member;
import com.ahlenius.revent3fx.entity.MemberStatus;
import com.ahlenius.revent3fx.exception.InvalidMemberInfoInputException;
import com.ahlenius.revent3fx.exception.InvalidNameInputException;
import com.ahlenius.revent3fx.exception.InvalidPhoneInputException;
import com.ahlenius.revent3fx.repository.MemberRepoImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class MemberServiceTest {

    private MemberRepoImpl memberRepo;
    private MemberService service;

    @BeforeEach
    void setUp(){
        memberRepo = mock(MemberRepoImpl.class); // Mockad databas.
        service = new MemberService(memberRepo);

    }

    @Test
    void newMember_shouldThrowExceptionIfPhoneInvalid() {
    //Testar så exceptionkastas och vi cike går in i save metoden i repoklassen.
        assertThrows(InvalidPhoneInputException.class,()-> service.newMember("Förnamn","Efternamn","0","mail@mail.com", MemberStatus.EMPLOYEE));
        verify(memberRepo,never()).saveMember(any(Member.class));
    }

    @Test
     void newMember_shouldThrowExceptionIfNameIsBajs(){
        //Testar så exceptionkastas och vi cike går in i save metoden i repoklassen.
        assertThrows(InvalidNameInputException.class,()-> service.newMember("bajs","Efternamn","0703024810","mail@mail.com", MemberStatus.EMPLOYEE));
        verify(memberRepo,never()).saveMember(any(Member.class));
    }

    @Test
    void newMember_shouldThrowExceptionIfNoNameOrEmail(){
        assertThrows(InvalidMemberInfoInputException.class,()-> service.newMember("","Efternamn","0703024810","", MemberStatus.EMPLOYEE));
        verify(memberRepo,never()).saveMember(any(Member.class));
    }

    @Test
    void newMember_shouldSaveMemberIfInputCorrect(){
        //Testar om den går in i sameMember metoden.
        service.newMember("Förnamn","Efternamn","0703024810","mail@mail.com", MemberStatus.EMPLOYEE);

        verify(memberRepo).saveMember(any(Member.class));
    }


    @Test
    void createMemberStatus() {
        //Arrage
        //Act
        //Assert
    }

    //Lägg till nått gränsvärdes test? vad finns de för gränsvärden?
}