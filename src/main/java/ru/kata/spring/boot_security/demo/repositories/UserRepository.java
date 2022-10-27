package ru.kata.spring.boot_security.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    @Query("SELECT c FROM User AS c JOIN FETCH c.roles m WHERE c.username = :username")
    User findUserByUsernameWithRoles(String username);

    User findUserByEmail(String email);

}
