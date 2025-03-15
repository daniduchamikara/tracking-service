package com.danidu.tracking.tracking_service.service;
import com.danidu.tracking.tracking_service.model.TrackingNumber;
import com.danidu.tracking.tracking_service.model.TrackingRequest;
import com.danidu.tracking.tracking_service.repositary.TrackingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;


import java.util.UUID;
import java.util.regex.Pattern;

@Service
@Validated
public class TrackingService {

    @Autowired
    private TrackingRepository trackingRepository;

    private static final String TRACKING_NUMBER_PATTERN = "^[A-Z0-9]{1,16}$";

    // Generate the tracking number
    public String generateTrackingNumber(TrackingRequest request) {

        // Generate the unique part using UUID-based encoding
        String uniquePart = generateUniquePart();

        // Combine the origin and destination country codes with the unique identifier
        String trackingNumber = String.format("%s%s%s",
                request.getOriginCountryId().toUpperCase(),
                request.getDestinationCountryId().toUpperCase(),
                uniquePart);

        // Ensure it matches the regex pattern
        if (!Pattern.matches(TRACKING_NUMBER_PATTERN, trackingNumber)) {
            throw new IllegalArgumentException("Generated tracking number does not match the required pattern");
        }

        // Save the generated tracking number to MongoDB
        saveTrackingNumber(trackingNumber);

        return trackingNumber;
    }



    // Generate a unique part for the tracking number using UUID encoding
    private String generateUniquePart() {
        // Generate a random UUID and convert it to a string
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "").toUpperCase().substring(0, 8); // Keep only the first 8 characters
    }

    // Save the tracking number to MongoDB (to ensure uniqueness)
    private void saveTrackingNumber(String trackingNumber) {
        // Use the repository to save the tracking number to MongoDB
        if (trackingRepository.existsByTrackingNumber(trackingNumber)) {
            throw new IllegalStateException("Tracking number already exists");
        }
        trackingRepository.save(new TrackingNumber(trackingNumber));
    }
}