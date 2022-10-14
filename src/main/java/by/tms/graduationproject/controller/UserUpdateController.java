package by.tms.graduationproject.controller;

import by.tms.graduationproject.entity.User;
import by.tms.graduationproject.service.ImageService;
import by.tms.graduationproject.service.UpdateService;
import by.tms.graduationproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/update")
public class UserUpdateController {
    private final UpdateService updateService;
    private final ImageService imageService;

    @PostMapping("/updateDescription")
    public String updateDescription(String description) {
        updateService.updateDescription(description);
        return "redirect:/user/account";
    }

    @PostMapping("/updateNumber")
    public String updateNumber(String number) {
        updateService.updateNumber(number);
        return "redirect:/user/account";
    }

    @PostMapping("/updateName")
    public String updateName(String name) {
        updateService.updateName(name);
        return "redirect:/user/account";
    }

    @PostMapping("/updateMainActivity")
    public String updateMainActivity(String mainActivity) {
        updateService.updateMainActivity(mainActivity);
        return "redirect:/user/account";
    }

    @PostMapping("/updateUsername")
    public String updateUsername(String username) {
        updateService.updateUsername(username);
        return "redirect:/user/account";
    }

    @GetMapping("/updatePassword")
    public String updatePasswordForm() {
        return "updatePassword";
    }

    @PostMapping("/password")
    public String updatePassword(String oldPassword, String newPassword, Model model) {
        if (!updateService.updatePassword(oldPassword, newPassword)) {
            model.addAttribute("wrongPs", "WRONG PASSWORD!!!");
            return "updatePassword";
        }
        return "redirect:/user/account";
    }
}
