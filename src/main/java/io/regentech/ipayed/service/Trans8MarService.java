package io.regentech.ipayed.service;

import io.regentech.ipayed.domain.Trans8Mar;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Trans8Mar.
 */
public interface Trans8MarService {

    /**
     * Save a trans8Mar.
     *
     * @param trans8Mar the entity to save
     * @return the persisted entity
     */
    Trans8Mar save(Trans8Mar trans8Mar);

    /**
     * Get all the trans8Mars.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Trans8Mar> findAll(Pageable pageable);

    /**
     * Get the "id" trans8Mar.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Trans8Mar findOne(Long id);

    /**
     * Delete the "id" trans8Mar.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
