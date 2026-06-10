package com.service;

import com.dao.SuggestionDao;
import com.model.Suggestion;

public class SuggestionService {
    private SuggestionDao suggestionDao = new SuggestionDao();

    public boolean addSuggestion(Suggestion suggestion) {
        return suggestionDao.insertSuggestion(suggestion);
    }
}
