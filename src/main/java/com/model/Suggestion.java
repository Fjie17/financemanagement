package com.model;

import java.sql.Timestamp;

public class Suggestion {
    private int suggestionID;
    private String clubName;
    private String content;
    private Timestamp SubmissionTime;

    // Getters and Setters
    public int getId() {
        return suggestionID;
    }
    public void setId(int id) {
        this.suggestionID = suggestionID;
    }

    public String getClubName() {
        return clubName;
    }
    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
	public void setSubmissionTime(Timestamp timestamp) {
		this.SubmissionTime =  SubmissionTime;
		
	}
}
