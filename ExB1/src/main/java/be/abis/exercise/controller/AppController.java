package be.abis.exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import be.abis.exercise.model.Login;
import be.abis.exercise.model.Person;
import be.abis.exercise.service.TrainingService;

@Controller
public class AppController {
	
	
	@Autowired
	TrainingService trainingService;
	
	Person validLoginPerson;
	
	@GetMapping("/")
	public String login(Model model){
		Login login=new Login();
		model.addAttribute("loginScreen",login);
		
		return "loginScreen"; 
				
	}
	
	@PostMapping("/")
	public String welcome (Model model, Login login) {
		
		validLoginPerson = trainingService.findPerson(login.getEmail(), login.getPsw()) ;
		if (validLoginPerson != null) {	
			model.addAttribute("attrib1",validLoginPerson);
			return "welcome";
		} else { 
			return "redirect:/" ;
		}
	}
	
	@GetMapping("/logout")
	public String logout(){
				
		return "redirect:/"; 
				
	}

	@GetMapping("/dummy")
	public String dummy(){
				
		return "redirect:/dummyPage"; 
				
	}
}
