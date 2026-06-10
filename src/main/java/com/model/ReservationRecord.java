package com.model;

public class ReservationRecord {
    private int reservationId;
    private String reservingClub;
    private String venue;
    private String reservationTimeStart;
    private String reservationTimeEnd;
    private String reservationStatus;
    private String recordCreationTime;

    // Getters and Setters
    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public String getReservingClub() {
        return reservingClub;
    }

    public void setReservingClub(String reservingClub) {
        this.reservingClub = reservingClub;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getReservationTimeStart() {
        return reservationTimeStart;
    }

    public void setReservationTimeStart(String reservationTimeStart) {
        this.reservationTimeStart = reservationTimeStart;
    }

    public String getReservationTimeEnd() {
        return reservationTimeEnd;
    }

    public void setReservationTimeEnd(String reservationTimeEnd) {
        this.reservationTimeEnd = reservationTimeEnd;
    }

    public String getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(String reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    public String getRecordCreationTime() {
        return recordCreationTime;
    }

    public void setRecordCreationTime(String recordCreationTime) {
        this.recordCreationTime = recordCreationTime;
    }
}
