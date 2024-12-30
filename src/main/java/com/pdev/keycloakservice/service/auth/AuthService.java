package com.pdev.keycloakservice.service.auth;


import com.pdev.keycloakservice.dto.user.UserRequestDTO;
import com.pdev.keycloakservice.util.CommonResponse;

/**
 * @author @maleeshasa
 * @Date 2024/11/15
 */
public interface AuthService {

    /**
     * This method is allowed to authenticate user by username and password and received the access and refresh token
     *
     * @param userRequest {@link UserRequestDTO} - user request dto
     * @return {@link CommonResponse} - authenticated user token response
     * @author maleeshasa
     */
    CommonResponse authenticateUser(UserRequestDTO userRequest);

    /**
     * This method is allowed to validate the token calling kcs_v1 service which is token issuer
     *
     * @param token {@link String} - token
     * @return {@link CommonResponse} - validity response of the token
     * @author maleeshasa
     */
    CommonResponse validateToken(String token);
}
