package com.danidu.tracking.tracking_service.controller;

import com.danidu.tracking.tracking_service.model.TrackingRequest;
import com.danidu.tracking.tracking_service.model.TrackingResponse;
import com.danidu.tracking.tracking_service.service.TrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class TrackingController {

    @Autowired
    private TrackingService trackingService;

    @GetMapping("/next-tracking-number")
    public TrackingResponse generateTrackingNumber(
            @RequestParam String originCountryId,
            @RequestParam String destinationCountryId,
            @RequestParam String weight,
            @RequestParam String createdAt,  // Get as String first
            @RequestParam String customerId,
            @RequestParam String customerName,
            @RequestParam String customerSlug) {

        // Fix the space issue in the createdAt string before parsing
        createdAt = createdAt.replace(" ", "+");

        // Convert the fixed string to OffsetDateTime
        OffsetDateTime createdAtDateTime = OffsetDateTime.parse(createdAt);

        // Create the TrackingRequest object
        TrackingRequest request = new TrackingRequest();
        request.setOriginCountryId(originCountryId);
        request.setDestinationCountryId(destinationCountryId);
        request.setWeight(weight);
        request.setCreatedAt(createdAtDateTime);  // Use the fixed OffsetDateTime
        request.setCustomerId(customerId);
        request.setCustomerName(customerName);
        request.setCustomerSlug(customerSlug);

        // Generate the tracking number
        String trackingNumber = trackingService.generateTrackingNumber(request);

        // Create a response object with tracking number
        TrackingResponse response = new TrackingResponse(trackingNumber, createdAtDateTime.toString());

        return response;
    }
}