package dev.jeffersonamorim.accountsrestapi.mapper;

import dev.jeffersonamorim.accountsrestapi.dto.AccountsDto;
import dev.jeffersonamorim.accountsrestapi.entity.Accounts;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
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
