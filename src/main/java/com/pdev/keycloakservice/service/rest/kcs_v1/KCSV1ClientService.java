package com.pdev.keycloakservice.service.rest.kcs_v1;

import com.pdev.keycloakservice.dto.user.UserRequestDTO;
import com.pdev.keycloakservice.dto.user.auth.AuthResponseDTO;
import com.pdev.keycloakservice.util.CommonResponse;

/**
 * @author @maleeshasa
 * @Date 2024/11/16
 */
public interface KCSV1ClientService {

    /**
     * This method is allowed to authenticate user by username and password and received the access and refresh token
     *
     * @param userRequest {@link UserRequestDTO} - user request dto
     * @return {@link AuthResponseDTO} - authenticated user token response
     * @author maleeshasa
     */
    AuthResponseDTO authenticateUser(UserRequestDTO userRequest);

    /**
     * This method is allowed to validate the token calling kcs_v1 service which is token issuer
     *
     * @param token {@link String} - token
     * @return {@link CommonResponse} - validity response of the token
     * @author maleeshasa
     */
    CommonResponse validateToken(String token);
}
