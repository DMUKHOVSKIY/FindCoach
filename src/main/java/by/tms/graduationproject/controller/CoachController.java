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


import java.io.IOException;


@Controller
@RequiredArgsConstructor
@RequestMapping("/coach")
public class CoachController {
    private final CoachService coachService;

    private final ImageService imageService;

    @Value("${upload.path}")
    private String path;

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

    @GetMapping("/coaches_info")
    public String Coaches(Model model) {
        model.addAttribute("coachesInfo", coachService.allCoaches());
        return "coaches";
    }

    @GetMapping("/info/{id}")
    public String coachInfo(@PathVariable Long id, Model model) {
        Coach coach = coachService.findById(id);
        model.addAttribute("coach", coach);
        model.addAttribute("image", coach.getImageName());
        return "page";
    }
}
