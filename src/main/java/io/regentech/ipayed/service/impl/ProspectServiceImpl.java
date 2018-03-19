package io.regentech.ipayed.service.impl;

import io.regentech.ipayed.service.ProspectService;
import io.regentech.ipayed.domain.Prospect;
import io.regentech.ipayed.repository.ProspectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Prospect.
 */
@Service
@Transactional
public class ProspectServiceImpl implements ProspectService {

    private final Logger log = LoggerFactory.getLogger(ProspectServiceImpl.class);

    private final ProspectRepository prospectRepository;

    public ProspectServiceImpl(ProspectRepository prospectRepository) {
        this.prospectRepository = prospectRepository;
    }

    /**
     * Save a prospect.
     *
     * @param prospect the entity to save
     * @return the persisted entity
     */
    @Override
    public Prospect save(Prospect prospect) {
        log.debug("Request to save Prospect : {}", prospect);
        return prospectRepository.save(prospect);
    }

    /**
     * Get all the prospects.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Prospect> findAll(Pageable pageable) {
        log.debug("Request to get all Prospects");
        return prospectRepository.findAll(pageable);
    }

    /**
     * Get one prospect by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Prospect findOne(Long id) {
        log.debug("Request to get Prospect : {}", id);
        return prospectRepository.findOne(id);
    }

    /**
     * Delete the prospect by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Prospect : {}", id);
        prospectRepository.delete(id);
    }
}
