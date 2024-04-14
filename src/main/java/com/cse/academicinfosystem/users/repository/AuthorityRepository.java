package com.cse.academicinfosystem.users.repository;

import com.cse.academicinfosystem.users.domain.Authority;
import com.cse.academicinfosystem.users.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    Authority findByUser(User user);
}
