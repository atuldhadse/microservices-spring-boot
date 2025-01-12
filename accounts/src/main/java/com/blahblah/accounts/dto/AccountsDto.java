package com.blahblah.accounts.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AccountsDto {

	@NotBlank(message = "Account Number Can't be NULL or Empty.")
	@Pattern(regexp = "^[0-9]{10}$", message = "Account Number Must Be 10 Digits.")
	private Long accountNumber;

	@NotBlank(message = "Account Type Can't be NULL or Empty.")
	private String accountType;

	@NotBlank(message = "Branch Address Can't be NULL or Empty.")
	private String branchAddress;

}
