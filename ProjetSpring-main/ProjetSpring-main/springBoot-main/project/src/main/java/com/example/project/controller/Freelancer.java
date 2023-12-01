package com.example.project.controller;

import com.example.project.dto.UserDto;
import com.example.project.model.User;
import com.example.project.model.WorkSample;
import com.example.project.repositories.UserRepository;
import com.example.project.repositories.WorkSampleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class Freelancer {
@Autowired
private WorkSampleRepo workSampleRepo;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserDetailsService  userDetailsService;
    @GetMapping("/quiz")
    public String getQuizPage(@ModelAttribute("quiz")UserDto userDto) {
        return "quiz";

    }
    @GetMapping("/ajoutprojet")
    public String getProfil(Model model) {
        WorkSample work  = new WorkSample();
        model.addAttribute("work",work);
        return "ajoutprojet";

    }
    @PostMapping("/worksample")
public String saveWork(@ModelAttribute("work") WorkSample work)
    {
       workSampleRepo.save(work);
       return "redirect:/";
    }
    @GetMapping("/listefreelancer")
    public String getListe(@ModelAttribute("listefreelancer")UserDto userDto) {
        return "listefreelancer";

    }
    @GetMapping("/client")
    public String getClienPage(@ModelAttribute("client")UserDto userDto) {
        return "client";

    }


    @GetMapping("freelancer-page")
    public String adminPage (Model model, Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("user", userDetails);
        return "freelancer";
    }
    @GetMapping("/donneefreelancer")
    public String getFreelancer(Model model) {
        List<User> freelancers = userRepository.findByRole("freelancer");

        model.addAttribute("donneefreelancer", freelancers);
        return "donneefreelancer";
    }


}
