package com.lucaslucenak.Demacia.repositories;

import com.lucaslucenak.Demacia.models.ClientAccountRequestModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientAccountRequestRepository extends JpaRepository<ClientAccountRequestModel, Long> {
}
