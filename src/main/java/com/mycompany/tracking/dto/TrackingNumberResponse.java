package com.mycompany.tracking.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrackingNumberResponse {

  @JsonProperty("tracking_number")
  private String trackingNumber;
  @JsonProperty("created_at")
  private String createdAt;

  public TrackingNumberResponse(String trackingNumber, LocalDateTime createdAt) {
    this.trackingNumber = trackingNumber;
    this.createdAt = createdAt.format(DateTimeFormatter.ISO_DATE_TIME);  // RFC 3339 format
  }
}

