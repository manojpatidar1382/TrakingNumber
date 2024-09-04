package com.mycompany.tracking.repository;

import com.mycompany.tracking.entity.TrackingNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackingNumberRepository extends JpaRepository<TrackingNumber, Long> {
}
