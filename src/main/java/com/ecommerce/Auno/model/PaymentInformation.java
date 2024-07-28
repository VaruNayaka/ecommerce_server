package com.ecommerce.Auno.model;

import java.time.LocalDate;

import jakarta.persistence.Column;


public class PaymentInformation {
	@Column(name = "cardholder_name")
	private String cardholdername;
	@Column(name = "card_number")
	private String cardnumber;
	@Column(name = "expiration_date")
	private LocalDate expirationdate;
	
	@Column(name = "cvv")
	private String cvv;

	public String getCardholdername() {
		return cardholdername;
	}

	public void setCardholdername(String cardholdername) {
		this.cardholdername = cardholdername;
	}

	public String getCardnumber() {
		return cardnumber;
	}

	public void setCardnumber(String cardnumber) {
		this.cardnumber = cardnumber;
	}

	public LocalDate getExpirationdate() {
		return expirationdate;
	}

	public void setExpirationdate(LocalDate expirationdate) {
		this.expirationdate = expirationdate;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
	
	public PaymentInformation() {
		// TODO Auto-generated constructor stub
	}

	public PaymentInformation(String cardholdername, String cardnumber, LocalDate expirationdate, String cvv) {
		super();
		this.cardholdername = cardholdername;
		this.cardnumber = cardnumber;
		this.expirationdate = expirationdate;
		this.cvv = cvv;
	}
	

}
