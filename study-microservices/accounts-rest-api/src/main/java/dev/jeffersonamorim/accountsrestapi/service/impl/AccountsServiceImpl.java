package dev.jeffersonamorim.accountsrestapi.service.impl;

import dev.jeffersonamorim.accountsrestapi.constants.AccountsConstants;
import dev.jeffersonamorim.accountsrestapi.dto.AccountsDto;
import dev.jeffersonamorim.accountsrestapi.dto.CustomerDto;
import dev.jeffersonamorim.accountsrestapi.entity.Accounts;
import dev.jeffersonamorim.accountsrestapi.entity.Customer;
import dev.jeffersonamorim.accountsrestapi.exception.CustomerAlreadyExistsException;
import dev.jeffersonamorim.accountsrestapi.exception.ResourceNotFoundException;
import dev.jeffersonamorim.accountsrestapi.mapper.AccountsMapper;
import dev.jeffersonamorim.accountsrestapi.mapper.CustomerMapper;
import dev.jeffersonamorim.accountsrestapi.repository.AccountsRepository;
import dev.jeffersonamorim.accountsrestapi.repository.CustomerRepository;
import dev.jeffersonamorim.accountsrestapi.service.IAccountsService;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

  private final AccountsRepository accountsRepository;
  private final CustomerRepository customerRepository;

  @Override
  public void createAccount(CustomerDto customerDto) {
    Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
    Optional<Customer> registeredCustomer =
        this.customerRepository.findByMobileNumber(customer.getMobileNumber());

    if (registeredCustomer.isPresent()) {
      throw new CustomerAlreadyExistsException(
          "Customer already registered with given mobile number " + customer.getMobileNumber());
    }

    customer.setCreatedAt(LocalDateTime.now());
    customer.setCreatedBy("anonymous");

    Customer savedCustomer = this.customerRepository.save(customer);
    this.accountsRepository.save(this.createNewAccount(savedCustomer));
  }

  private Accounts createNewAccount(Customer customer) {
    Accounts newAccount = new Accounts();
    newAccount.setCustomerId(customer.getCustomerId());
    long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

    newAccount.setAccountNumber(randomAccNumber);
    newAccount.setAccountType(AccountsConstants.SAVINGS);
    newAccount.setBranchAddress(AccountsConstants.ADDRESS);

    newAccount.setCreatedAt(LocalDateTime.now());
    newAccount.setCreatedBy("anonymous");

    return newAccount;
  }

  @Override
  public CustomerDto fetchAccount(String mobileNumber) {
    Customer customer = this.customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
        () -> new ResourceNotFoundException("Customer", "mobile number", mobileNumber));


    Accounts accounts = this.accountsRepository.findByCustomerId(customer.getCustomerId())
        .orElseThrow(() -> new ResourceNotFoundException("Account", "customer Id",
            customer.getCustomerId().toString()));


    CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
    customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));

    return customerDto;
  }

  @Override
  public boolean updateAccount(CustomerDto customerDto) {

    boolean isUpdated = false;
    AccountsDto accountsDto = customerDto.getAccountsDto();
    if (accountsDto != null) {
      Accounts accounts = this.accountsRepository.findById(accountsDto.getAccountNumber())
          .orElseThrow(() -> new ResourceNotFoundException("Account", "AccountNumber",
              accountsDto.getAccountNumber().toString()));
      AccountsMapper.mapToAccounts(accountsDto, accounts);
      accounts = this.accountsRepository.save(accounts);

      Long customerId = accounts.getCustomerId();
      Customer customer = this.customerRepository.findById(customerId).orElseThrow(
          () -> new ResourceNotFoundException("Customer", "CustomerID", customerId.toString()));
      CustomerMapper.mapToCustomer(customerDto, customer);
      this.customerRepository.save(customer);
      isUpdated = true;
    }
    return isUpdated;
  }

  @Override
  public boolean deleteAccount(String mobileNumber) {
    Customer customer = this.customerRepository.findByMobileNumber(mobileNumber)
        .orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));
    this.accountsRepository.deleteByCustomerId(customer.getCustomerId());
    this.customerRepository.deleteById(customer.getCustomerId());
    return true;
  }



}
