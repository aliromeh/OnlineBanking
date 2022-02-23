package com.gbis.Bank.ServicesImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gbis.Bank.DTOs.OperationDTO;
import com.gbis.Bank.DTOs.OperationResponseDTO;
import com.gbis.Bank.Entities.AccountEntity;
import com.gbis.Bank.Entities.OperationEntity;
import com.gbis.Bank.Enums.OperationTypeEnum;
import com.gbis.Bank.Repositories.OperationRepository;
import com.gbis.Bank.Services.AccountService;
import com.gbis.Bank.Services.OperationService;
import com.gbis.Bank.exceptions.InsufficientFunds;
import com.gbis.Bank.exceptions.NotFoundException;
import com.gbis.Bank.utils.Converter;

@Service
public class OperationServiceImpl implements OperationService {

	@Autowired
	OperationRepository operationRepository;

	@Autowired
	AccountService accountService;

	@Override
	public OperationResponseDTO createOperation(long accountNumber, OperationDTO objOperation) {
		LocalDateTime operationDate = LocalDateTime.now();
		String operationDesc = "";
		Optional<AccountEntity> objAccount = accountService.getAccountEntity(accountNumber);
		Double newAccountBalance = 0.0;
		if (objAccount.isPresent()) {
			OperationEntity objOperationE = new OperationEntity();
			switch (objOperation.getOperationType()) {

			case WITHDRAWAL:
				if (accountService.getAccountBalance(accountNumber) < objOperation.getAmount()) {
					throw new InsufficientFunds("Insufficient Funds. your balance is :"
							.concat(String.valueOf(accountService.getAccountBalance(accountNumber))));
				} else {
					newAccountBalance = accountService.getAccountBalance(accountNumber) - objOperation.getAmount();

					objOperationE = operationRepository.save(
							new OperationEntity(Converter.toSqlDateTime(operationDate), "withdraw from my bank Account",
									1, objAccount.get(), newAccountBalance, objOperation.getAmount()));
					accountService.updateBalance(accountNumber, newAccountBalance);

				}

				break;
			case DEPOSIT:
				newAccountBalance = accountService.getAccountBalance(accountNumber) + objOperation.getAmount();

				objOperationE = operationRepository
						.save(new OperationEntity(Converter.toSqlDateTime(operationDate), "deposit in my bank Account",
								2, objAccount.get(), newAccountBalance, objOperation.getAmount()));
				accountService.updateBalance(accountNumber, newAccountBalance);

				break;
			}

			operationDesc = objOperationE.getDescription();

		} else {
			throw new NotFoundException("accountNumber not found : " + accountNumber);
		}

		return new OperationResponseDTO(objOperation.getOperationType(), objOperation.getAmount(), operationDate,
				newAccountBalance, operationDesc);
	}

	@Override
	public List<OperationResponseDTO> getAllOperations(long accountNumber) {
		List<OperationResponseDTO> lObjOpeRationResponse = new ArrayList<OperationResponseDTO>();

		Optional<AccountEntity> objAccount = accountService.getAccountEntity(accountNumber);
		if (objAccount.isPresent()) {
			AccountEntity accountID = objAccount.get();
			Set<OperationEntity> lObjOpe = accountID.getOperations();
			if (lObjOpe != null) {
				for (OperationEntity operation : lObjOpe) {

					lObjOpeRationResponse
							.add(new OperationResponseDTO(OperationTypeEnum.type(operation.getOperationType()),
									operation.getAmount(), Converter.toJavaUtilDateTime(operation.getOperationDate()),
									operation.getBalanceOfPayments(), operation.getDescription()));
				}

			}
		}

		return lObjOpeRationResponse;
	}

}
