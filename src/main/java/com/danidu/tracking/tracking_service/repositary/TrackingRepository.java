package com.danidu.tracking.tracking_service.repositary;

import com.danidu.tracking.tracking_service.model.TrackingNumber;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface TrackingRepository extends MongoRepository<TrackingNumber, String> {
    boolean existsByTrackingNumber(String trackingNumber);
}

