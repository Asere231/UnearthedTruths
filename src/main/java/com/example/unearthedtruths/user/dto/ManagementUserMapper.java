package com.example.unearthedtruths.user.dto;

import com.example.unearthedtruths.user.model.ManagementUser;
import org.springframework.stereotype.Component;

@Component
public class ManagementUserMapper {

    public ManagementUser toEntity(ManagementUserRequest request) {
        return ManagementUser.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .username(request.getUsername())
                .password(request.getPassword())
                .role(request.getRole())
                .build();
    }

    public  ManagementUserResponse toResponse(ManagementUser entity) {
        return ManagementUserResponse.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .role(entity.getRole())
                .build();
    }

}
