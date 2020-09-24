package com.hcl.training.ecommerce.poc.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Manjeet Kumar
 *
 *         Sep 21, 2020
 *
 */

@Entity
@Table(name = "cart_order")
public class Order implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_Id")
	private Long orderId;

	@Column(name = "ordered_date")
	private LocalDate orderedDate;

	@Column(name = "status")
	private String status;

	@Column(name = "total")
	private float total;
	
	public Order() {

	}

	/**
	 * @return the orderId
	 */
	public Long getOrderId() {
		return orderId;
	}

	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	/**
	 * @return the orderedDate
	 */
	public LocalDate getOrderedDate() {
		return orderedDate;
	}

	/**
	 * @param orderedDate the orderedDate to set
	 */
	public void setOrderedDate(LocalDate orderedDate) {
		this.orderedDate = orderedDate;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the total
	 */
	public float getTotal() {
		return total;
	}

	/**
	 * @param f the total to set
	 */
	public void setTotal(float f) {
		this.total = f;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", orderedDate=" + orderedDate + ", status=" + status + ", total=" + total
				+ "]";
	}

}
