package com.gbis.Bank.ServicesImpl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gbis.Bank.DTOs.ClientDTO;
import com.gbis.Bank.DTOs.ClientResponseDTO;
import com.gbis.Bank.Entities.ClientEntity;
import com.gbis.Bank.Repositories.ClientRepository;
import com.gbis.Bank.Services.ClientService;
import com.gbis.Bank.utils.Converter;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	ClientRepository clientRepository;

	@Transactional
	@Override
	public ClientResponseDTO registerClient(ClientDTO client) {
		ClientResponseDTO objClientResponse = new ClientResponseDTO();
		if (client != null) {
			String username = client.getLastname();
			String firstname = client.getFirstname();
			String lastName = client.getLastname();
			java.sql.Date createdOn = Converter.toSqlDate(new Date(System.currentTimeMillis()));
			ClientEntity newClient = new ClientEntity(username, firstname, firstname, createdOn, null);
			ClientEntity savedClient = clientRepository.save(newClient);
			username = client.getLastname() + '#' + savedClient.getClientId().toString();
			savedClient.setUsername(username);
			objClientResponse = new ClientResponseDTO(username, Converter.toJavaUtilDate(createdOn), firstname,
					lastName);
		}
		return objClientResponse;
	}

	@Override
	public Boolean existsByUsername(String username) {
		return clientRepository.existsByUsername(username);
	}

	public Optional<ClientEntity> findClient(String username) {
		return clientRepository.findByUsername(username);
	}

}
