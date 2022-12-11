package com.shahian.springBootSecurity.servic;

import com.shahian.springBootSecurity.model.Role;
import com.shahian.springBootSecurity.repository.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleService {
    private final RoleRepository roleRepository;


    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role getRoleById(Long id) {
        return roleRepository.findById(id).orElseThrow(() -> new NullPointerException("this is null"));
    }

    public Role getRoleByName(String name) {
        return roleRepository.findByName(name).orElseThrow(() -> new NullPointerException("this is null"));
    }

    public List<Role> getAllRole() {
        return roleRepository.findAll();
    }

    @Transactional
    public void addRole(Role role) {
        Role role1 = Role.builder()
                .name(role.getName())
                .build();
        roleRepository.save(role1);
    }

    @Transactional
    public Role updateRole(Long id, Role role) {
        Role role1 = roleRepository.findById(id).orElseThrow(() -> new NullPointerException("this is null"));
        role1.setName(role1.getName());

        roleRepository.save(role1);
        return role1;
    }

    public void deleteRole(Long id) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new NullPointerException("this is null"));
        roleRepository.delete(role);
    }
}
