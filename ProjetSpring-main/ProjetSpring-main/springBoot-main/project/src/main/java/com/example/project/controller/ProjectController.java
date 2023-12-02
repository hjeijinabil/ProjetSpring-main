package com.example.project.controller;

import com.example.project.model.Client;
import com.example.project.model.Project;
import com.example.project.model.User;
import com.example.project.model.WorkSample;
import com.example.project.repositories.ClientRepository;
import com.example.project.repositories.ProjectRepo;
import com.example.project.repositories.WorkSampleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class ProjectController {
    @Autowired
    private ProjectRepo projectRepo;

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private WorkSampleRepo workSampleRepo;

    @GetMapping("/listeProject/{clientId}")
    public String home(@PathVariable Long clientId, Model model) {

        Optional<Client> client = clientRepository.findById(clientId);

        if (client.isPresent()) {

            List<Project> projets = projectRepo.findAll();


            model.addAttribute("projets", projets);
            model.addAttribute("client", client.get());


            return "listeProject";
        } else {

            return "redirect:/erreur";
        }
    }
    @GetMapping("/listeprojectadmin")
    public String listProjects(Model model) {
        List<Project> projects = projectRepo.findAll();
        model.addAttribute("projects", projects);
        return "listeproject";
    }
    @GetMapping("deleteproject/{id}")
    public String deleteProject(@PathVariable("id") Long id){
        projectRepo.deleteById(id);

        return "redirect:/listeprojectadmin";
    }
    @GetMapping("/worksampleadmin")
    public String listWorkSimple(Model model) {
        List<WorkSample> workSamples = workSampleRepo.findAll();
        model.addAttribute("worksamples", workSamples);
        return "listeworksampleadmin";
    }
    @GetMapping("deleteworksample/{id}")
    public String deleteWorkSample(@PathVariable("id") Long id){
        workSampleRepo.deleteById(id);

        return "redirect:/worksampleadmin";
    }

}
