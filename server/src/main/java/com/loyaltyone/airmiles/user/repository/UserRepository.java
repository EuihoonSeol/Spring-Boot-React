package com.loyaltyone.airmiles.user.repository;

import com.loyaltyone.airmiles.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
