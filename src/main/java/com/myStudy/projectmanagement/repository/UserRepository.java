package com.myStudy.projectmanagement.repository;

import com.myStudy.projectmanagement.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.cdi.JpaRepositoryExtension;
import org.springframework.security.authentication.jaas.JaasPasswordCallbackHandler;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);
}
