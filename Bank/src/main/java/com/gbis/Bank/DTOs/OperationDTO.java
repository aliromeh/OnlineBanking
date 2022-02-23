package com.gbis.Bank.DTOs;

import com.gbis.Bank.Enums.OperationTypeEnum;
import com.sun.istack.NotNull;

public class OperationDTO {
	@NotNull
	OperationTypeEnum operationType;
	@NotNull
	Double amount;

	public OperationDTO() {
	}

	public OperationDTO(OperationTypeEnum operationType, Double amount) {
		this.operationType = operationType;
		this.amount = amount;
	}

	public OperationTypeEnum getOperationType() {
		return operationType;
	}

	public void setOperationType(OperationTypeEnum operationType) {
		this.operationType = operationType;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

}
