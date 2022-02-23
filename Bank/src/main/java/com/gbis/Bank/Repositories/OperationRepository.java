package com.gbis.Bank.Repositories;



import org.springframework.data.jpa.repository.JpaRepository;

import com.gbis.Bank.Entities.OperationEntity;

public interface OperationRepository extends JpaRepository<OperationEntity, Long> {



}
