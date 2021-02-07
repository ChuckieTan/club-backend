package com.example.club.mapper;

import com.example.club.model.Club;
import com.example.club.model.ClubWithBLOBs;

public interface ClubMapper {
    int deleteByPrimaryKey(Integer clubId);

    int insert(ClubWithBLOBs record);

    int insertSelective(ClubWithBLOBs record);

    ClubWithBLOBs selectByPrimaryKey(Integer clubId);

    int updateByPrimaryKeySelective(ClubWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ClubWithBLOBs record);

    int updateByPrimaryKey(Club record);
}