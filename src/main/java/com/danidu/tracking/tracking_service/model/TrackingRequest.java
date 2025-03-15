package com.danidu.tracking.tracking_service.model;

import java.time.OffsetDateTime;
import java.util.UUID;
import java.util.regex.Pattern;

public class TrackingRequest {

    private String originCountryId;
    private String destinationCountryId;
    private String weight;
    private OffsetDateTime createdAt;  // Changed to OffsetDateTime
    private String customerId;
    private String customerName;
    private String customerSlug;

    private static final Pattern COUNTRY_CODE_PATTERN = Pattern.compile("^[A-Z]{2}$");
    private static final Pattern WEIGHT_PATTERN = Pattern.compile("^[0-9]+(\\.[0-9]{1,3})?$");
    private static final Pattern SLUG_PATTERN = Pattern.compile("^[a-z0-9]+(?:-[a-z0-9]+)*$");

    public TrackingRequest() {
    }

    public String getOriginCountryId() {
        return originCountryId;
    }

    public void setOriginCountryId(String originCountryId) throws IllegalArgumentException {
        if (originCountryId == null || !COUNTRY_CODE_PATTERN.matcher(originCountryId).matches()) {
            throw new IllegalArgumentException("Invalid origin country code. Must be in ISO 3166-1 alpha-2 format.");
        }
        this.originCountryId = originCountryId;
    }

    public String getDestinationCountryId() {
        return destinationCountryId;
    }

    public void setDestinationCountryId(String destinationCountryId) throws IllegalArgumentException {
        if (destinationCountryId == null || !COUNTRY_CODE_PATTERN.matcher(destinationCountryId).matches()) {
            throw new IllegalArgumentException("Invalid destination country code. Must be in ISO 3166-1 alpha-2 format.");
        }
        this.destinationCountryId = destinationCountryId;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) throws IllegalArgumentException {
        if (weight == null || !WEIGHT_PATTERN.matcher(weight).matches()) {
            throw new IllegalArgumentException("Invalid weight. Must be a decimal number with up to three decimal places.");
        }
        this.weight = weight;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) throws IllegalArgumentException {
        if (createdAt == null) {
            throw new IllegalArgumentException("created_at cannot be null.");
        }
        this.createdAt = createdAt;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) throws IllegalArgumentException {
        try {
            UUID.fromString(customerId);  // Will throw IllegalArgumentException if not a valid UUID
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid customer_id. Must be a valid UUID.");
        }
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) throws IllegalArgumentException {
        if (customerName == null || customerName.trim().isEmpty()) {
            throw new IllegalArgumentException("customer_name cannot be empty.");
        }
        this.customerName = customerName;
    }

    public String getCustomerSlug() {
        return customerSlug;
    }

    public void setCustomerSlug(String customerSlug) throws IllegalArgumentException {
        if (customerSlug == null || !SLUG_PATTERN.matcher(customerSlug).matches()) {
            throw new IllegalArgumentException("Invalid customer_slug. Must be in slug-case.");
        }
        this.customerSlug = customerSlug;
    }
}
