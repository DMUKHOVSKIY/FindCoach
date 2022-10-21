package by.tms.graduationproject.controller;

import by.tms.graduationproject.entity.Role;
import by.tms.graduationproject.entity.User;
import by.tms.graduationproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class IndexController {
    private final UserService userService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("coachesInfo", userService.allActiveUsers());
        return "index";
    }

    @PostMapping
    public String filter(@RequestParam(required = false) String filter, Model model) {
        Iterable<User> users;
        if (filter == null || filter.isEmpty()) {
            users = userService.allActiveUsers();
        } else {
            users = userService.findByMainActivity(filter);
        }
        model.addAttribute("coachesInfo", users);
        model.addAttribute("filter", filter);
        return "index";
    }

    @GetMapping("/default")
    public String defaultPageAfterLogin() {
        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(Role.ADMIN)) {
            return "redirect:/admin/account";
        }
        return "redirect:/user/account";
    }
}