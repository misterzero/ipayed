package io.regentech.ipayed.service;

import io.regentech.ipayed.domain.CustomerActivity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing CustomerActivity.
 */
public interface CustomerActivityService {

    /**
     * Save a customerActivity.
     *
     * @param customerActivity the entity to save
     * @return the persisted entity
     */
    CustomerActivity save(CustomerActivity customerActivity);

    /**
     * Get all the customerActivities.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CustomerActivity> findAll(Pageable pageable);

    /**
     * Get the "id" customerActivity.
     *
     * @param id the id of the entity
     * @return the entity
     */
    CustomerActivity findOne(Long id);

    /**
     * Delete the "id" customerActivity.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
