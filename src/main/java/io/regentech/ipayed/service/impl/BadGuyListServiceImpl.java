package io.regentech.ipayed.service.impl;

import io.regentech.ipayed.service.BadGuyListService;
import io.regentech.ipayed.domain.BadGuyList;
import io.regentech.ipayed.repository.BadGuyListRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing BadGuyList.
 */
@Service
@Transactional
public class BadGuyListServiceImpl implements BadGuyListService {

    private final Logger log = LoggerFactory.getLogger(BadGuyListServiceImpl.class);

    private final BadGuyListRepository badGuyListRepository;

    public BadGuyListServiceImpl(BadGuyListRepository badGuyListRepository) {
        this.badGuyListRepository = badGuyListRepository;
    }

    /**
     * Save a badGuyList.
     *
     * @param badGuyList the entity to save
     * @return the persisted entity
     */
    @Override
    public BadGuyList save(BadGuyList badGuyList) {
        log.debug("Request to save BadGuyList : {}", badGuyList);
        return badGuyListRepository.save(badGuyList);
    }

    /**
     * Get all the badGuyLists.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<BadGuyList> findAll(Pageable pageable) {
        log.debug("Request to get all BadGuyLists");
        return badGuyListRepository.findAll(pageable);
    }

    /**
     * Get one badGuyList by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public BadGuyList findOne(Long id) {
        log.debug("Request to get BadGuyList : {}", id);
        return badGuyListRepository.findOne(id);
    }

    /**
     * Delete the badGuyList by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete BadGuyList : {}", id);
        badGuyListRepository.delete(id);
    }
}
