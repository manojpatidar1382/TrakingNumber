package com.mycompany.tracking.controller;

import com.mycompany.tracking.dto.TrackingNumberResponse;
import com.mycompany.tracking.service.TrackingNumberService;
import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TrackingNumberController {

  @Autowired
  private TrackingNumberService trackingNumberService;

  @GetMapping("/next-tracking-number")
  public TrackingNumberResponse getNextTrackingNumber(
      @RequestParam(required = true) String origin_country_id,
      @RequestParam(required = true) String destination_country_id,
      @RequestParam(required = true) Double weight,
      @RequestParam(required = true) UUID customer_id,
      @RequestParam(required = true) LocalDateTime created_at,
      @RequestParam(required = true) String customer_name,
      @RequestParam(required = true) String customer_slug) {
    return trackingNumberService.generateTrackingNumber(origin_country_id, destination_country_id, weight,
        customer_id, created_at, customer_name, customer_slug);
  }
}