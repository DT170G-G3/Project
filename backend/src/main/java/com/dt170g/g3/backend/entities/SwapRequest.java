package com.dt170g.g3.backend.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "swap_request")
public class SwapRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="sender_id")
    private String senderId;
    @Column(name="receiver_id")
    private String receiverId;
    @Column(name="shift_id")
    private int shiftId;

    public SwapRequest() {}

    public int getId() {
        return id;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public int getShiftId() {
        return shiftId;
    }

    public void setShiftId(int shiftId) {
        this.shiftId = shiftId;
    }
}
