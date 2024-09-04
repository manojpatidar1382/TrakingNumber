package com.mycompany.tracking.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.mycompany.tracking.dto.TrackingNumberResponse;
import com.mycompany.tracking.service.TrackingNumberService;
import java.time.LocalDateTime;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(TrackingNumberController.class)
public class TrackingNumberControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private TrackingNumberService trackingNumberService;

  @Test
  public void testGetNextTrackingNumber() throws Exception {
    // Mocking the service response
    String mockTrackingNumber = "AB1234567890CD";
    LocalDateTime mockCreatedAt = LocalDateTime.parse("2024-09-04T06:21:19.6171147");
    TrackingNumberResponse mockResponse = new TrackingNumberResponse(mockTrackingNumber, mockCreatedAt);
    when(trackingNumberService.generateTrackingNumber("MY", "ID", 1.234,
        UUID.fromString("de619854-b59b-425e-9db4-943979e1bd49"),
        LocalDateTime.parse("2018-11-20T19:29:32"), "RedBox Logistics", "redbox-logistics"))
        .thenReturn(mockResponse);

    mockMvc.perform(get("/api/next-tracking-number")
            .param("origin_country_id", "MY")
            .param("destination_country_id", "ID")
            .param("weight", "1.234")
            .param("created_at", "2018-11-20T19:29:32")
            .param("customer_id", "de619854-b59b-425e-9db4-943979e1bd49")
            .param("customer_name", "RedBox Logistics")
            .param("customer_slug", "redbox-logistics"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.tracking_number").value(mockTrackingNumber))
        .andExpect(jsonPath("$.created_at").value("2024-09-04T06:21:19.6171147"));
  }

  @Test
  public void testGetNextTrackingNumber_MissingParameter() throws Exception {
    mockMvc.perform(get("/api/next-tracking-number")
            .param("origin_country_id", "MY"))
        .andExpect(status().isBadRequest());
  }
}
