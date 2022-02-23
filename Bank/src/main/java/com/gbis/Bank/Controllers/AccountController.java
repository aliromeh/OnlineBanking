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

import com.gbis.Bank.DTOs.AccountDTO;
import com.gbis.Bank.DTOs.AccountReponseDTO;
import com.gbis.Bank.Services.AccountService;
import com.gbis.Bank.utils.Constants;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/account/v1")
@Api(value = "ACCOUNT Functionalities")
public class AccountController {

	@Autowired
	AccountService accountService;

	@ApiResponses(value = { @ApiResponse(code = 200, message = Constants.SUCCESS),
			@ApiResponse(code = 400, message = Constants.MISSING_ELEMENT),
			@ApiResponse(code = 417, message = Constants.EXCEPTION_FAILED) })
	@PostMapping(path = "/createAccount", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AccountReponseDTO> register(
			@RequestParam(value = "username", required = true) String username, @RequestBody AccountDTO accountDTO) {
		return ResponseEntity.ok().body(accountService.createAccount(username, accountDTO));
	}

	@ApiOperation(value = " get Account by Account Number")
	@ApiResponses(value = { @ApiResponse(code = 200, message = Constants.SUCCESS),
			@ApiResponse(code = 403, message = Constants.FORBIDDEN) })
	@GetMapping(path = "/account", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AccountReponseDTO> getAccountByAccountNumber(
			@RequestParam(value = "accountNumber", required = true) long accountNumber) {
		return ResponseEntity.ok().body(accountService.getAccount(accountNumber));
	}

	@ApiOperation(value = " get all Accounts by client")
	@ApiResponses(value = { @ApiResponse(code = 200, message = Constants.SUCCESS),
			@ApiResponse(code = 403, message = Constants.FORBIDDEN) })
	@GetMapping(path = "/accounts", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AccountReponseDTO>> getAllAccounts(
			@RequestParam(value = "username", required = true) String username) {
		return ResponseEntity.ok().body(accountService.getAccounts(username));
	}

}
