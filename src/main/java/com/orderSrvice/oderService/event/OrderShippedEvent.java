package com.orderSrvice.oderService.event;
import lombok.Data;

@Data
public class OrderShippedEvent {
    private Long orderId;
    private String status;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}