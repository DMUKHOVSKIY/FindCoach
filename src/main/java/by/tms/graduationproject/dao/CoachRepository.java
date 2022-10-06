package by.tms.graduationproject.dao;

import by.tms.graduationproject.entity.Coach;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CoachRepository extends JpaRepository<Coach, Long> {
    Optional<Coach> findByUsername(String username);
}
