package com.example.casestudy_g2_m4.model;

import jakarta.persistence.*;

@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "room_type_id", nullable = false)
    private RoomType roomType;

    @Column(name = "room_number", nullable = false)
    private String roomNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    public enum Status {
        available, booked, maintenance
    }
    @Column(name = "image_url")
    private String imageUrl;

    // Getters and Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public RoomType getRoomType() { return roomType; }
    public void setRoomType(RoomType roomType) { this.roomType = roomType; }
    public String getRoomNumber() { return roomNumber; }
    public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}