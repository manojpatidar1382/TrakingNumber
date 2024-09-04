package com.mycompany.tracking.controller;

import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TrackingErrorController implements ErrorController {

  @RequestMapping("/error")
  public ResponseEntity<Map<String, Object>> handleError(HttpServletRequest request) {

    String message = (String) request.getAttribute("jakarta.servlet.error.message");
    HttpStatus status = HttpStatus.BAD_REQUEST;

    // Create a custom error response
    Map<String, Object> response = new HashMap<>();
    response.put("timestamp", LocalDateTime.now());
    response.put("status", status.value());
    response.put("error", status.getReasonPhrase());
    response.put("message", message);

    return new ResponseEntity<>(response, status);
  }
}
