package com.ahlenius.revent3fx.repository;

import com.ahlenius.revent3fx.entity.Member;

public interface MemberRepo {

    void saveMember(Member member);

    void deleteMember(Member member);

    Member updateMember(Member me);
}
