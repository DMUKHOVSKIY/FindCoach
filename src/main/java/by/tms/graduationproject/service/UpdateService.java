package by.tms.graduationproject.service;

import by.tms.graduationproject.dao.UserRepository;
import by.tms.graduationproject.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.GenerationType;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UpdateService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void updateDescription(String description) {
        User user = getUser();
        user.setDescription(description);
        userRepository.save(user);
    }

    public void updateNumber(String number) {
        User user = getUser();
        user.setNumber(number);
        userRepository.save(user);
    }

    public void updateName(String name) {
        User user = getUser();
        user.setName(name);
        userRepository.save(user);
    }

    public void updateMainActivity(String mainActivity) {
        User user = getUser();
        user.setMainActivity(mainActivity);
        userRepository.save(user);
    }

    public void updateUsername(String username) {
        User user = getUser();
        user.setUsername(username);
        userRepository.save(user);
    }
    public boolean updatePassword(String oldPassword, String newPassword) {
        if (passwordEncoder.matches(oldPassword, userRepository.findById(getUser().getId()).get().getPassword())) {
            User user = getUser();
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
            return true;
        } else {
            return false;
        }
    }

    private User getUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
