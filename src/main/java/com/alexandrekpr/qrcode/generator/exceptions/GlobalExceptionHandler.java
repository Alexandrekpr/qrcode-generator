package com.alexandrekpr.qrcode.generator.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.alexandrekpr.qrcode.generator.dto.ErrorResponse;

import software.amazon.awssdk.services.s3.model.S3Exception;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(S3Exception.class)
    public ResponseEntity<ErrorResponse> handleS3Exception(S3Exception ex) {
        System.err.println("AWS S3 Error: " + ex.awsErrorDetails().errorMessage());
        // Log the error details for debugging and monitoring purposes
        ErrorResponse error = new ErrorResponse(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "Error processing external storage",
            System.currentTimeMillis(),
            List.of("Failed to persist the QR Code. Please try again later.")
        );
        return ResponseEntity.status(500).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception ex) {
        ErrorResponse error = new ErrorResponse(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "Internal server error",
            System.currentTimeMillis(),
            List.of(ex.getMessage())
        );
        return ResponseEntity.internalServerError().body(error);
    }

    @ExceptionHandler(QrCodeGenerationException.class)
    public ResponseEntity<ErrorResponse> handleQrCodeGeneration(QrCodeGenerationException ex) {
        ErrorResponse error = new ErrorResponse(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            ex.getMessage(),
            System.currentTimeMillis(),
            List.of(ex.getCause() != null ? ex.getCause().getMessage() : "Unknown error")
        );
        return ResponseEntity.status(500).body(error);
    }

    @ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
public ResponseEntity<ErrorResponse> handleValidationErrors(MethodArgumentNotValidException ex) {
    String errorMessage = ex.getBindingResult()
                            .getFieldErrors()
                            .get(0)
                            .getDefaultMessage();

    ErrorResponse error = new ErrorResponse(
        HttpStatus.BAD_REQUEST.value(),
        "Validation Failed",
        System.currentTimeMillis(), 
        List.of(errorMessage)
    );

    return ResponseEntity.badRequest().body(error);
}
}