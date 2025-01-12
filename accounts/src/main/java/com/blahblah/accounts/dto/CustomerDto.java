package com.blahblah.accounts.dto;

import io.swagger.v3.oas.annotations.StringToClassMapItem;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Schema(name = "Customer", 
		description = "Schema to hold Customer and Account information", 
		dependentSchemas = {
				@StringToClassMapItem(key = "account", value = AccountsDto.class) 
		}
)
@Data
public class CustomerDto {

	@NotBlank(message = "Name shouldn't be null or empty")
	@Size(min = 2, max = 50, message = "Name length must be between 2 to 50")
	@Schema(description = "Name of the customer", example = "Blah Blah")
	private String name;

	@Email
	@Schema(description = "Email address of the customer", example = "blahblah@mailinator.com")
	private String email;

	@Pattern(regexp = "^[0-9]{10}$", message = "Mobile Number must contain 10 digits.")
	@Schema(description = "Mobile Number of the customer", example = "9345432123")
	private String mobileNumber;

	@Schema(description = "Account details of the Customer")
	private AccountsDto accountsDto;

}
