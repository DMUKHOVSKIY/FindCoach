package by.tms.graduationproject.service;

import by.tms.graduationproject.dao.CoachRepository;
import by.tms.graduationproject.entity.Coach;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CoachService {
    private final CoachRepository coachRepository;

    public Coach saveTrainer(Coach coach) {
        log.info("Saving new {}", coach);
        return coachRepository.save(coach);
    }

    public List<Coach> allCoaches() {
        return coachRepository.findAll();
    }

    public Coach findById(Long id) {
        return coachRepository.findById(id).orElse(null);
    }

}
