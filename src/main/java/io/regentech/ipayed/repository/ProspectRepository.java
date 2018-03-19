package io.regentech.ipayed.repository;

import io.regentech.ipayed.domain.Prospect;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Prospect entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProspectRepository extends JpaRepository<Prospect, Long> {

}
