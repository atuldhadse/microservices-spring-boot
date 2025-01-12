package com.blahblah.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(name = "Accounts", description = "Schema to hold Account information")
public class AccountsDto {

	@NotBlank(message = "Account Number Can't be NULL or Empty.")
	@Pattern(regexp = "^[0-9]{10}$", message = "Account Number Must Be 10 Digits.")
	@Schema(description = "Account Number of Blah Blah Bank account", example = "3454433243")
	private Long accountNumber;

	@NotBlank(message = "Account Type Can't be NULL or Empty.")
	@Schema(description = "Account type of Blan Blah Bank account", example = "Savings")
	private String accountType;

	@NotBlank(message = "Branch Address Can't be NULL or Empty.")
	@Schema(description = "Blah Blah Bank branch address", example = "123 NewYork")
	private String branchAddress;

}
