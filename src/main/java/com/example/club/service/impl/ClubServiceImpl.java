package com.example.club.service.impl;

import com.example.club.mapper.ClubMapper;
import com.example.club.model.Club;
import com.example.club.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ClubServiceImpl implements ClubService {

    @Resource
    private ClubMapper clubMapper;

    @Override
    public List<Club> findClubs() {
        return (List<Club>) clubMapper.selectByPrimaryKey(1);
    }

    @Override
    public Club findClubById(Integer id) {
        return clubMapper.selectByPrimaryKey(id);
    }

}
