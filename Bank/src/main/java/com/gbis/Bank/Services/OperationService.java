package com.gbis.Bank.Services;

import java.util.List;

import com.gbis.Bank.DTOs.OperationDTO;
import com.gbis.Bank.DTOs.OperationResponseDTO;

public interface OperationService {

	public OperationResponseDTO createOperation(long accountNumber, OperationDTO objOperation);

	public List<OperationResponseDTO> getAllOperations(long accountNumber);

}
