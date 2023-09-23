package com.lucaslucenak.Demacia.repositories;

import com.lucaslucenak.Demacia.models.OrderRequestModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRequestRepository extends JpaRepository<OrderRequestModel, Long> {
}
