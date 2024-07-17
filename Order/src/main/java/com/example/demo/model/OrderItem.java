package com.example.demo.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_items")
public class OrderItem {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer orderItemId;

	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "order_id", nullable = false)
	    private Order order;

	    @Column(nullable = false)
	    private Integer productId;

	    @Column(nullable = false)
	    private Integer quantity;

	    @Column(nullable = false)
	    private Integer price;
	    
	    public Integer getOrderItemId() {
	        return orderItemId;
	    }

	    public void setOrderItemId(Integer orderItemId) {
	        this.orderItemId = orderItemId;
	    }

	    public Order getOrder() {
	        return order;
	    }

	    public void setOrder(Order order) {
	        this.order = order;
	    }

	    public Integer getProductId() {
	        return productId;
	    }

	    public void setProductId(Integer productId) {
	        this.productId = productId;
	    }

	    public Integer getQuantity() {
	        return quantity;
	    }

	    public void setQuantity(Integer quantity) {
	        this.quantity = quantity;
	    }

	    public Integer getPrice() {
	        return price;
	    }

	    public void setPrice(Integer price) {
	        this.price = price;
	    }
}
