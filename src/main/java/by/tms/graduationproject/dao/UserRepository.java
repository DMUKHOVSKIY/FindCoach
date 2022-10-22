package by.tms.graduationproject.dao;

import by.tms.graduationproject.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Page<User> findByMainActivity(String main_activity, Pageable pageable);

    @Query(value = "select * from users  join  user_roles on users.id=user_roles.user_id where user_roles.roles not in ('ADMIN') and users.active=true ",
            countQuery = "select count(*) from users  join  user_roles on users.id=user_roles.user_id where user_roles.roles not in ('ADMIN') and users.active=true ",
            nativeQuery = true)
    Page<User> findAllActiveCoaches(Pageable pageable);

    @Query(value = "select * from users where not  (username=:username)", nativeQuery = true)
    List<User> findAllExceptAdmin(String username);
}
