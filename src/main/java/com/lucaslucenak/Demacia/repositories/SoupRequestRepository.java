package com.lucaslucenak.Demacia.repositories;

import com.lucaslucenak.Demacia.models.SoupRequestModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoupRequestRepository extends JpaRepository<SoupRequestModel, Long> {
}
