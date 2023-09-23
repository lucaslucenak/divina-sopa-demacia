package com.lucaslucenak.Demacia.repositories;

import com.lucaslucenak.Demacia.models.DrinkRequestModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrinkRequestRepository extends JpaRepository<DrinkRequestModel, Long> {
}
