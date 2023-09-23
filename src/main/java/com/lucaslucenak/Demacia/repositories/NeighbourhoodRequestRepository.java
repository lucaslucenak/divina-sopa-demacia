package com.lucaslucenak.Demacia.repositories;

import com.lucaslucenak.Demacia.models.NeighbourhoodRequestModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NeighbourhoodRequestRepository extends JpaRepository<NeighbourhoodRequestModel, Long> {
}
