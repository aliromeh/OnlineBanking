package com.gbis.Bank.ServicesImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gbis.Bank.DTOs.AccountDTO;
import com.gbis.Bank.DTOs.AccountReponseDTO;
import com.gbis.Bank.Entities.AccountEntity;
import com.gbis.Bank.Entities.ClientEntity;
import com.gbis.Bank.Enums.CurrencyEnum;
import com.gbis.Bank.Repositories.AccountRepository;
import com.gbis.Bank.Services.AccountService;
import com.gbis.Bank.Services.ClientService;
import com.gbis.Bank.exceptions.NotFoundException;
import com.gbis.Bank.utils.Constants;
import com.gbis.Bank.utils.Converter;
import com.gbis.Bank.utils.HelperClass;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	ClientService clientService;

	@Override
	public AccountReponseDTO createAccount(String username, AccountDTO account) {
		AccountReponseDTO objAccountReponse = new AccountReponseDTO();
		ClientEntity objClient = new ClientEntity();
		if (username != null && !username.isEmpty() && account != null) {

			boolean exist = clientService.existsByUsername(username);
			long clientId = 0;
			if (exist) {
				if (clientService.findClient(username).isPresent()) {
					objClient = clientService.findClient(username).get();
				}
			} else {
				throw new NotFoundException(username + "  " + Constants.NOT_FOUND);
			}
			String accountName = account.getAccountName();
			int currency = CurrencyEnum.nb(account.getCurrency());
			long accountNumber = Converter.stringToLong(HelperClass.costumID() + clientId);
			java.sql.Date createdOn = Converter.toSqlDate(new Date());
			AccountEntity newObjAccount = accountRepository
					.save(new AccountEntity(accountName, accountNumber, createdOn, null, 0.0, currency, objClient));

			objAccountReponse = new AccountReponseDTO(newObjAccount.getAccountName(), newObjAccount.getAccountNumber(),
					Converter.toJavaUtilDate(newObjAccount.getCreatedOn()),
					"0.0".concat(" ").concat(account.getCurrency().toString()));

		}

		return objAccountReponse;
	}

	@Override
	public List<AccountReponseDTO> getAccounts(String username) {

		if (username == null || !clientService.existsByUsername(username)) {
			throw new NotFoundException("username : " + username + "dosen't exist");
		}
		List<AccountReponseDTO> lAccountResult = new ArrayList<>();
		if (clientService.findClient(username).isPresent()) {
			ClientEntity clientObj = clientService.findClient(username).get();
			Set<AccountEntity> lobjAccounts = clientObj.getAccounts();
			if (lobjAccounts != null) {
				for (AccountEntity account : lobjAccounts) {
					String accountName = account.getAccountName();
					Long accountNumber = account.getAccountNumber();
					Date createdOn = Converter.toJavaUtilDate(account.getCreatedOn());
					String currentBalance = HelperClass
							.displayNumberInLegalFormat(String.valueOf(account.getCurrentBalance()));
					currentBalance = currentBalance.concat(" ")
							.concat(CurrencyEnum.type(account.getCurrencyID()).toString());
					lAccountResult.add(new AccountReponseDTO(accountName, accountNumber, createdOn, currentBalance));

				}
			}
		}

		return lAccountResult;

	}

	@Override
	public AccountReponseDTO getAccount(long accountNumber) {
		AccountReponseDTO objAccountResult = new AccountReponseDTO();
		Optional<AccountEntity> objAccount = this.getAccountEntity(accountNumber);
		if (objAccount.isPresent()) {
			String accountName = objAccount.get().getAccountName();
			Date createdOn = Converter.toJavaUtilDate(objAccount.get().getCreatedOn());
			String currentBalance = HelperClass
					.displayNumberInLegalFormat(String.valueOf(objAccount.get().getCurrentBalance()));
			currentBalance = currentBalance.concat(" ")
					.concat(CurrencyEnum.type(objAccount.get().getCurrencyID()).toString());

			objAccountResult = new AccountReponseDTO(accountName, accountNumber, createdOn, currentBalance);
		} else {
			throw new NotFoundException("Account " + accountNumber + Constants.NOT_FOUND);
		}

		return objAccountResult;
	}

	@Override
	public Optional<AccountEntity> getAccountEntity(long accountNumber) {
		return accountRepository.findAccountByAccountNumber(accountNumber);
	}

	@Override
	public Double getAccountBalance(long accountNumber) {
		Double balanceResult = 0.0;
		if (accountRepository.findAccountByAccountNumber(accountNumber).isPresent()) {
			balanceResult = accountRepository.findCurrentBalance(accountNumber);
		}
		return balanceResult;
	}

	@Override
	public void updateBalance(long accountNumber, Double balance) {
		Optional<AccountEntity> ObjAccount = this.getAccountEntity(accountNumber);
		if (ObjAccount.isPresent()) {
			ObjAccount.get().setCurrentBalance(balance);
			accountRepository.save(ObjAccount.get());
		}

	}

}
