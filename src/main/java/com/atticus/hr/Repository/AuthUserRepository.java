package com.atticus.hr.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.atticus.hr.domain.AuthUser;

@Repository
public interface AuthUserRepository extends JpaRepository<AuthUser, Integer> {
    public AuthUser findByEmail(String email);
    public AuthUser findUserById(Integer id);
}
