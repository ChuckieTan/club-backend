package com.example.club.service;

import com.example.club.model.ClubWithBLOBs;
import com.example.club.util.PageRequest;
import com.example.club.util.PageResult;

import java.sql.SQLException;
import java.util.List;

public interface ClubService {
    List<ClubWithBLOBs> findClubs();

    PageResult findAllClubsByPage(PageRequest pageRequest);

    ClubWithBLOBs findClubById(Integer id);

    int createClub(ClubWithBLOBs club) throws SQLException;

    int changeClubInfo(ClubWithBLOBs club);

    List<ClubWithBLOBs> getCreatedClubs(Integer initiatorId);

    List<ClubWithBLOBs> getApplyClubs();

    int deleteClubById(Integer clubId);

    PageResult searchClubsByPage(String word, PageRequest pageRequest);

    PageResult findClubsByProgress(PageRequest pageRequest, Integer progress);

    List<ClubWithBLOBs> findAllClubsByProgress(Integer progress);
}
