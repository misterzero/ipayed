package io.regentech.ipayed.repository;

import io.regentech.ipayed.domain.BankAccountType;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the BankAccountType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BankAccountTypeRepository extends JpaRepository<BankAccountType, Long> {

}
