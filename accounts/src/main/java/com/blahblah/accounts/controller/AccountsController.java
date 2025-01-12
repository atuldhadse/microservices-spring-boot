package com.blahblah.accounts.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blahblah.accounts.constants.AccountsConstants;
import com.blahblah.accounts.dto.CustomerDto;
import com.blahblah.accounts.dto.ErrorResponseDto;
import com.blahblah.accounts.dto.ResponseDto;
import com.blahblah.accounts.service.IAccountsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;

@Tag(name = "CRUD APIs for accounts", description = "CRUD APIs operation for account in Blah Blah Bank")
@RestController
@RequestMapping(path = "/api", produces = { MediaType.APPLICATION_JSON_VALUE })
@AllArgsConstructor
@Validated
public class AccountsController {

	private IAccountsService iAccountsService;

	@Operation(summary = "Customer and Account create API", description = "API for customer and account creation", responses = {
			@ApiResponse(responseCode = "201", description = "HTTP Status Created"),
			@ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))) })
	@PostMapping("/create")
	public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {
		iAccountsService.createAccount(customerDto);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
	}

	@Operation(summary = "Customer and Account fetch API", description = "API to fetch using customer mobile number", responses = {
			@ApiResponse(responseCode = "200", description = "HTTP Status Fetch"),
			@ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))) })
	@GetMapping("/fetch")
	public ResponseEntity<CustomerDto> fetch(
			@RequestParam(required = true) @Pattern(regexp = "^[0-9]{10}$", message = "mobile number must be of 10 digits.") String mobileNumber) {
		CustomerDto customerDto = iAccountsService.fetch(mobileNumber);
		return ResponseEntity.status(HttpStatus.OK).body(customerDto);
	}

	@Operation(summary = "Customer and Account update API", description = "API to update customer and account", responses = {
			@ApiResponse(responseCode = "200", description = "HTTP Status Updated"),
			@ApiResponse(responseCode = "417", description = "HTTP Status Not Updated"),
			@ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))) })
	@PutMapping("/update")
	public ResponseEntity<ResponseDto> update(@Valid @RequestBody CustomerDto customerDto) {
		boolean updated = iAccountsService.updateAccount(customerDto);
		if (Boolean.TRUE.equals(updated)) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
		} else {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_UPDATE));
		}
	}

	@Operation(summary = "Customer and Account delete API", description = "API to delete customer and account", responses = {
			@ApiResponse(responseCode = "200", description = "HTTP Status Deleted"),
			@ApiResponse(responseCode = "417", description = "HTTP Status Not Deleted"),
			@ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))) })
	@PutMapping("/delete")
	public ResponseEntity<ResponseDto> delete(
			@RequestParam @Pattern(regexp = "^[0-9]{10}$", message = "mobile number must be of 10 digits.") String mobileNumber) {
		boolean deleted = iAccountsService.deleteAccount(mobileNumber);
		if (Boolean.TRUE.equals(deleted)) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
		} else {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_DELETE));
		}
	}

}
