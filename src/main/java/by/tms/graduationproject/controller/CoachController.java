package by.tms.graduationproject.controller;


import by.tms.graduationproject.entity.Coach;
import by.tms.graduationproject.service.CoachService;
import by.tms.graduationproject.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;


@Controller
@RequiredArgsConstructor
@RequestMapping("/coach")
public class CoachController {
    private final CoachService coachService;

    private final ImageService imageService;

    @Value("${upload.path}")
    private String path;

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("coachesInfo", coachService.allCoaches());
        return "index";
    }

    @GetMapping("/reg")
    public String createNewCoach(Model model) {
        // model.addAttribute("Coach", new Coach());
        return "reg";
    }

    @PostMapping("/reg")
    public String save(@RequestParam("image") MultipartFile image, Coach coach) throws IOException {
        String resultImageName = imageService.upload(image, path);
        coach.setImageName(resultImageName);
        coachService.saveTrainer(coach);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String fillLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String login(String username, String password, Model model, HttpSession session) {
        Optional<Coach> byUsername = coachService.findByUsername(username);
        if (byUsername.isPresent()) {
            Coach coach = byUsername.get();
            if (coach.getPassword().equals(password)) {
                session.setAttribute("currentUser", coach);
                return "redirect:/";
            } else {
                model.addAttribute("message", "Wrong password");
                return "login";
            }
        } else {
            model.addAttribute("message", "User not found!");
            return "login";
        }
    }


    @GetMapping("/info/{id}")
    public String coachInfo(@PathVariable Long id, Model model) {
        Coach coach = coachService.findById(id);
        model.addAttribute("coach", coach);
        return "page";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
