package com.example.club.service;

import com.example.club.model.ClubWithBLOBs;

import java.sql.SQLException;
import java.util.List;

public interface ClubService {
    List<ClubWithBLOBs> findClubs();

    ClubWithBLOBs findClubById(Integer id);

    int createClub(ClubWithBLOBs club) throws SQLException;

    int changeClubInfo(ClubWithBLOBs club);

    List<ClubWithBLOBs> getCreatedClubs(Integer initiatorId);
}
