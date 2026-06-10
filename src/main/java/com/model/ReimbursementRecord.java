package com.model;

import java.sql.Timestamp;

public class ReimbursementRecord {
    private int reimbursementId;
    private String reimbursingClub;
    private String reimbursementContent;
    private double reimbursementAmount;
    private String reimbursementStatus;
    private Timestamp lastStatusUpdateTime;
    private Timestamp recordCreationTime;

    // Getters and Setters
    public int getReimbursementId() {
        return reimbursementId;
    }
    public void setReimbursementId(int reimbursementId) {
        this.reimbursementId = reimbursementId;
    }
    public String getReimbursingClub() {
        return reimbursingClub;
    }
    public void setReimbursingClub(String reimbursingClub) {
        this.reimbursingClub = reimbursingClub;
    }
    public String getReimbursementContent() {
        return reimbursementContent;
    }
    public void setReimbursementContent(String reimbursementContent) {
        this.reimbursementContent = reimbursementContent;
    }
    public double getReimbursementAmount() {
        return reimbursementAmount;
    }
    public void setReimbursementAmount(double reimbursementAmount) {
        this.reimbursementAmount = reimbursementAmount;
    }
    public String getReimbursementStatus() {
        return reimbursementStatus;
    }
    public void setReimbursementStatus(String reimbursementStatus) {
        this.reimbursementStatus = reimbursementStatus;
    }
    public Timestamp getLastStatusUpdateTime() {
        return lastStatusUpdateTime;
    }
    public void setLastStatusUpdateTime(Timestamp lastStatusUpdateTime) {
        this.lastStatusUpdateTime = lastStatusUpdateTime;
    }
    public Timestamp getRecordCreationTime() {
        return recordCreationTime;
    }
    public void setRecordCreationTime(Timestamp recordCreationTime) {
        this.recordCreationTime = recordCreationTime;
    }
}
