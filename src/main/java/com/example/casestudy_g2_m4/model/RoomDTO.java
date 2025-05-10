package com.example.casestudy_g2_m4.model;

import jakarta.validation.constraints.*;

public class RoomDTO {

    private Integer id;
    @NotBlank(message = "Room number is required")
    @Pattern(regexp = "\\d{3}", message = "Room number must be 3 digits")
    private String roomNumber;

    @NotNull(message = "Room type must be selected")
    private Integer roomTypeId;

    @NotBlank(message = "Status must be selected")
    private String status;

    private String imageUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Integer getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(Integer roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
