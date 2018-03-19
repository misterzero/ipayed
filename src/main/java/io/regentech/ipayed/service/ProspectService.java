package io.regentech.ipayed.service;

import io.regentech.ipayed.domain.Prospect;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Prospect.
 */
public interface ProspectService {

    /**
     * Save a prospect.
     *
     * @param prospect the entity to save
     * @return the persisted entity
     */
    Prospect save(Prospect prospect);

    /**
     * Get all the prospects.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Prospect> findAll(Pageable pageable);

    /**
     * Get the "id" prospect.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Prospect findOne(Long id);

    /**
     * Delete the "id" prospect.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
