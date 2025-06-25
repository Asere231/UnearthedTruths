package com.example.unearthedtruths.user.repo;

import com.example.unearthedtruths.user.model.ManagementUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ManagementUserRepo extends JpaRepository<ManagementUser, Integer> {
    Optional<ManagementUser> findByUsername(String username);
}
