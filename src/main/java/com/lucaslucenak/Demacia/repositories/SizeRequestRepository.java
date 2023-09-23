package com.lucaslucenak.Demacia.repositories;

import com.lucaslucenak.Demacia.models.SizeRequestModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SizeRequestRepository extends JpaRepository<SizeRequestModel, Long> {
}
