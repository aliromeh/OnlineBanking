package com.gbis.Bank.DTOs;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class AccountReponseDTO {

	private String accountName;
	private Long accountNumber;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Europe/Paris")
	private Date createdOn;
	private String currentBalance;

	public AccountReponseDTO() {
	}

	public AccountReponseDTO(String accountName, Long accountNumber, Date createdOn, String currentBalance) {
		this.accountName = accountName;
		this.accountNumber = accountNumber;
		this.createdOn = createdOn;
		this.currentBalance = currentBalance;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(String currentBalance) {
		this.currentBalance = currentBalance;
	}

}
