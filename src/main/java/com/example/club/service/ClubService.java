package com.example.club.service;

import com.example.club.model.Club;
import com.example.club.model.ClubWithBLOBs;

import java.sql.SQLException;
import java.util.List;

public interface ClubService {
    List<Club> findClubs();

    Club findClubById(Integer id);

    int createClub(ClubWithBLOBs club) throws SQLException;
}
