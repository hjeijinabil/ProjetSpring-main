package com.example.project.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import com.example.project.configurations.JwtLocalStorage;
import com.example.project.dto.UserDto;
import com.example.project.model.Client;
import com.example.project.model.Project;
import com.example.project.model.User;
import com.example.project.model.WorkSample;
import com.example.project.repositories.ClientRepository;
import com.example.project.repositories.ProjectRepo;
import com.example.project.repositories.UserRepository;
import com.example.project.repositories.WorkSampleRepo;
import com.example.project.service.JwtService;
import com.example.project.service.UserService;
import org.antlr.v4.runtime.tree.pattern.ParseTreePattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



@Controller

public class UserController {
	@Autowired

	private UserDetailsService userDetailsService;
	@Autowired
	private UserRepository userRepository;
	
@Autowired
	private UserService userService;
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private JwtService jwtService;
	@Autowired
	private ProjectRepo projectRepo;
	@Autowired
	private WorkSampleRepo workSampleRepo;

	@GetMapping("/")
	public String getIndexPage(@ModelAttribute("index") UserDto userDto, Model model) {
//		String jwt = JwtLocalStorage.getJwt();
//		String userEmail = jwtService.extractUsername(jwt);
//		// Récupérer le client
//		Optional<Client> client = clientRepository.findByEmail(userEmail);
//
//
			List<Project> projets = projectRepo.findAll();

			// Ajouter les projets et le client à l'objet Model
			model.addAttribute("projets", projets);
//			model.addAttribute("client", client.get());

			List<WorkSample> worksamples = workSampleRepo.findAll();
			model.addAttribute("worksamples", worksamples);


			// Renvoyer le nom de la vue Thymeleaf
			return "index";

	}

	@GetMapping("/registration")
	public String getRegistrationPage(@ModelAttribute("user") UserDto userDto) {
		return "enregistre";
	}@PostMapping("/registration")
	public String saveUser(@ModelAttribute("user") UserDto userDto, Model model) {
		userService.save(userDto) ;
			model.addAttribute("message", "Registered Successfully!");
			return "enregistre";


		}



	@GetMapping("/login")
	public String login() {
		return "SignIn";
	}
	
	@GetMapping("user-page")
	public String userPage (Model model, Principal principal) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("user", userDetails);
		return "client";
	}
	@GetMapping("/donnee")
	public String GetFreelancer(Model model){
		model.addAttribute("donnee", userRepository.findAll());
		return "rrr";
	}

	@GetMapping("updateuser/{id}")
	public String updateUser(@PathVariable("id") Long id , Model model){
		Optional<User> temp = userRepository.findById(id);
		User user = temp.get();
		model.addAttribute("user", user);
		return "updateUser";
	}
//	@PostMapping("/admin/updateuser/{id}")
//	public String updateAdminUser(@PathVariable("id") Long id, @ModelAttribute("user") User updatedUser) {
//		Optional<User> optionalUser = userRepository.findById(id);
//
//		if (optionalUser.isPresent()) {
//			User user = optionalUser.get();
//
//			// Update fields other than password and role
//			user.getFullname(updatedUser.getFullname());
//			user.setAddresse(updatedUser.getAddresse());
//			user.setPhone(updatedUser.getPhone());
//			user.setCountry(updatedUser.getCountry());
//			user.setEmail(updatedUser.getEmail());
//			// Add more fields as needed
//
//			// Save the changes
//			userRepository.save(user);
//
//			return "redirect:/admin/userDetails/" + id; // Redirect to user details or another appropriate page
//		} else {
//			// Handle the case where the user with the given ID is not found
//			return "errorPage";
//		}
//	}

	@GetMapping("deleteuser/{id}")
	public String deleteUser(@PathVariable("id") Long id){
		userRepository.deleteById(id);

		return "redirect:/donnee";
	}
	@GetMapping("/saveuser")
	public String saveUser(Model model)
	{ User user = new User();
		model.addAttribute("user",user);
		return "saveuser";
	}
	@PostMapping("/addUser")
	public String addUser(@ModelAttribute("user") User user) {
		// Encrypt the user's password using BCrypt
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encryptedPassword = passwordEncoder.encode(user.getPassword());

		// Set the encrypted password back to the user object
		user.setPassword(encryptedPassword);

		// Save the user with the encrypted password
		userRepository.save(user);

		return "redirect:/donnee";
	}
//	@PostMapping("/addUser")
//	public String addUser(@ModelAttribute ("user") User user)
//	{
//		userRepository.save(user);
//		return "redirect:/donnee";
//	}
//	@GetMapping("/profile")
//	public String showProfile(Model model) {
//		// Récupérer l'utilisateur actuellement authentifié
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		CustomUserDetail userDetails = (CustomUserDetail) authentication.getPrincipal();
//
//		// Récupérer les informations de l'utilisateur
//
//		String phone = userDetails.getPhone();
//		String address = userDetails.getAddresse();
//		String country = userDetails.getCountry();
//
//		// Ajouter les informations au modèle pour les afficher dans la page de profil
//
//
//		model.addAttribute("phone", phone);
//		model.addAttribute("addresse", address);
//		model.addAttribute("country", country);
//
//		return "client";
//	}


}
