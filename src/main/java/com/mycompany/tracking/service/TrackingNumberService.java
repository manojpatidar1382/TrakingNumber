package com.mycompany.tracking.service;

import com.mycompany.tracking.dto.TrackingNumberResponse;
import com.mycompany.tracking.entity.TrackingNumber;
import com.mycompany.tracking.repository.TrackingNumberRepository;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrackingNumberService {
  private static final String ALPHANUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
  private static final int TRACKING_NUMBER_LENGTH = 16;
  private final SecureRandom random = new SecureRandom();

  @Autowired
  private TrackingNumberRepository trackingNumberRepository;

  public TrackingNumberResponse generateTrackingNumber(
      String originCountryId, String destinationCountryId, Double weight,
      UUID customerId, LocalDateTime created_at, String customerName, String customerSlug) {
    String trackingNumber = generateUniqueTrackingNumber();

    TrackingNumber tracking = new TrackingNumber();
    tracking.setTrackingNumber(trackingNumber);
    tracking.setOriginCountryId(originCountryId);
    tracking.setDestinationCountryId(destinationCountryId);
    tracking.setWeight(weight);
    tracking.setCustomerId(customerId);
    tracking.setCreatedAt(created_at);
    tracking.setCustomerName(customerName);
    tracking.setCustomerSlug(customerSlug);
    trackingNumberRepository.save(tracking);
    return new TrackingNumberResponse(trackingNumber, LocalDateTime.now());
  }

  private String generateUniqueTrackingNumber() {
    String trackingNumber;
    do {
      trackingNumber = generateRandomTrackingNumber();
    } while (trackingNumberRepository.existsById((long) trackingNumber.hashCode()));
    return trackingNumber;
  }

  private String generateRandomTrackingNumber() {
    StringBuilder sb = new StringBuilder(TRACKING_NUMBER_LENGTH);
    for (int i = 0; i < TRACKING_NUMBER_LENGTH; i++) {
      sb.append(ALPHANUMERIC.charAt(random.nextInt(ALPHANUMERIC.length())));
    }
    return sb.toString();
  }
}
