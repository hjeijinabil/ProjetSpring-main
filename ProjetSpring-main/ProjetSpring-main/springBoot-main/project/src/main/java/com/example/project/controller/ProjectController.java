package com.example.project.controller;

import com.example.project.model.Client;
import com.example.project.model.Project;
import com.example.project.model.User;
import com.example.project.repositories.ClientRepository;
import com.example.project.repositories.ProjectRepo;
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

    @GetMapping("/listeProject/{clientId}")
    public String home(@PathVariable Long clientId, Model model) {
        // Récupérer le client
        Optional<Client> client = clientRepository.findById(clientId);

        if (client.isPresent()) {
            // Récupérer les projets associés au client
            List<Project> projets = projectRepo.findAll();

            // Ajouter les projets et le client à l'objet Model
            model.addAttribute("projets", projets);
            model.addAttribute("client", client.get());


            // Renvoyer le nom de la vue Thymeleaf
            return "client";
        } else {
            // Gérer le cas où le client n'est pas trouvé
            return "redirect:/erreur";
        }
    }

    @GetMapping("deleteprojett/{id}")
    public String deleteProjet(@PathVariable("id") Long id){
        projectRepo.deleteById(id);

        return "redirect:/user-page";

    }
    @GetMapping("updateProjett/{id}")
    public String updateProjet(@PathVariable("id") Long id , Model model){
        Optional<Project> temp = projectRepo.findById(id);
        Project projet = temp.get();
        model.addAttribute("projet", projet);
        return "updateprojet";
    }
}
