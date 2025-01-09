package com.blahblah.accounts.mapper;

import com.blahblah.accounts.dto.AccountsDto;
import com.blahblah.accounts.entity.Accounts;

public class AccountsMapper {

	public static AccountsDto mapToAccountsDto(Accounts accounts, AccountsDto accountsDto) {
		accountsDto.setAccountNumber(accounts.getAccountNumber());
		accountsDto.setAccountType(accounts.getAccountType());
		accountsDto.setBranchAddress(accounts.getBranchAddress());
		return accountsDto;
	}

	public static Accounts mapToAccounts(AccountsDto accountsDto, Accounts accounts) {
		accounts.setAccountNumber(accountsDto.getAccountNumber());
		accounts.setAccountType(accountsDto.getAccountType());
		accounts.setBranchAddress(accountsDto.getBranchAddress());
		return accounts;
	}

}
