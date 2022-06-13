package com.example.graduation_work.jpa.repository;

import com.example.graduation_work.jpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {

    Optional<User> findByIdEquals(Integer id);
    Optional<User> findUserByLoginEquals(String login);
    boolean existsByLogin(String login);

    User getUserByLogin(String login);
}
