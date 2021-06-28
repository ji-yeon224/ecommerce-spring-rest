package kr.ac.hansung.cse.ecommercespringrest.repository;


import kr.ac.hansung.cse.ecommercespringrest.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByRolename(String rolename);
}