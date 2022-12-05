package com.shahian.springBootSecurity.repository;

import com.shahian.springBootSecurity.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    List<Role> findAll();

    Optional<Role> findById(Long id);

    Optional<Role> findByName(String name);
     Role  getByName(String name);


}
