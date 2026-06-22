package com.github.joaovitorqs.rescue_gatitos_to_work.repository;

import com.github.joaovitorqs.rescue_gatitos_to_work.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
