package dev.jeffersonamorim.accountsrestapi.mapper;

import dev.jeffersonamorim.accountsrestapi.dto.CustomerDto;
import dev.jeffersonamorim.accountsrestapi.entity.Customer;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomerMapper {

  public static CustomerDto mapToCustomerDto(Customer customer, CustomerDto customerDto) {
    customerDto.setName(customer.getName());
    customerDto.setEmail(customer.getEmail());
    customerDto.setMobileNumber(customer.getMobileNumber());
    return customerDto;
  }

  public static Customer mapToCustomer(CustomerDto customerDto, Customer customer) {
    customer.setName(customerDto.getName());
    customer.setEmail(customerDto.getEmail());
    customer.setMobileNumber(customerDto.getMobileNumber());
    return customer;
  }
}
