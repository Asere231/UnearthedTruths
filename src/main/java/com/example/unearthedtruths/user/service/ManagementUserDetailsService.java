package com.example.unearthedtruths.user.service;

import com.example.unearthedtruths.user.model.ManagementUser;
import com.example.unearthedtruths.user.model.ManagementUserPrincipal;
import com.example.unearthedtruths.user.repo.ManagementUserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ManagementUserDetailsService implements UserDetailsService {

    private final ManagementUserRepo managementUserRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<ManagementUser> user = managementUserRepo.findByUsername(username);
        ManagementUser managementUser;

        if (user.isPresent()) {
            managementUser = user.get();
        } else {
            System.out.println("User 404");
            throw new UsernameNotFoundException("User 404");
        }

        return new ManagementUserPrincipal(managementUser);
    }
}
