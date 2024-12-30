package com.pdev.keycloakservice.client.kcs_v1;

import com.pdev.keycloakservice.constant.CommonConstants;
import com.pdev.keycloakservice.dto.user.UserRequestDTO;
import com.pdev.keycloakservice.dto.user.auth.AuthResponseDTO;
import com.pdev.keycloakservice.util.CommonResponse;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * @author @maleeshasa
 * @Date 2024/11/15
 */
@FeignClient(name = "pdev-kcs-service")
@Headers("Content-Type: application/json")
public interface KCSV1ServiceClient {

    /**
     * This method is allowed to authenticate user by username and password and received the access and refresh token
     *
     * @param userRequest {@link UserRequestDTO} - user request dto
     * @return {@link ResponseEntity<CommonResponse>} - authenticated user token response
     * @author maleeshasa
     */
    @PostMapping(value = "/api/kcs_v1/v1/auth/user/token")
    ResponseEntity<CommonResponse> authenticateUser(@RequestBody UserRequestDTO userRequest);

    /**
     * This method is allowed to validate the token calling kcs_v1 service which is token issuer
     *
     * @param token {@link String} - token
     * @return {@link ResponseEntity<CommonResponse>} - validity response of the token
     * @author maleeshasa
     */
    @GetMapping(value = "/api/kcs_v1/v1/auth/user/token/validate")
    ResponseEntity<CommonResponse> validateToken(@RequestHeader(CommonConstants.AUTHORIZATION) String token);
}
