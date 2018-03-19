package io.regentech.ipayed.service.impl;

import io.regentech.ipayed.service.Trans8MarService;
import io.regentech.ipayed.domain.Trans8Mar;
import io.regentech.ipayed.repository.Trans8MarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Trans8Mar.
 */
@Service
@Transactional
public class Trans8MarServiceImpl implements Trans8MarService {

    private final Logger log = LoggerFactory.getLogger(Trans8MarServiceImpl.class);

    private final Trans8MarRepository trans8MarRepository;

    public Trans8MarServiceImpl(Trans8MarRepository trans8MarRepository) {
        this.trans8MarRepository = trans8MarRepository;
    }

    /**
     * Save a trans8Mar.
     *
     * @param trans8Mar the entity to save
     * @return the persisted entity
     */
    @Override
    public Trans8Mar save(Trans8Mar trans8Mar) {
        log.debug("Request to save Trans8Mar : {}", trans8Mar);
        return trans8MarRepository.save(trans8Mar);
    }

    /**
     * Get all the trans8Mars.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Trans8Mar> findAll(Pageable pageable) {
        log.debug("Request to get all Trans8Mars");
        return trans8MarRepository.findAll(pageable);
    }

    /**
     * Get one trans8Mar by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Trans8Mar findOne(Long id) {
        log.debug("Request to get Trans8Mar : {}", id);
        return trans8MarRepository.findOne(id);
    }

    /**
     * Delete the trans8Mar by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Trans8Mar : {}", id);
        trans8MarRepository.delete(id);
    }
}
