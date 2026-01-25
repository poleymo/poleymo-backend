package com.auth.repository;

import com.auth.entity.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAuthRepository extends JpaRepository<UserAuth, Long> {
    boolean existsByUserEmail(String userEmail);

    Optional<UserAuth> findByUserEmailAndPasswordAndActive(String userEmail, String password, Boolean active);

    Optional<UserAuth> findByUserEmail(String userEmail);

    Optional<UserAuth> findByAuthSeqAndActive(Long authSeq, boolean active);
}
