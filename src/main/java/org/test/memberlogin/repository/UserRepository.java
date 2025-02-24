package org.test.memberlogin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.test.memberlogin.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
