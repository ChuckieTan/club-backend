package com.example.club.mapper;

import com.example.club.model.ClubMember;
import com.example.club.model.ClubMemberKey;

import java.util.List;

public interface ClubMemberMapper {
    int deleteByPrimaryKey(ClubMemberKey key);

    int insert(ClubMember record);

    int insertSelective(ClubMember record);

    ClubMember selectByPrimaryKey(ClubMemberKey key);

    int updateByPrimaryKeySelective(ClubMember record);

    int updateByPrimaryKeyWithBLOBs(ClubMember record);

    int updateByPrimaryKey(ClubMember record);

    List<ClubMember> selectByClubId(Integer clubId);

    List<ClubMember> selectByUserId(Integer userId);

    List<ClubMember> selectByClubIdAndRole(Integer clubId, Integer role);

    int deleteByClubId(Integer clubId);
}