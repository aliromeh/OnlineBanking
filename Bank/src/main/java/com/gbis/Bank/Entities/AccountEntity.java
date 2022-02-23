package com.gbis.Bank.Entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "TBL_ACCOUNTS", uniqueConstraints = { @UniqueConstraint(columnNames = "ACCOUNT_ID"),
		@UniqueConstraint(columnNames = "ACCOUNT_NUMBER") })
public class AccountEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ACCOUNT_ID")
	private Long accountId;

	@Column(name = "ACCOUNT_NAME", unique = false, nullable = false, length = 100)
	private String accountName;

	@Column(name = "ACCOUNT_NUMBER", unique = false, nullable = false, length = 100)
	private Long accountNumber;

	@ManyToOne
	@JoinColumn(name = "CLIENT_ID")
	@JsonIgnore
	ClientEntity clientId;

	@Column(name = "CREATED_ON", unique = false, nullable = false)
	private Date createdOn;

	@Column(name = "CLOSED_ON", unique = false, nullable = true)
	private Date closedOn;

	@Column(name = "CURRENT_BALANCE")
	private Double currentBalance;

	@Column(name = "CURRENCY")
	private int currencyID;

	@OneToMany(mappedBy = "accountId", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private Set<OperationEntity> operations;

	public AccountEntity() {
	}

	public AccountEntity(String accountName, Long accountNumber, Date createdOn, Date closedOn, Double currentBalance,
			int currencyID,ClientEntity clientId) {
		this.accountName = accountName;
		this.accountNumber = accountNumber;
		this.createdOn = createdOn;
		this.closedOn = closedOn;
		this.currentBalance = currentBalance;
		this.currencyID = currencyID;
		this.clientId = clientId;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
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

	public Date getClosedOn() {
		return closedOn;
	}

	public void setClosedOn(Date closedOn) {
		this.closedOn = closedOn;
	}

	public Double getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(Double currentBalance) {
		this.currentBalance = currentBalance;
	}

	public Set<OperationEntity> getOperations() {
		return operations;
	}

	public void setOperations(Set<OperationEntity> operations) {
		this.operations = operations;
	}

	public ClientEntity getClientId() {
		return clientId;
	}

	public void setClientId(ClientEntity clientId) {
		this.clientId = clientId;
	}

	public int getCurrencyID() {
		return currencyID;
	}

	public void setCurrencyID(int currencyID) {
		this.currencyID = currencyID;
	}
	

}
