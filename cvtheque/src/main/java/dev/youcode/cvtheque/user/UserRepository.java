package dev.youcode.cvtheque.user;

import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmailIgnoreCase(String email);
    User findByEmail(String email);

    User findByLastName(String lname);

}
