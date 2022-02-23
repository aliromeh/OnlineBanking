package com.gbis.Bank.DTOs;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ClientResponseDTO extends ClientDTO {

	private String userName;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Europe/Paris")
	private Date creationDate;

	public ClientResponseDTO() {
	}
	
	public ClientResponseDTO(String userName, Date creationDate, String firstname, String lastname) {
		super(lastname, firstname);
		this.userName = userName;
		this.creationDate = creationDate;
		
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}



}
