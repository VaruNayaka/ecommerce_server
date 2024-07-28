package com.ecommerce.Auno.model;

public class PaymentDetails {
    private String paymentMethod;
    private String paymentStatus;
    private String paymentId;
    private String razorPaymentLinkId;
    private String razorPaymentReferenceId;
    private String razorPaymentLinkStatus;
    private String razorPaymentId;
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public String getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}
	public String getRazorPaymentLinkId() {
		return razorPaymentLinkId;
	}
	public void setRazorPaymentLinkId(String razorPaymentLinkId) {
		this.razorPaymentLinkId = razorPaymentLinkId;
	}
	public String getRazorPaymentReferenceId() {
		return razorPaymentReferenceId;
	}
	public void setRazorPaymentReferenceId(String razorPaymentReferenceId) {
		this.razorPaymentReferenceId = razorPaymentReferenceId;
	}
	public String getRazorPaymentLinkStatus() {
		return razorPaymentLinkStatus;
	}
	public void setRazorPaymentLinkStatus(String razorPaymentLinkStatus) {
		this.razorPaymentLinkStatus = razorPaymentLinkStatus;
	}
	public String getRazorPaymentId() {
		return razorPaymentId;
	}
	public void setRazorPaymentId(String razorPaymentId) {
		this.razorPaymentId = razorPaymentId;
	}
	public PaymentDetails(String paymentMethod, String paymentStatus, String paymentId, String razorPaymentLinkId,
			String razorPaymentReferenceId, String razorPaymentLinkStatus, String razorPaymentId) {
		super();
		this.paymentMethod = paymentMethod;
		this.paymentStatus = paymentStatus;
		this.paymentId = paymentId;
		this.razorPaymentLinkId = razorPaymentLinkId;
		this.razorPaymentReferenceId = razorPaymentReferenceId;
		this.razorPaymentLinkStatus = razorPaymentLinkStatus;
		this.razorPaymentId = razorPaymentId;
	}
    
    public PaymentDetails() {
		// TODO Auto-generated constructor stub
	}
    
    

 
}
