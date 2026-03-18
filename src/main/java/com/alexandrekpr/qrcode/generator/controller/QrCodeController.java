package com.alexandrekpr.qrcode.generator.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.alexandrekpr.qrcode.generator.dto.QrCodeGenerateRequest;
import com.alexandrekpr.qrcode.generator.dto.QrCodeGenerateResponse;
import com.alexandrekpr.qrcode.generator.service.QrCodeGeneratorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/qrcode")
public class QrCodeController {

  private final QrCodeGeneratorService qrCodeGeneratorService;

  public QrCodeController(QrCodeGeneratorService qrCodeGeneratorService) {
    this.qrCodeGeneratorService = qrCodeGeneratorService;
  }
  
  @PostMapping
  public ResponseEntity<QrCodeGenerateResponse> generate(@Valid @RequestBody QrCodeGenerateRequest request) {
      QrCodeGenerateResponse response = this.qrCodeGeneratorService.generateAndUploadQrCode(request.text());
      return ResponseEntity.ok(response);
  }
}