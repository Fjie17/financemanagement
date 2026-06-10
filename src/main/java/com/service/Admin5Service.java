package com.service;

import java.util.List;
import com.dao.Admin5Dao;
import com.model.Suggestion;

public class Admin5Service {
    private Admin5Dao admin5Dao;

    public Admin5Service() {
        admin5Dao = new Admin5Dao();
    }

    public List<Suggestion> getAllSuggestions() {
        return admin5Dao.getAllSuggestions();
    }
}
