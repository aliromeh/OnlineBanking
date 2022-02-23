package com.gbis.Bank.Entities;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "TBL_OPERATIONS")
public class OperationEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "OPERATION_ID")
	private Long operationId;

	@Column(name = "TRANSACTION_DATE", unique = false, nullable = false)
	private Timestamp operationDate;

	@Column(name = "DESCRIPTION", unique = false, nullable = true)
	private String description;

	@Column(name = "OPERATION_TYPE", nullable = false)
	private int operationType;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "ACCOUNT_ID")
	@JsonIgnore
	AccountEntity accountId;

	@Column(name = "BALANCE_OF_PAYMENTS")
	private Double balanceOfPayments;

	@Column(name = "AMOUNT")
	private Double amount;

	public OperationEntity() {

	}

	public OperationEntity(Timestamp operationDate, String description, int operationType, AccountEntity accountId,
			Double balanceOfPayments, Double amount) {
		this.operationDate = operationDate;
		this.description = description;
		this.operationType = operationType;
		this.accountId = accountId;
		this.balanceOfPayments = balanceOfPayments;
		this.amount = amount;
	}

	public Long getOperationId() {
		return operationId;
	}

	public void setOperationId(Long operationId) {
		this.operationId = operationId;
	}

	public Timestamp getOperationDate() {
		return operationDate;
	}

	public void setOperationDate(Timestamp operationDate) {
		this.operationDate = operationDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getOperationType() {
		return operationType;
	}

	public void setOperationType(int operationType) {
		this.operationType = operationType;
	}

	public AccountEntity getAccountId() {
		return accountId;
	}

	public void setAccountId(AccountEntity accountId) {
		this.accountId = accountId;
	}

	public Double getBalanceOfPayments() {
		return balanceOfPayments;
	}

	public void setBalanceOfPayments(Double balanceOfPayments) {
		this.balanceOfPayments = balanceOfPayments;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

}
