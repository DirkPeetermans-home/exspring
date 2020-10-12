package be.abis.exercise.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import be.abis.exercise.model.Course;
import be.abis.exercise.model.Login;
import be.abis.exercise.model.Person;
import be.abis.exercise.service.TrainingService;

@Controller
public class AppController {
	
	
	@Autowired
	TrainingService trainingService;
	
	Person validLoginPerson;
	List<Course> allCourses;
	
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

	@GetMapping("/searchforcourses")
	public String searchForCourses(){
				
		return "/searchforcourses"; 
				
	}
	
	@GetMapping("/personadministration")
	public String personAdministrations(){
				
		return "/personadministration"; 
				
	}
	
	@GetMapping("/back")
	public String back(Model model, Person validloginPerson){
		model.addAttribute("attrib1",validLoginPerson);	
		return "welcome"; 
				
	}
	
	@GetMapping("/showallcourses")
		public String showAllCourses(Model model){
		model.addAttribute("attrib1",allCourses);
		return "/showallcourses"; 
				
	}
	
	@GetMapping("/findcoursebyall")
	public String findcoursebyall(Model model){
		allCourses = trainingService.getCourseService().findAllCourses();
		model.addAttribute("attrib1",allCourses);
		return "redirect:/showallcourses"; 
	}
	
	@GetMapping("/findcoursebyid")
	public String findcoursebyid(Model model){
		Course c1=new Course();
		model.addAttribute("attrib1",c1);
		
		return "findcoursebyid"; 
				
	} 
	
	@PostMapping("/findcoursebyid")
	public String findCoursebyId (Course c1) {
		
		int id = Integer.parseInt(c1.getCourseId());
		Course courseById = trainingService.getCourseService().findCourse(id);
		
		if (courseById != null) {
			allCourses = new ArrayList<Course>();
			allCourses.add(courseById);
			return "redirect:/showallcourses";
		} else {
			return "redirect:/findcoursebyid";
		}
						
	}
	
	@GetMapping("/findcoursebyshorttitle")
	public String findcoursebyShortTitle(Model model){
		Course c2=new Course();
		model.addAttribute("attrib1",c2);
		
		return "findcoursebyshorttitle"; 
				
	} 
	
	@PostMapping("/findcoursebyshorttitle")
	public String findCoursebyShortTitle (Course c2) {
		
		String shortTitle = c2.getShortTitle();
		Course courseByShortTitle = trainingService.getCourseService().findCourse(shortTitle);
		System.out.println(courseByShortTitle);
		if (courseByShortTitle != null) {
			allCourses = new ArrayList<Course>();
			allCourses.add(courseByShortTitle);
			return "redirect:/showallcourses";
		} else {
			return "redirect:/findcoursebyshorttitle";
		}
			
	}
}
