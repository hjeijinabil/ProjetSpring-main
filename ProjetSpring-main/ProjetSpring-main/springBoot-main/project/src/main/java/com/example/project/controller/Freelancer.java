package com.example.project.controller;

import com.example.project.configurations.JwtLocalStorage;
import com.example.project.dto.UserDto;
import com.example.project.model.*;
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
    @GetMapping("/ajoutwork")
    public String getProfil(Model model) {
        WorkSample work  = new WorkSample();
        model.addAttribute("work",work);
        return "addWorksampleFreelancer";

    }
    @PostMapping("/addworksample")
public String saveWork(@ModelAttribute("work") WorkSample work, Principal principal)
    {


        User user = userRepository.findByEmail(principal.getName());
        freelancer fr = new freelancer(user.getEmail(),user.getRole(), user.getFullname(),user.getAddresse(),user.getPhone(),user.getCountry(),user.getId());
        work.setFreelancer(fr);
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

    @GetMapping("/updateprofilfreelancer")
    public String afficherProfil(Model model, Principal principal) {
        // Obtenez l'utilisateur connecté
        User utilisateur = userRepository.findByEmail(principal.getName());

        // Ajoutez l'utilisateur au modèle
        model.addAttribute("utilisateur", utilisateur);

        return "updateprofilfreelancer";
    }
    @GetMapping("/donneefreelancer")
    public String getFreelancer(Model model) {
        List<User> freelancers = userRepository.findByRole("freelancer");

        model.addAttribute("donneefreelancer", freelancers);
        return "donneefreelancer";
    }


}
