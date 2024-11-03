package com.mehmetolgun.repository;

import com.mehmetolgun.model.SoldCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoldCarRepository extends JpaRepository<SoldCar, Long> {
}
