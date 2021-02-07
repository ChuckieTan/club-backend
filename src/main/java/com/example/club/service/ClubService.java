package com.example.club.service;

import com.example.club.model.Club;

import java.util.List;

public interface ClubService {
    List<Club> findClubs();
    Club findClubById(Integer id);
}
