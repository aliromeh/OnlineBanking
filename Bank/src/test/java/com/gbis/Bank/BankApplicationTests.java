package com.gbis.Bank;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.gbis.Bank.DTOs.AccountDTO;
import com.gbis.Bank.DTOs.AccountReponseDTO;
import com.gbis.Bank.DTOs.ClientDTO;
import com.gbis.Bank.DTOs.ClientResponseDTO;
import com.gbis.Bank.DTOs.OperationDTO;
import com.gbis.Bank.DTOs.OperationResponseDTO;
import com.gbis.Bank.Enums.CurrencyEnum;
import com.gbis.Bank.Enums.OperationTypeEnum;
import com.gbis.Bank.utils.HelperClass;

@SpringBootTest
class BankApplicationTests {

//	@Test
//	void contextLoads() {
//	}

	@Test
	void registerUser() throws IOException {
		RestTemplate restTemplate = new RestTemplate();

		String apiConfigPath = HelperClass.getFilePath("ApiConfig.properties");
		Properties props = HelperClass.getProps(apiConfigPath);

		String url = props.getProperty("API.BaseUrl") + ":" + props.getProperty("API.Port") + "/"
				+ props.getProperty("API.ProjectNameWAR") + "/api/client/v1/client/register";
		ClientDTO client = new ClientDTO("ALROMEH", "ALI");
		ResponseEntity<ClientResponseDTO> clientRes = restTemplate.postForEntity(url, client, ClientResponseDTO.class);
		assertNotNull(clientRes.getBody());
		assertNotNull(clientRes.getBody().getFirstname());

		String username = clientRes.getBody().getUserName();
		String createAccountURL = props.getProperty("API.BaseUrl") + ":" + props.getProperty("API.Port") + "/"
				+ props.getProperty("API.ProjectNameWAR") + "/api/account/v1/createAccount";
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(createAccountURL)
				// Add query parameter
				.queryParam("username", username);
		HttpEntity<AccountDTO> accountDTO = new HttpEntity<>(new AccountDTO("testAccount", 0.0, CurrencyEnum.EUR));
		ResponseEntity<AccountReponseDTO> objAccResponse = restTemplate.exchange(builder.build().toUri(),
				HttpMethod.POST, accountDTO, AccountReponseDTO.class);
		assertNotNull(objAccResponse);

		long accountNumber = objAccResponse.getBody().getAccountNumber();
		HttpEntity<OperationDTO> objOperationDTO = new HttpEntity<>(
				new OperationDTO(OperationTypeEnum.DEPOSIT, 1000.0));
		String operationURL = props.getProperty("API.BaseUrl") + ":" + props.getProperty("API.Port") + "/"
				+ props.getProperty("API.ProjectNameWAR") + "/api/operation/v1/makeOperation";
		UriComponentsBuilder OperationBuilder = UriComponentsBuilder.fromUriString(operationURL)
				// Add query parameter
				.queryParam("accountNumber", accountNumber);
		ResponseEntity<OperationResponseDTO> objOperationResponse = restTemplate.exchange(
				OperationBuilder.build().toUri(), HttpMethod.POST, objOperationDTO, OperationResponseDTO.class);
		assertNotNull(objOperationResponse.getBody());

	}

	@Test
	void testCreate() {
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8080/Bank/api/client/v1/client/register";
		ClientDTO client = new ClientDTO("ALROMEH", "ALI");
		ResponseEntity<ClientResponseDTO> clientRes = restTemplate.postForEntity(url, client, ClientResponseDTO.class);
		assertNotNull(clientRes.getBody());
		assertNotNull(clientRes.getBody().getFirstname());

	}

}
