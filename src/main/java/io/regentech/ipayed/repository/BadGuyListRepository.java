package io.regentech.ipayed.repository;

import io.regentech.ipayed.domain.BadGuyList;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the BadGuyList entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BadGuyListRepository extends JpaRepository<BadGuyList, Long> {

}
