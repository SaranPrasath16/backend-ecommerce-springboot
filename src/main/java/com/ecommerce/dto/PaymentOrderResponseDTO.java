package com.ecommerce.dto;

import com.ecommerce.model.Orders;
import com.ecommerce.model.Payment;

public class PaymentOrderResponseDTO {
    private Payment payment;
    private Orders order;
	public Payment getPayment() {
		return payment;
	}
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	public Orders getOrder() {
		return order;
	}
	public void setOrder(Orders order) {
		this.order = order;
	}
	public PaymentOrderResponseDTO(Payment payment, Orders order) {
		super();
		this.payment = payment;
		this.order = order;
	}
	public PaymentOrderResponseDTO(Payment payment) {
		super();
		this.payment = payment;
	}
	public PaymentOrderResponseDTO() {
		super();
	}
}
