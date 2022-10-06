package by.tms.graduationproject.controller;

import by.tms.graduationproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class IndexController {
    private final UserService userService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("coachesInfo", userService.allCoaches());
        return "index";
    }
}