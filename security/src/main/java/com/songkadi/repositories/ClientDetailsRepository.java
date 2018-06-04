package com.songkadi.repositories;

import com.songkadi.model.ClientDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientDetailsRepository extends JpaRepository<ClientDetail, Long> {

    ClientDetail findByUsername(String name);
}
