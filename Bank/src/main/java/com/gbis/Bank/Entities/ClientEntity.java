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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "TBL_CLIENTS", uniqueConstraints = { @UniqueConstraint(columnNames = "CLIENT_ID"),
		@UniqueConstraint(columnNames = "USERNAME") })
public class ClientEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CLIENT_ID")
	private Long clientId;

	@Column(name = "USERNAME", unique = true, nullable = false)
	private String username;

	@Column(name = "FIRST_NAME", unique = false, nullable = false, length = 100)
	private String firstname;

	@Column(name = "LAST_NAME", unique = false, nullable = false, length = 100)
	private String lastname;

	@Column(name = "CREATED_ON", unique = false, nullable = false)
	private Date createdOn;

	@OneToMany(mappedBy = "clientId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<AccountEntity> accounts;

	public ClientEntity() {
	}

	public ClientEntity(String username, String firstname, String lastname, Date createdOn,
			Set<AccountEntity> accounts) {
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.createdOn = createdOn;
		this.accounts = accounts;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Set<AccountEntity> getAccounts() {
		return accounts;
	}

	public void setAccounts(Set<AccountEntity> accounts) {
		this.accounts = accounts;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

}
