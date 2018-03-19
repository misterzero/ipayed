package io.regentech.ipayed.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.regentech.ipayed.domain.BadGuyList;
import io.regentech.ipayed.service.BadGuyListService;
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
 * REST controller for managing BadGuyList.
 */
@RestController
@RequestMapping("/api")
public class BadGuyListResource {

    private final Logger log = LoggerFactory.getLogger(BadGuyListResource.class);

    private static final String ENTITY_NAME = "badGuyList";

    private final BadGuyListService badGuyListService;

    public BadGuyListResource(BadGuyListService badGuyListService) {
        this.badGuyListService = badGuyListService;
    }

    /**
     * POST  /bad-guy-lists : Create a new badGuyList.
     *
     * @param badGuyList the badGuyList to create
     * @return the ResponseEntity with status 201 (Created) and with body the new badGuyList, or with status 400 (Bad Request) if the badGuyList has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/bad-guy-lists")
    @Timed
    public ResponseEntity<BadGuyList> createBadGuyList(@Valid @RequestBody BadGuyList badGuyList) throws URISyntaxException {
        log.debug("REST request to save BadGuyList : {}", badGuyList);
        if (badGuyList.getId() != null) {
            throw new BadRequestAlertException("A new badGuyList cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BadGuyList result = badGuyListService.save(badGuyList);
        return ResponseEntity.created(new URI("/api/bad-guy-lists/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /bad-guy-lists : Updates an existing badGuyList.
     *
     * @param badGuyList the badGuyList to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated badGuyList,
     * or with status 400 (Bad Request) if the badGuyList is not valid,
     * or with status 500 (Internal Server Error) if the badGuyList couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/bad-guy-lists")
    @Timed
    public ResponseEntity<BadGuyList> updateBadGuyList(@Valid @RequestBody BadGuyList badGuyList) throws URISyntaxException {
        log.debug("REST request to update BadGuyList : {}", badGuyList);
        if (badGuyList.getId() == null) {
            return createBadGuyList(badGuyList);
        }
        BadGuyList result = badGuyListService.save(badGuyList);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, badGuyList.getId().toString()))
            .body(result);
    }

    /**
     * GET  /bad-guy-lists : get all the badGuyLists.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of badGuyLists in body
     */
    @GetMapping("/bad-guy-lists")
    @Timed
    public ResponseEntity<List<BadGuyList>> getAllBadGuyLists(Pageable pageable) {
        log.debug("REST request to get a page of BadGuyLists");
        Page<BadGuyList> page = badGuyListService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/bad-guy-lists");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /bad-guy-lists/:id : get the "id" badGuyList.
     *
     * @param id the id of the badGuyList to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the badGuyList, or with status 404 (Not Found)
     */
    @GetMapping("/bad-guy-lists/{id}")
    @Timed
    public ResponseEntity<BadGuyList> getBadGuyList(@PathVariable Long id) {
        log.debug("REST request to get BadGuyList : {}", id);
        BadGuyList badGuyList = badGuyListService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(badGuyList));
    }

    /**
     * DELETE  /bad-guy-lists/:id : delete the "id" badGuyList.
     *
     * @param id the id of the badGuyList to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/bad-guy-lists/{id}")
    @Timed
    public ResponseEntity<Void> deleteBadGuyList(@PathVariable Long id) {
        log.debug("REST request to delete BadGuyList : {}", id);
        badGuyListService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
