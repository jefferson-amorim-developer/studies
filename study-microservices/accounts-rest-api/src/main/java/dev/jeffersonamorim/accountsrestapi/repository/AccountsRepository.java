package dev.jeffersonamorim.accountsrestapi.repository;

import dev.jeffersonamorim.accountsrestapi.entity.Accounts;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;


public interface AccountsRepository extends JpaRepository<Accounts, Long> {

  Optional<Accounts> findByCustomerId(Long customerId);

  @Transactional
  void deleteByCustomerId(Long customerId);
}
