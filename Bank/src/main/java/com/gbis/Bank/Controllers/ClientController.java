package com.gbis.Bank.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gbis.Bank.DTOs.ClientDTO;
import com.gbis.Bank.DTOs.ClientResponseDTO;
import com.gbis.Bank.Services.ClientService;
import com.gbis.Bank.utils.Constants;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/client/v1")
@Api(value = "Client Functionalities")
public class ClientController {

	@Autowired
	ClientService clientService;

	@ApiOperation(value = "client registration")
	@ApiResponses(value = { @ApiResponse(code = 200, message = Constants.SUCCESS),
			@ApiResponse(code = 400, message = Constants.MISSING_ELEMENT),
			@ApiResponse(code = 417, message = Constants.EXCEPTION_FAILED) })
	@PostMapping(path = "/client/register", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ClientResponseDTO> register(@RequestBody ClientDTO clientDTO) {
		return ResponseEntity.ok().body(clientService.registerClient(clientDTO));
	}

}
