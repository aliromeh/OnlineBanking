package com.gbis.Bank.Services;

import java.util.Optional;

import com.gbis.Bank.DTOs.ClientDTO;
import com.gbis.Bank.DTOs.ClientResponseDTO;
import com.gbis.Bank.Entities.ClientEntity;

public interface ClientService {

	public ClientResponseDTO registerClient(ClientDTO client);

	public Boolean existsByUsername(String username);

	public Optional<ClientEntity> findClient(String username);

}
