package io.regentech.ipayed.repository;

import io.regentech.ipayed.domain.CustomerActivity;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the CustomerActivity entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CustomerActivityRepository extends JpaRepository<CustomerActivity, Long> {

}
