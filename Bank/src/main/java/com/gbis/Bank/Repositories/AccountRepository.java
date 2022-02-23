package com.gbis.Bank.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gbis.Bank.Entities.AccountEntity;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

	@Query("select u from AccountEntity u where u.accountNumber = :accountNumber")
	public Optional<AccountEntity> findAccountByAccountNumber(@Param("accountNumber") long accountNumber);

	@Query("select u.currentBalance from AccountEntity u where u.accountNumber = :accountNumber")
	public Double findCurrentBalance(@Param("accountNumber") long accountNumber);

}
