package by.tms.graduationproject.controller;

import by.tms.graduationproject.entity.Role;
import by.tms.graduationproject.entity.User;
import by.tms.graduationproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class IndexController {
    private final UserService userService;

    @GetMapping()
    public String index(Model model,
                        @RequestParam(required = false) String filter,
                        @RequestParam(value = "page", required = false, defaultValue = "0") Integer page) {

        Page<User> usersPage;
        if (filter == null || filter.isEmpty()) {
            usersPage = userService.allActiveCoaches(PageRequest.of(page, 2));
        } else {
            usersPage = userService.findByMainActivity(filter, PageRequest.of(page, 1));
            model.addAttribute("filter", filter);
        }

        model.addAttribute("usersPage", usersPage);

        int totalPages = usersPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(0, totalPages-1)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
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