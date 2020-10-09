package Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import be.abis.exercise.service.TrainingService;

@Controller
public class AppController {
	
	@Autowired
	TrainingService trainingService;
	
	@GetMapping("/")
	public String printCourse(Model model){
		String title = trainingService.getCourseService().findCourse(7900).getShortTitle();
		model.addAttribute("coursetitle", title);
		// return html page
		return "course"; 
	}

}
