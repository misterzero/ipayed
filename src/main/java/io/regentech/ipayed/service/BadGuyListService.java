package io.regentech.ipayed.service;

import io.regentech.ipayed.domain.BadGuyList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing BadGuyList.
 */
public interface BadGuyListService {

    /**
     * Save a badGuyList.
     *
     * @param badGuyList the entity to save
     * @return the persisted entity
     */
    BadGuyList save(BadGuyList badGuyList);

    /**
     * Get all the badGuyLists.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<BadGuyList> findAll(Pageable pageable);

    /**
     * Get the "id" badGuyList.
     *
     * @param id the id of the entity
     * @return the entity
     */
    BadGuyList findOne(Long id);

    /**
     * Delete the "id" badGuyList.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
