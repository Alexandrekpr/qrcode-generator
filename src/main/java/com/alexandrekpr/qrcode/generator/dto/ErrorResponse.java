package com.alexandrekpr.qrcode.generator.dto;

import java.util.List;

public record ErrorResponse(
    int status,
    String message,
    long timestamp,
    List<String> details
) {}