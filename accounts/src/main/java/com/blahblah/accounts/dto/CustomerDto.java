package com.blahblah.accounts.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerDto {

	@NotBlank(message = "Name shouldn't be null or empty")
	@Size(min = 2, max = 50, message = "Name length must be between 2 to 50")
	private String name;

	@Email
	private String email;

	@Pattern(regexp = "^[0-9]{10}$", message = "Mobile Number must contain 10 digits.")
	private String mobileNumber;

	private AccountsDto accountsDto;

}
