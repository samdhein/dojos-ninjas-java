package com.samhein.dojosninjas.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.samhein.dojosninjas.models.Dojo;
import com.samhein.dojosninjas.models.Ninja;
import com.samhein.dojosninjas.services.MainService;

@Controller
public class HomeController {

	@Autowired MainService mainService;
	
	//create a dojo (render the form, process the form)
	@GetMapping("/dojos/new")
	public String createDojoForm(@ModelAttribute("dojo")Dojo dojo) {
		return "createDojo.jsp";
	}
	@PostMapping("/dojos/new")
	public String createDojoProcess(@Valid @ModelAttribute("dojo")Dojo dojo, BindingResult result) {
		if(result.hasErrors()) {
			return "createDojo.jsp";
		} else {
			mainService.saveDojo(dojo);
			return "redirect:/dojos/new";
		}
	}
	// create a ninja (render and process)
	@GetMapping("/ninjas/new")
	public String createNinjaForm(@ModelAttribute("ninja")Ninja ninja, Model model) {
		model.addAttribute("dojos", mainService.allDojos());
		return "createNinja.jsp";
	}
	@PostMapping("/ninjas/new")
	public String createNinjaProcess(@Valid @ModelAttribute("ninja")Ninja ninja, BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("dojos", mainService.allDojos());
			return "createNinja.jsp";
		} else {
			mainService.saveNinja(ninja);
			return "redirect:/ninjas/new";
		}
	}
	// show ninjas associated with a particular dojo
	@GetMapping("/dojos/{dojoId}")
	public String showDojo(@PathVariable("dojoId")Long dojoId, Model model) {
		Dojo dojo = mainService.oneDojo(dojoId);
		model.addAttribute("dojo", dojo);
		return "oneDojo.jsp";
	}
	
}
