package com.gbis.Bank.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gbis.Bank.DTOs.OperationDTO;
import com.gbis.Bank.DTOs.OperationResponseDTO;
import com.gbis.Bank.Services.OperationService;
import com.gbis.Bank.utils.Constants;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/operation/v1")
@Api(value = "Operations Functionalities")
public class OperationController {

	@Autowired
	OperationService operationService;

	@ApiOperation(value = "create Operation")
	@ApiResponses(value = { @ApiResponse(code = 200, message = Constants.SUCCESS),
			@ApiResponse(code = 400, message = Constants.MISSING_ELEMENT),
			@ApiResponse(code = 417, message = Constants.EXCEPTION_FAILED) })
	@PostMapping(path = "/makeOperation", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<OperationResponseDTO> makeOperation(
			@RequestParam(value = "accountNumber", required = true) long accountNumber,
			@RequestBody OperationDTO objOperation) {
		return ResponseEntity.ok().body(operationService.createOperation(accountNumber, objOperation));
	}

	@ApiOperation(value = " get all Accounts by client")
	@ApiResponses(value = { @ApiResponse(code = 200, message = Constants.SUCCESS),
			@ApiResponse(code = 403, message = "Insufficient Funds") })
	@GetMapping(path = "/operations", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<OperationResponseDTO>> getAllOpeations(
			@RequestParam(value = "accountNumber", required = true) long accountNumber) {
		return ResponseEntity.ok().body(operationService.getAllOperations(accountNumber));
	}

}
