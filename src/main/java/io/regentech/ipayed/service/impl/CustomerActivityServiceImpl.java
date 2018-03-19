package io.regentech.ipayed.service.impl;

import io.regentech.ipayed.service.CustomerActivityService;
import io.regentech.ipayed.domain.CustomerActivity;
import io.regentech.ipayed.repository.CustomerActivityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing CustomerActivity.
 */
@Service
@Transactional
public class CustomerActivityServiceImpl implements CustomerActivityService {

    private final Logger log = LoggerFactory.getLogger(CustomerActivityServiceImpl.class);

    private final CustomerActivityRepository customerActivityRepository;

    public CustomerActivityServiceImpl(CustomerActivityRepository customerActivityRepository) {
        this.customerActivityRepository = customerActivityRepository;
    }

    /**
     * Save a customerActivity.
     *
     * @param customerActivity the entity to save
     * @return the persisted entity
     */
    @Override
    public CustomerActivity save(CustomerActivity customerActivity) {
        log.debug("Request to save CustomerActivity : {}", customerActivity);
        return customerActivityRepository.save(customerActivity);
    }

    /**
     * Get all the customerActivities.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CustomerActivity> findAll(Pageable pageable) {
        log.debug("Request to get all CustomerActivities");
        return customerActivityRepository.findAll(pageable);
    }

    /**
     * Get one customerActivity by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public CustomerActivity findOne(Long id) {
        log.debug("Request to get CustomerActivity : {}", id);
        return customerActivityRepository.findOne(id);
    }

    /**
     * Delete the customerActivity by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CustomerActivity : {}", id);
        customerActivityRepository.delete(id);
    }
}
