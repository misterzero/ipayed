package io.regentech.ipayed.service;

import io.regentech.ipayed.domain.BankAccountType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing BankAccountType.
 */
public interface BankAccountTypeService {

    /**
     * Save a bankAccountType.
     *
     * @param bankAccountType the entity to save
     * @return the persisted entity
     */
    BankAccountType save(BankAccountType bankAccountType);

    /**
     * Get all the bankAccountTypes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<BankAccountType> findAll(Pageable pageable);

    /**
     * Get the "id" bankAccountType.
     *
     * @param id the id of the entity
     * @return the entity
     */
    BankAccountType findOne(Long id);

    /**
     * Delete the "id" bankAccountType.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
