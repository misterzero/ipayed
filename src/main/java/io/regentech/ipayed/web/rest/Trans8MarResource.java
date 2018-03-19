package io.regentech.ipayed.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.regentech.ipayed.domain.Trans8Mar;
import io.regentech.ipayed.service.Trans8MarService;
import io.regentech.ipayed.web.rest.errors.BadRequestAlertException;
import io.regentech.ipayed.web.rest.util.HeaderUtil;
import io.regentech.ipayed.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Trans8Mar.
 */
@RestController
@RequestMapping("/api")
public class Trans8MarResource {

    private final Logger log = LoggerFactory.getLogger(Trans8MarResource.class);

    private static final String ENTITY_NAME = "trans8Mar";

    private final Trans8MarService trans8MarService;

    public Trans8MarResource(Trans8MarService trans8MarService) {
        this.trans8MarService = trans8MarService;
    }

    /**
     * POST  /trans-8-mars : Create a new trans8Mar.
     *
     * @param trans8Mar the trans8Mar to create
     * @return the ResponseEntity with status 201 (Created) and with body the new trans8Mar, or with status 400 (Bad Request) if the trans8Mar has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/trans-8-mars")
    @Timed
    public ResponseEntity<Trans8Mar> createTrans8Mar(@Valid @RequestBody Trans8Mar trans8Mar) throws URISyntaxException {
        log.debug("REST request to save Trans8Mar : {}", trans8Mar);
        if (trans8Mar.getId() != null) {
            throw new BadRequestAlertException("A new trans8Mar cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Trans8Mar result = trans8MarService.save(trans8Mar);
        return ResponseEntity.created(new URI("/api/trans-8-mars/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /trans-8-mars : Updates an existing trans8Mar.
     *
     * @param trans8Mar the trans8Mar to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated trans8Mar,
     * or with status 400 (Bad Request) if the trans8Mar is not valid,
     * or with status 500 (Internal Server Error) if the trans8Mar couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/trans-8-mars")
    @Timed
    public ResponseEntity<Trans8Mar> updateTrans8Mar(@Valid @RequestBody Trans8Mar trans8Mar) throws URISyntaxException {
        log.debug("REST request to update Trans8Mar : {}", trans8Mar);
        if (trans8Mar.getId() == null) {
            return createTrans8Mar(trans8Mar);
        }
        Trans8Mar result = trans8MarService.save(trans8Mar);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, trans8Mar.getId().toString()))
            .body(result);
    }

    /**
     * GET  /trans-8-mars : get all the trans8Mars.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of trans8Mars in body
     */
    @GetMapping("/trans-8-mars")
    @Timed
    public ResponseEntity<List<Trans8Mar>> getAllTrans8Mars(Pageable pageable) {
        log.debug("REST request to get a page of Trans8Mars");
        Page<Trans8Mar> page = trans8MarService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/trans-8-mars");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /trans-8-mars/:id : get the "id" trans8Mar.
     *
     * @param id the id of the trans8Mar to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the trans8Mar, or with status 404 (Not Found)
     */
    @GetMapping("/trans-8-mars/{id}")
    @Timed
    public ResponseEntity<Trans8Mar> getTrans8Mar(@PathVariable Long id) {
        log.debug("REST request to get Trans8Mar : {}", id);
        Trans8Mar trans8Mar = trans8MarService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(trans8Mar));
    }

    /**
     * DELETE  /trans-8-mars/:id : delete the "id" trans8Mar.
     *
     * @param id the id of the trans8Mar to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/trans-8-mars/{id}")
    @Timed
    public ResponseEntity<Void> deleteTrans8Mar(@PathVariable Long id) {
        log.debug("REST request to delete Trans8Mar : {}", id);
        trans8MarService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
