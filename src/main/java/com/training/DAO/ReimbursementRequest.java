package com.training.DAO;

public class ReimbursementRequest {
	
	int requestid;
	int userId;
	int amount;
	String category;
	String details;
	Status status;
	public ReimbursementRequest(int requestid, int userId, int amount, String category, String details, Status status) {
		super();
		this.requestid = requestid;
		this.userId = userId;
		this.amount = amount;
		this.category = category;
		this.details = details;
		this.status = status;
	}
	public int getRequestid() {
		return requestid;
	}
	public void setRequestid(int requestid) {
		this.requestid = requestid;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
	
}
