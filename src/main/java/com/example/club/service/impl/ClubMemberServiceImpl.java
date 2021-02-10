package com.example.club.service.impl;

import com.example.club.mapper.ClubMemberMapper;
import com.example.club.model.ClubMember;
import com.example.club.service.ClubMemberService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ClubMemberServiceImpl implements ClubMemberService {
    @Resource
    ClubMemberMapper clubMemberMapper;

    @Override
    public List<ClubMember> getClubMembers(Integer clubId) {
        return clubMemberMapper.selectByClubId(clubId);
    }
}