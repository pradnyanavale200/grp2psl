package com.psl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.psl.entities.Trainer;
import com.psl.service.TrainerService;

@RestController
@RequestMapping("/trainers")
public class TrainerController {
	@Autowired
	private TrainerService service;
	
	@GetMapping("/{id}")
	public Trainer getTrainer(@PathVariable int id) {
		return service.getTrainer(id);
	}
	
	@PostMapping("/register")
	public void addTrainer(@RequestBody Trainer t) {
		service.addTrainer(t);
	}
}