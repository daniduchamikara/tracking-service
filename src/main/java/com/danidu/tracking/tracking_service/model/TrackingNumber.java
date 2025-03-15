package com.danidu.tracking.tracking_service.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "tracking_numbers")
public class TrackingNumber {

    @Id
    private String id;
    private String trackingNumber;

    public TrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }
}
