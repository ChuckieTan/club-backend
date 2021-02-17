package com.example.club.mapper;

import com.example.club.model.Club;
import com.example.club.model.ClubWithBLOBs;

import java.util.List;

public interface ClubMapper {
    int deleteByPrimaryKey(Integer clubId);

    int insert(ClubWithBLOBs record);

    int insertSelective(ClubWithBLOBs record);

    ClubWithBLOBs selectByPrimaryKey(Integer clubId);

    List<ClubWithBLOBs> selectAll();

    int updateByPrimaryKeySelective(ClubWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ClubWithBLOBs record);

    int updateByPrimaryKey(Club record);

    List<ClubWithBLOBs> selectByInitiatorId(Integer initiatorId);
}