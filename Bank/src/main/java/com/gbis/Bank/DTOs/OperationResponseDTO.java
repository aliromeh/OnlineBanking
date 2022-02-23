package com.gbis.Bank.DTOs;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gbis.Bank.Enums.OperationTypeEnum;

public class OperationResponseDTO extends OperationDTO {

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "Europe/Paris")
	private LocalDateTime operationDate;
	Double balance;
	String description;

	public OperationResponseDTO() {
	}

	public OperationResponseDTO(OperationTypeEnum operationType, Double amount, LocalDateTime operationDate, Double balance,
			String desc) {
		super(operationType, amount);
		this.operationDate = operationDate;
		this.balance = balance;
		this.description = desc;
	}

	public LocalDateTime getOperationDate() {
		return operationDate;
	}

	public void setOperationDate(LocalDateTime operationDate) {
		this.operationDate = operationDate;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

}
