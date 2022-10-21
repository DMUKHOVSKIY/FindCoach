package by.tms.graduationproject.service;

import by.tms.graduationproject.dao.UserRepository;
import by.tms.graduationproject.entity.Role;
import by.tms.graduationproject.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean saveUser(User user) {
        if (userRepository.findByUsername(user.getUsername()).isEmpty()) {
            log.info("Saving new {}", user);
            user.getRoles().add(Role.COACH);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setActive(true);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public List<User> allActiveUsers() {
        return userRepository.findAllActiveUsers(true);//FIXME
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<User> findByMainActivity(String mainActivity) {
        return userRepository.findByMainActivity(mainActivity);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User update(User user) {
        return userRepository.save(user);
    }

    public void banUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            if (user.isActive()) {
                user.setActive(false);
            } else {
                user.setActive(true);
            }
        }
        userRepository.save(user);
    }
}
