package kr.ac.hansung.cse.ecommercespringrest.repository;

import kr.ac.hansung.cse.ecommercespringrest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer>
{
    Optional<User> findByEmail(String email);
}