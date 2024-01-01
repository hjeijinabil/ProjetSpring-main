package com.example.project.controller;
import com.example.project.configurations.JwtLocalStorage;
import com.example.project.dto.UserDto;
import com.example.project.model.Client;
import com.example.project.model.Project;
import com.example.project.model.User;
import com.example.project.repositories.ClientRepository;
import com.example.project.repositories.ProjectRepo;
import com.example.project.repositories.UserRepository;
import com.example.project.service.JwtService;
import com.example.project.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.antlr.v4.runtime.tree.pattern.ParseTreePattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class BuyController {

    @Autowired
    private ProjectRepo projectRepo;

    @ResponseBody
    @RequestMapping(value = "/projettest", method = RequestMethod.GET)
    public ResponseEntity<Object> save() {

        List<Project> projets = projectRepo.findAllProjectsWithClient();
        return new ResponseEntity<>(projets , HttpStatus.OK);
    }

    @GetMapping("/listProjects")
    public String afficherListProject(Authentication authentication, Model model) {
//        User user = (User) authentication.getPrincipal();

        List<Project> projets = projectRepo.findAllProjectsWithClient();
        List<List<Project>> chunkedList = chunkList(projets, 3); // Chunk the list into sublists of size 3

//        System.out.println("aa "+projets.get(0).getClient());
//        List<Project> pp = projectRepo.findAllProjects();
//        System.out.println("aa "+pp.get(0).getClient());

//        System.out.println(projets.get(0).getClient().getId());
        // Ajouter les projets et le client à l'objet Model
        model.addAttribute("chunkedList", chunkedList);

        return "list-projects";
    }

    // Chunking method to break list into sublists of specified size
    private <T> List<List<T>> chunkList(List<T> list, int chunkSize) {
        List<List<T>> chunks = new ArrayList<>();
        for (int i = 0; i < list.size(); i += chunkSize) {
            chunks.add(list.subList(i, Math.min(i + chunkSize, list.size())));
        }
        return chunks;
    }
}
