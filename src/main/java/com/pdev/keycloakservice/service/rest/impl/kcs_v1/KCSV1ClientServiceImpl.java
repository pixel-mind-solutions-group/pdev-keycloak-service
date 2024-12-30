package com.pdev.keycloakservice.service.rest.impl.kcs_v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pdev.keycloakservice.client.kcs_v1.KCSV1ServiceClient;
import com.pdev.keycloakservice.dto.user.UserRequestDTO;
import com.pdev.keycloakservice.dto.user.auth.AuthResponseDTO;
import com.pdev.keycloakservice.exception.BaseException;
import com.pdev.keycloakservice.exception.FeignCustomException;
import com.pdev.keycloakservice.exception.RecordNotFoundException;
import com.pdev.keycloakservice.service.rest.kcs_v1.KCSV1ClientService;
import com.pdev.keycloakservice.util.CommonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author @maleeshasa
 * @Date 2024/11/16
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class KCSV1ClientServiceImpl implements KCSV1ClientService {

    private final KCSV1ServiceClient kcsv1ServiceClient;
    private final ObjectMapper objectMapper;

    /**
     * This method is allowed to authenticate user by username and password and received the access and refresh token
     *
     * @param userRequest {@link UserRequestDTO} - user request dto
     * @return {@link AuthResponseDTO} - authenticated user token response
     * @author maleeshasa
     */
    @Override
    public AuthResponseDTO authenticateUser(UserRequestDTO userRequest) {
        log.info("KCSV1ClientServiceImpl.authenticateUser() => started.");
        try {
            log.info("Calling kcs_v1 service to authenticate user by username and password and received the access and refresh token...");
            ResponseEntity<CommonResponse> response = kcsv1ServiceClient.authenticateUser(userRequest);
            if (response.getStatusCode().equals(HttpStatus.OK) &&
                    Objects.requireNonNull(response.getBody()).getStatus().equals(HttpStatus.ACCEPTED) &&
                    response.getBody().getData() != null) {
                log.info("User is authenticated and received token.");
                return objectMapper.convertValue(response.getBody().getData(), AuthResponseDTO.class);

            } else {
                log.error("Error while fetching user token response.");
                throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error while fetching user token response.");
            }

        } catch (RecordNotFoundException e) {
            log.error(e.getMessage());
            throw new BaseException(HttpStatus.NOT_FOUND.value(), e.getMessage());

        } catch (FeignCustomException e) {
            log.error("Error occurred while calling kcs_v1 service to fetch user by username and password. Error: {}", e.getMessage());
            throw new BaseException(500, "Error occurred while calling keycloak to fetch user by username and password. Error: " + e.getMessage());
        }
    }

    /**
     * This method is allowed to validate the token calling kcs_v1 service which is token issuer
     *
     * @param token {@link String} - token
     * @return {@link CommonResponse} - validity response of the token
     * @author maleeshasa
     */
    @Override
    public CommonResponse validateToken(String token) {
        log.info("KCSV1ClientServiceImpl.validateToken() => started.");
        try {
            log.info("Calling kcs_v1 service to validate the token...");
            ResponseEntity<CommonResponse> response = kcsv1ServiceClient.validateToken(token);
            if (response.getStatusCode().equals(HttpStatus.OK)) {
                log.info("Token is validated.");
                return response.getBody();

            } else {
                log.warn("Error while validating user token.");
                throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error while validating user token.");
            }

        } catch (FeignCustomException e) {
            log.warn("Error occurred while calling kcs_v1 to validate user token. Error: {}", e.getMessage());
            throw new BaseException(500, "Error occurred while calling kcs_v1 to validate user token. Error: " + e.getMessage());
        }
    }
}
