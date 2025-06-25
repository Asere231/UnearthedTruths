package com.example.unearthedtruths.user.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ManagementUserResponse {
    private int id;
    private String username;
    private String role;
}
