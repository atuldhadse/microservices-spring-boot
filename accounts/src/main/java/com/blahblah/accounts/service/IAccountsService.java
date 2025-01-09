package com.blahblah.accounts.service;

import com.blahblah.accounts.dto.CustomerDto;

public interface IAccountsService {

	/**
	 * @param customerDto -> CustomerDto Object
	 */
	void createAccount(CustomerDto customerDto);

}
