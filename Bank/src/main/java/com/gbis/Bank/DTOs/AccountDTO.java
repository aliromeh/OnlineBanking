package com.gbis.Bank.DTOs;

import org.springframework.lang.NonNull;

import com.gbis.Bank.Enums.CurrencyEnum;

public class AccountDTO {
	@NonNull
	private String accountName;
	@NonNull
	private CurrencyEnum currency;

	public AccountDTO() {
	}

	public AccountDTO(String accountName, Double currentBalance, CurrencyEnum currency) {
		this.accountName = accountName;
		this.currency = currency;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public CurrencyEnum getCurrency() {
		return currency;
	}

	public void setCurrency(CurrencyEnum currency) {
		this.currency = currency;
	}

}
