package com.prj2.prj2.user;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findByUserName(String userName);
    public Optional<User> findByEmail(String email);

    boolean existsByUserName(String userName);
    boolean existsByEmail(String email);
    boolean existsByUserNameAndIdNot(String userName, Long id);
    boolean existsByEmailAndIdNot(String email, Long id);

    public List<User> findByUserNameContainingIgnoreCase(String userName);
    public List<User> findByEmailContainingIgnoreCase(String email);
    public List<User> findByIsAdmin(Boolean isAdmin);
}
