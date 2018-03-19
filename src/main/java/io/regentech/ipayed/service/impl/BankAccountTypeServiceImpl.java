package io.regentech.ipayed.service.impl;

import io.regentech.ipayed.service.BankAccountTypeService;
import io.regentech.ipayed.domain.BankAccountType;
import io.regentech.ipayed.repository.BankAccountTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing BankAccountType.
 */
@Service
@Transactional
public class BankAccountTypeServiceImpl implements BankAccountTypeService {

    private final Logger log = LoggerFactory.getLogger(BankAccountTypeServiceImpl.class);

    private final BankAccountTypeRepository bankAccountTypeRepository;

    public BankAccountTypeServiceImpl(BankAccountTypeRepository bankAccountTypeRepository) {
        this.bankAccountTypeRepository = bankAccountTypeRepository;
    }

    /**
     * Save a bankAccountType.
     *
     * @param bankAccountType the entity to save
     * @return the persisted entity
     */
    @Override
    public BankAccountType save(BankAccountType bankAccountType) {
        log.debug("Request to save BankAccountType : {}", bankAccountType);
        return bankAccountTypeRepository.save(bankAccountType);
    }

    /**
     * Get all the bankAccountTypes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<BankAccountType> findAll(Pageable pageable) {
        log.debug("Request to get all BankAccountTypes");
        return bankAccountTypeRepository.findAll(pageable);
    }

    /**
     * Get one bankAccountType by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public BankAccountType findOne(Long id) {
        log.debug("Request to get BankAccountType : {}", id);
        return bankAccountTypeRepository.findOne(id);
    }

    /**
     * Delete the bankAccountType by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete BankAccountType : {}", id);
        bankAccountTypeRepository.delete(id);
    }
}
