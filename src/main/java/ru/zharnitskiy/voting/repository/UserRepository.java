package ru.zharnitskiy.voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.zharnitskiy.voting.model.User;

@Repository
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, Integer> {
    User getByEmail(String email);
}
