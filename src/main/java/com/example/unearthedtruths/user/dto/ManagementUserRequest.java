package com.example.unearthedtruths.user.dto;

import lombok.Data;

/**
 * DTO for incoming user data (create/update).
 */

@Data
public class ManagementUserRequest {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String role;
}
