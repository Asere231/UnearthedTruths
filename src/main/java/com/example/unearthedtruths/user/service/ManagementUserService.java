package com.example.unearthedtruths.user.service;

import com.example.unearthedtruths.user.dto.ManagementUserMapper;
import com.example.unearthedtruths.user.dto.ManagementUserRequest;
import com.example.unearthedtruths.user.dto.ManagementUserResponse;
import com.example.unearthedtruths.user.repo.ManagementUserRepo;
import com.example.unearthedtruths.user.model.ManagementUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ManagementUserService {

    // Repository dependency, injected via constructor (recommended best practice)
    private final ManagementUserRepo managementUserRepo;

    private final ManagementUserMapper mapper;

    /**
     * Adds a new management user to the system.
     *
     * @param request the user entity to save
     * @return the persisted ManagementUser entity
     */
    @Transactional
    public ResponseEntity<ManagementUserResponse> addManagementUser(ManagementUserRequest request) {

        ManagementUser managementUser = mapper.toEntity(request);
        ManagementUser saved = managementUserRepo.save(managementUser);

        log.info("Adding new management user - Username: {}, First Name: {}, Last Name: {}",
                request.getUsername(), request.getFirstName(), request.getLastName());


        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(saved));
    }

    public String getRole(String username) {
        Optional<ManagementUser> user = managementUserRepo.findByUsername(username);

        if (user.isPresent()) {
            return user.get().getRole();
        } else {
            throw new RuntimeException("Couldn't get role for username: " + username);
        }
    }

    public ResponseEntity<List<ManagementUser>> getAllAdmins() {
        return ResponseEntity.ok(managementUserRepo.findAll());
    }
}
