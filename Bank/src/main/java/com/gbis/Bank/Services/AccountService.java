package com.gbis.Bank.Services;

import java.util.List;
import java.util.Optional;

import com.gbis.Bank.DTOs.AccountDTO;
import com.gbis.Bank.DTOs.AccountReponseDTO;
import com.gbis.Bank.Entities.AccountEntity;

public interface AccountService {

	public AccountReponseDTO createAccount(String username, AccountDTO account);

	public List<AccountReponseDTO> getAccounts(String username);

	public AccountReponseDTO getAccount(long accountNumber);

	public Optional<AccountEntity> getAccountEntity(long accountNumber);

	public Double getAccountBalance(long accountNumber);

	public void updateBalance(long accountNumber,Double balance);

}
