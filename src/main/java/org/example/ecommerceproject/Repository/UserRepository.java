package org.example.ecommerceproject.Repository;

import jdk.jfr.Enabled;
import org.example.ecommerceproject.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
