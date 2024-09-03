package dev.jeffersonamorim.accountsrestapi.controller;

import dev.jeffersonamorim.accountsrestapi.constants.AccountsConstants;
import dev.jeffersonamorim.accountsrestapi.dto.CustomerDto;
import dev.jeffersonamorim.accountsrestapi.dto.ResponseDto;
import dev.jeffersonamorim.accountsrestapi.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
public class AccountsController {

  private final IAccountsService iAccountsService;

  @PostMapping(path = "create")
  public ResponseEntity<ResponseDto> createAccount(@RequestBody CustomerDto customerDto) {

    this.iAccountsService.createAccount(customerDto);

    return ResponseEntity.status(HttpStatus.CREATED)
        .body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
  }


  @GetMapping("fetch")
  public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam String mobileNumber) {
    CustomerDto customerDto = this.iAccountsService.fetchAccount(mobileNumber);
    return ResponseEntity.status(HttpStatus.OK).body(customerDto);
  }


  @PutMapping("/update")
  public ResponseEntity<ResponseDto> updateAccountDetails(@RequestBody CustomerDto customerDto) {
    boolean isUpdated = this.iAccountsService.updateAccount(customerDto);
    if (isUpdated) {
      return ResponseEntity.status(HttpStatus.OK)
          .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
    } else {
      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(
          new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_UPDATE));
    }
  }

  @DeleteMapping("/delete")
  public ResponseEntity<ResponseDto> deleteAccountDetails(@RequestParam String mobileNumber) {
    boolean isDeleted = this.iAccountsService.deleteAccount(mobileNumber);
    if (isDeleted) {
      return ResponseEntity.status(HttpStatus.OK)
          .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
    } else {
      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(
          new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_DELETE));
    }
  }

}
