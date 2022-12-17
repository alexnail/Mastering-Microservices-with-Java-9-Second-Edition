package com.packtpub.mmj.booking.domain.model.entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class Booking extends BaseEntity<String> {

    private String restaurantId;
    private String userId;
    private LocalDate date;
    private LocalTime time;
    private String tableId;

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Booking(String id, String name, String restaurantId, String tableId, String userId, LocalDate date,
            LocalTime time) {
        super(id, name);
        this.restaurantId = restaurantId;
        this.tableId = tableId;
        this.userId = userId;
        this.date = date;
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format(
                "{id: %s, name: %s, userId: %s, restaurantId: %s, tableId: %s, date: %s, time: %s}",
                id, name, userId, restaurantId, tableId, date, time);
    }
}
