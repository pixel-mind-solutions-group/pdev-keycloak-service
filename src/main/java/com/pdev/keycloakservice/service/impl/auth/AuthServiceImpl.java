package com.pdev.keycloakservice.service.impl.auth;

import com.pdev.keycloakservice.dto.user.UserRequestDTO;
import com.pdev.keycloakservice.dto.user.auth.AuthResponseDTO;
import com.pdev.keycloakservice.service.auth.AuthService;
import com.pdev.keycloakservice.service.rest.kcs_v1.KCSV1ClientService;
import com.pdev.keycloakservice.util.CommonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * @author @maleeshasa
 * @Date 2024/11/15
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    private final KCSV1ClientService kcsv1ClientService;

    /**
     * This method is allowed to authenticate user by username and password and received the access and refresh token
     *
     * @param userRequest {@link UserRequestDTO} - user request dto
     * @return {@link CommonResponse} - authenticated user token response
     * @author maleeshasa
     */
    @Override
    public CommonResponse authenticateUser(UserRequestDTO userRequest) {
        AuthResponseDTO response = kcsv1ClientService.authenticateUser(userRequest);
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setData(response);
        commonResponse.setStatus(HttpStatus.ACCEPTED);
        commonResponse.setMessage("Authentication accepted.");
        return commonResponse;
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
        return kcsv1ClientService.validateToken(token);
    }
}
