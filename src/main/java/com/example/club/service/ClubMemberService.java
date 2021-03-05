package com.example.club.service;

import com.example.club.model.ClubMember;

import java.util.List;

public interface ClubMemberService {
    List<ClubMember> getClubMembers(Integer clubId);

    List<ClubMember> getUserClubs(Integer userId);

    int newClubMember(ClubMember clubMember);

    List<ClubMember> getApplyMembers(Integer clubId);

    int setUserRole(ClubMember clubMember);

    int deleteMember(Integer clubId, Integer userId);

    ClubMember getClubMemberInfo(Integer clubId, Integer userId);

    int changeClubMemberInfo(ClubMember clubMember);

    int deleteClubAllMembers(Integer clubId);

    ClubMember getClubPresident(Integer clubId);
}
