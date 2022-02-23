package com.gbis.Bank.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gbis.Bank.Entities.ClientEntity;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
	
	@Query("select u from ClientEntity u where LOWER(u.username) = LOWER(:username)")
	public Optional<ClientEntity> findByUsername(@Param("username") String username);
	
	public Boolean existsByUsername(String username);
}
