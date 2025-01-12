package com.blahblah.accounts.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.blahblah.accounts.constants.AccountsConstants;
import com.blahblah.accounts.dto.AccountsDto;
import com.blahblah.accounts.dto.CustomerDto;
import com.blahblah.accounts.entity.Accounts;
import com.blahblah.accounts.entity.Customer;
import com.blahblah.accounts.exception.CustomerAlreadyExistsException;
import com.blahblah.accounts.exception.ResourceNotFoundException;
import com.blahblah.accounts.mapper.AccountsMapper;
import com.blahblah.accounts.mapper.CustomerMapper;
import com.blahblah.accounts.repository.AccountsRepository;
import com.blahblah.accounts.repository.CustomerRepository;
import com.blahblah.accounts.service.IAccountsService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

	private AccountsRepository accountsRepository;
	private CustomerRepository customerRepository;

	/**
	 * @param customerDto -> CustomerDto Object
	 */
	@Override
	public void createAccount(CustomerDto customerDto) {
		Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
		Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customer.getMobileNumber());
		if (optionalCustomer.isPresent()) {
			throw new CustomerAlreadyExistsException(
					"Customer already exists with mobile number: " + customer.getMobileNumber());
		}
		Customer savedCustomer = customerRepository.save(customer);
		Accounts account = createNewAccount(savedCustomer);
		accountsRepository.save(account);
	}

	/**
	 * @param customer - Customer Object
	 * @return the new account details
	 */
	private Accounts createNewAccount(Customer customer) {
		Accounts newAccount = new Accounts();
		newAccount.setCustomerId(customer.getCustomerId());
		long randomAccNumber = 1000000000L + new Random().nextInt(900000000);
		newAccount.setAccountNumber(randomAccNumber);
		newAccount.setAccountType(AccountsConstants.SAVINGS);
		newAccount.setBranchAddress(AccountsConstants.ADDRESS);
		return newAccount;
	}

	/**
	 * @param mobileNumber
	 * @return CustomerDto Object
	 */
	@Override
	public CustomerDto fetch(String mobileNumber) {
		Customer customer = customerRepository.findByMobileNumber(mobileNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));
		Accounts account = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
				() -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString()));
		CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
		customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(account, new AccountsDto()));
		return customerDto;
	}

	/**
	 * @param customerDto -> CustomerDto Object
	 * @return customer and account update or not
	 */
	@Override
	public boolean updateAccount(CustomerDto customerDto) {
		AccountsDto accountsDto = customerDto.getAccountsDto();
		if (!Optional.ofNullable(accountsDto).isPresent()) {
			return false;
		}
		Accounts accounts = accountsRepository.findById(accountsDto.getAccountNumber())
				.orElseThrow(() -> new ResourceNotFoundException("Accounts", "accountNumber",
						accountsDto.getAccountNumber().toString()));
		accounts = AccountsMapper.mapToAccounts(accountsDto, accounts);
		accountsRepository.save(accounts);
		Optional<Customer> optionalCustomer = customerRepository.findById(accounts.getCustomerId());
		if (!optionalCustomer.isPresent())
			throw new ResourceNotFoundException("Customer", "customerId", accounts.getCustomerId().toString());
		Customer customer = CustomerMapper.mapToCustomer(customerDto, optionalCustomer.get());
		customerRepository.save(customer);
		return true;
	}

	/**
	 * @param mobileNumber
	 * @return account deleted or not
	 */
	@Override
	public boolean deleteAccount(String mobileNumber) {
		Customer customer = customerRepository.findByMobileNumber(mobileNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));
		customerRepository.deleteById(customer.getCustomerId());
		accountsRepository.deleteByCustomerId(customer.getCustomerId());
		return true;
	}

}
