package by.tms.graduationproject.controller;

import by.tms.graduationproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {
    private final UserService userService;

    @GetMapping("/account")
    public String admin(Model model) {
        model.addAttribute("allUsers", userService.findAll());

        return "adminAccount";
    }

    @PostMapping("/ban/{id}")
    public String ban(@PathVariable Long id){
        userService.banUser(id);
        return "redirect:/admin/account";
    }

}
