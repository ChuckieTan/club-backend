package com.example.club.service.impl;

import com.example.club.mapper.ClubMemberMapper;
import com.example.club.model.ClubMember;
import com.example.club.model.ClubMemberKey;
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

    @Override
    public List<ClubMember> getUserClubs(Integer userId) {
        return clubMemberMapper.selectByUserId(userId);
    }

    @Override
    public int newClubMember(ClubMember clubMember) {
        return clubMemberMapper.insertSelective(clubMember);
    }

    @Override
    public List<ClubMember> getApplyMembers(Integer clubId) {
        return clubMemberMapper.selectByClubAndRole(clubId, 3);
    }

    @Override
    public int setUserRole(ClubMember clubMember) {
        return clubMemberMapper.updateByPrimaryKeySelective(clubMember);
    }

    @Override
    public int deleteMember(Integer clubId, Integer userId) {
        return clubMemberMapper.deleteByPrimaryKey(new ClubMemberKey(clubId, userId));
    }

    @Override
    public ClubMember getClubMemberInfo(Integer clubId, Integer userId) {
        return clubMemberMapper.selectByPrimaryKey(new ClubMemberKey(clubId, userId));
    }

    @Override
    public int changeClubMemberInfo(ClubMember clubMember) {
        return clubMemberMapper.updateByPrimaryKeySelective(clubMember);
    }

    @Override
    public int deleteClubAllMembers(Integer clubId) {
        return clubMemberMapper.deleteByClubId(clubId);
    }
}
