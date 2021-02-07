package com.example.club.mapper;

import com.example.club.model.ClubMember;
import com.example.club.model.ClubMemberKey;

public interface ClubMemberMapper {
    int deleteByPrimaryKey(ClubMemberKey key);

    int insert(ClubMember record);

    int insertSelective(ClubMember record);

    ClubMember selectByPrimaryKey(ClubMemberKey key);

    int updateByPrimaryKeySelective(ClubMember record);

    int updateByPrimaryKey(ClubMember record);
}