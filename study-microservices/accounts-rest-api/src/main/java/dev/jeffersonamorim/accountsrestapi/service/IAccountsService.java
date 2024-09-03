package dev.jeffersonamorim.accountsrestapi.service;

import dev.jeffersonamorim.accountsrestapi.dto.CustomerDto;

public interface IAccountsService {

  void createAccount(CustomerDto customerDto);

  CustomerDto fetchAccount(String mobileNumber);

  boolean updateAccount(CustomerDto customerDto);

  boolean deleteAccount(String mobileNumber);
}
