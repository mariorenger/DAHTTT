package com.httt.backend.service;

import com.httt.backend.ultilities.ERole;
import com.httt.backend.entity.Role;

import java.util.Optional;

public interface RoleService {
    Optional<Role> findByName(ERole name);
}
