package com.alexandrekpr.qrcode.generator.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record QrCodeGenerateRequest(
  @NotBlank(message = "The text to encode cannot be empty.")
  @Size(min = 1, max = 2000, message = "The text must be between 1 and 2000 characters.")
  String text
) {}
