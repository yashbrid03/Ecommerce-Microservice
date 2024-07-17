package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer orderId;

	    @Column(nullable = false)
	    private Integer userId;

	    @Column(nullable = false)
	    private Integer totalAmount;

	    @Column(nullable = false)
	    private String status;

	    @Column(nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	    private LocalDateTime createdAt;

	    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	    private LocalDateTime updatedAt;

	    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
	    private List<OrderItem> items;
	    
	    public Integer getOrderId() {
	        return orderId;
	    }

	    public void setOrderId(Integer orderId) {
	        this.orderId = orderId;
	    }

	    public Integer getUserId() {
	        return userId;
	    }

	    public void setUserId(Integer userId) {
	        this.userId = userId;
	    }

	    public Integer getTotalAmount() {
	        return totalAmount;
	    }

	    public void setTotalAmount(Integer totalAmount) {
	        this.totalAmount = totalAmount;
	    }

	    public String getStatus() {
	        return status;
	    }

	    public void setStatus(String status) {
	        this.status = status;
	    }

	    public LocalDateTime getCreatedAt() {
	        return createdAt;
	    }

	    public void setCreatedAt(LocalDateTime createdAt) {
	        this.createdAt = createdAt;
	    }

	    public LocalDateTime getUpdatedAt() {
	        return updatedAt;
	    }

	    public void setUpdatedAt(LocalDateTime updatedAt) {
	        this.updatedAt = updatedAt;
	    }

	    public List<OrderItem> getItems() {
	        return items;
	    }

	    public void setItems(List<OrderItem> items) {
	        this.items = items;
	    }

}