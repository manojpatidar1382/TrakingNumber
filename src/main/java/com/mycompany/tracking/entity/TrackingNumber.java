package com.mycompany.tracking.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Data;

@Entity
@Data
public class TrackingNumber {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(unique = true, nullable = false)
  private String trackingNumber;

  @Column
  private LocalDateTime createdAt;

  @Column
  private String originCountryId;

  @Column
  private String destinationCountryId;

  @Column
  private Double weight;

  @Column
  private UUID customerId;

  @Column
  private String customerName;

  @Column
  private String customerSlug;
}