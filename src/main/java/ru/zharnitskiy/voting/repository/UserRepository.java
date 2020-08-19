package ru.zharnitskiy.voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.zharnitskiy.voting.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}
