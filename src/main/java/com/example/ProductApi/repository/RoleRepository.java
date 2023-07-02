package com.example.ProductApi.repository;

import com.example.ProductApi.models.Role;
import com.example.ProductApi.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String name);

}
