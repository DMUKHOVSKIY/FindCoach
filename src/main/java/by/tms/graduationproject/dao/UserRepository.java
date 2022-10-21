package by.tms.graduationproject.dao;

import by.tms.graduationproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    List<User> findByMainActivity(String mainActivity);

    @Query(value = "select * from users  join  user_roles on users.id=user_roles.user_id where user_roles.roles not in ('ADMIN') and users.active=true", nativeQuery = true)
    List<User> findAllActiveCoaches(boolean active);

    @Query(value = "select * from users where not  (username=:username)", nativeQuery = true)
    List<User> findAllExceptAdmin(String username);
}
