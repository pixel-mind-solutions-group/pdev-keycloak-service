package com.pdev.keycloakservice.util;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class CommonResponse {
    private HttpStatus status;
    private String message;
    private Object data;
}
