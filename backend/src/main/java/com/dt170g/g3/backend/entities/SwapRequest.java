package com.dt170g.g3.backend.entities;

import com.dt170g.g3.backend.SwapStatus;
import jakarta.persistence.*;


@NamedQuery(name= "SwapRequest.getAllRequests",
        query= "SELECT req FROM SwapRequest req")
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

    @Enumerated(EnumType.STRING)
    private SwapStatus status;

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

    public SwapStatus getStatus(){
        return this.status;
    }

    public void setStatus(SwapStatus status){
        this.status = status;
    }
}
