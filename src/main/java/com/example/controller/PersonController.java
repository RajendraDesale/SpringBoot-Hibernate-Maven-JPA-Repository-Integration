package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.JpaRepository.PersonJpaRepository;
import com.example.model.PersonModel;

@Controller
public class PersonController {

	@Autowired
	private PersonJpaRepository personRepository;

	@RequestMapping(value = "/persons", method = RequestMethod.GET)
	public String listPersons(ModelMap model) {
		model.addAttribute("person", new PersonModel());
		model.addAttribute("listPersons", personRepository.findAll());
		return "person";
	}

	// For add and update person both
	@RequestMapping(value = "/person/add", method = RequestMethod.POST)
	public String addPerson(@ModelAttribute("person") PersonModel p, ModelMap model) {
		String errorMessge = null;
		if (p != null) {
			personRepository.save(p);
		} else {
			errorMessge = "Please enter ther required field";
		}

		model.addAttribute("errorMessge", errorMessge);
		return "redirect:/persons";
	}

	@RequestMapping("/remove/{id}")
	public String removePerson(@PathVariable("id") Long id) {
		personRepository.deleteById(id);
		return "redirect:/persons";
	}

	@RequestMapping("/edit/{id}")
	public String editPerson(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("person", personRepository.existsById(id));
		model.addAttribute("listPersons", personRepository.findAll());
		return "person";
	}
}
