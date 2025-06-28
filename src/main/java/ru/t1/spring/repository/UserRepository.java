package ru.t1.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.t1.spring.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
