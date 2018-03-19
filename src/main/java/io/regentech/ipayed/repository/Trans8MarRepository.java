package io.regentech.ipayed.repository;

import io.regentech.ipayed.domain.Trans8Mar;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Trans8Mar entity.
 */
@SuppressWarnings("unused")
@Repository
public interface Trans8MarRepository extends JpaRepository<Trans8Mar, Long> {

}
