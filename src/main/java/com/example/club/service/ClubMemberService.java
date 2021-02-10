package com.example.club.service;

import com.example.club.model.ClubMember;

import java.util.List;

public interface ClubMemberService {
    List<ClubMember> getClubMembers(Integer clubId);
}
