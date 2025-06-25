package com.example.unearthedtruths.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ManagementUserLoginRequest {
    private String username;
    private String password;
}
