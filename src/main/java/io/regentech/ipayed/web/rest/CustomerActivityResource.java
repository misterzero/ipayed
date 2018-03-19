package io.regentech.ipayed.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.regentech.ipayed.domain.CustomerActivity;
import io.regentech.ipayed.service.CustomerActivityService;
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
 * REST controller for managing CustomerActivity.
 */
@RestController
@RequestMapping("/api")
public class CustomerActivityResource {

    private final Logger log = LoggerFactory.getLogger(CustomerActivityResource.class);

    private static final String ENTITY_NAME = "customerActivity";

    private final CustomerActivityService customerActivityService;

    public CustomerActivityResource(CustomerActivityService customerActivityService) {
        this.customerActivityService = customerActivityService;
    }

    /**
     * POST  /customer-activities : Create a new customerActivity.
     *
     * @param customerActivity the customerActivity to create
     * @return the ResponseEntity with status 201 (Created) and with body the new customerActivity, or with status 400 (Bad Request) if the customerActivity has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/customer-activities")
    @Timed
    public ResponseEntity<CustomerActivity> createCustomerActivity(@Valid @RequestBody CustomerActivity customerActivity) throws URISyntaxException {
        log.debug("REST request to save CustomerActivity : {}", customerActivity);
        if (customerActivity.getId() != null) {
            throw new BadRequestAlertException("A new customerActivity cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CustomerActivity result = customerActivityService.save(customerActivity);
        return ResponseEntity.created(new URI("/api/customer-activities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /customer-activities : Updates an existing customerActivity.
     *
     * @param customerActivity the customerActivity to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated customerActivity,
     * or with status 400 (Bad Request) if the customerActivity is not valid,
     * or with status 500 (Internal Server Error) if the customerActivity couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/customer-activities")
    @Timed
    public ResponseEntity<CustomerActivity> updateCustomerActivity(@Valid @RequestBody CustomerActivity customerActivity) throws URISyntaxException {
        log.debug("REST request to update CustomerActivity : {}", customerActivity);
        if (customerActivity.getId() == null) {
            return createCustomerActivity(customerActivity);
        }
        CustomerActivity result = customerActivityService.save(customerActivity);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, customerActivity.getId().toString()))
            .body(result);
    }

    /**
     * GET  /customer-activities : get all the customerActivities.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of customerActivities in body
     */
    @GetMapping("/customer-activities")
    @Timed
    public ResponseEntity<List<CustomerActivity>> getAllCustomerActivities(Pageable pageable) {
        log.debug("REST request to get a page of CustomerActivities");
        Page<CustomerActivity> page = customerActivityService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/customer-activities");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /customer-activities/:id : get the "id" customerActivity.
     *
     * @param id the id of the customerActivity to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the customerActivity, or with status 404 (Not Found)
     */
    @GetMapping("/customer-activities/{id}")
    @Timed
    public ResponseEntity<CustomerActivity> getCustomerActivity(@PathVariable Long id) {
        log.debug("REST request to get CustomerActivity : {}", id);
        CustomerActivity customerActivity = customerActivityService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(customerActivity));
    }

    /**
     * DELETE  /customer-activities/:id : delete the "id" customerActivity.
     *
     * @param id the id of the customerActivity to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/customer-activities/{id}")
    @Timed
    public ResponseEntity<Void> deleteCustomerActivity(@PathVariable Long id) {
        log.debug("REST request to delete CustomerActivity : {}", id);
        customerActivityService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
