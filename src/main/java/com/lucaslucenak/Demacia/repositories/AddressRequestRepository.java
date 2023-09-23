package com.lucaslucenak.Demacia.repositories;

import com.lucaslucenak.Demacia.models.AddressRequestModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRequestRepository extends JpaRepository<AddressRequestModel, Long> {
}
