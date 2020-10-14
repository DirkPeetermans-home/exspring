package be.abis.exercise.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	List<Person> allPersons;
	
	@GetMapping("/")
	public String login(Model model){
		Login login=new Login();
		model.addAttribute("loginScreen",login);
		
		return "loginScreen"; 
				
	}
	
	@PostMapping("/")
	public String welcome (Model model, @Valid @ModelAttribute("loginScreen") Login login, BindingResult bindingresult) {
		
		if (bindingresult.hasErrors()) {
			return "loginScreen";
		}
		
		validLoginPerson = trainingService.findPerson(login.getEmail(), login.getPsw()) ;
		if (validLoginPerson != null) {	
			model.addAttribute("attrib1",validLoginPerson);
			return "welcome";
		} else { 
			bindingresult.reject("global","Login failed");
			return "loginScreen" ;
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
	
	@GetMapping("/changepassword")
	public String changepassword(Model model){
		
		model.addAttribute("attrib1",validLoginPerson);
		
		return "changepassword"; 
				
	} 
	
	@PostMapping("/changepassword")
	public String changepassword(Model model, Person p1){
		try {
			trainingService.changePassword(validLoginPerson,p1.getPassword());
			return "personadministration";
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return "changepassword";
		}		
		 
					} 
	@GetMapping("/searchforperson")
	public String searchforperson(Model model){
		Person p2=new Person();
		model.addAttribute("attrib1",p2);
		
		return "searchforperson"; 
				
	} 
	
	@PostMapping("/searchforperson")
	public String searchforperson(Model model, Person person){
			Person personById= trainingService.findPerson(person.getPersonId());
			allPersons = new ArrayList<Person>();
			
				if (personById != null) {				
					allPersons.add(personById);
								
				} else {
					allPersons = trainingService.getAllPersons();				
				}
				
			model.addAttribute("attrib1",allPersons);	
			return "showallpersons";		
			}	
	
	@GetMapping("/removeperson")
	public String removeperson(Model model){
		Person p3=new Person();
		model.addAttribute("attrib1",p3);
		
		return "removeperson"; 
				
	} 
	
	@PostMapping("/removeperson")
	public String removeperson(Model model, Person person){
			System.out.println(person.getPersonId());
			trainingService.deletePerson(person.getPersonId());
			return "redirect:/personadministration";
			}	
	
	@GetMapping("/addperson")
	public String addperson(Model model){
		Person p4=new Person();
		model.addAttribute("person",p4);
		
		return "addperson"; 
				
	} 
	
	@PostMapping("/addperson")
	public String addperson( Model model, @Valid @ModelAttribute("person") Person person, BindingResult bindingresult){
		
			if (bindingresult.hasErrors()) {
				return "addperson";
			}
		
			try {
				trainingService.addPerson(person); 
				return "redirect:/personadministration";
			} catch (IOException e) {
				System.out.println(e.getMessage());
				return "addperson";
			}
			
			}	
}
