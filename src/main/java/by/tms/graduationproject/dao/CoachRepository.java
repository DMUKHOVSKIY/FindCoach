package by.tms.graduationproject.dao;

import by.tms.graduationproject.entity.Coach;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoachRepository extends JpaRepository<Coach, Long> {
}
