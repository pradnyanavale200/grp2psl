package com.psl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.psl.entities.Manager;
import com.psl.service.ManagerService;

@RestController
@RequestMapping("/managers")
public class ManagerController {
	@Autowired
	private ManagerService service;
	
	@GetMapping("/{id}")
	public Manager getManager(@PathVariable int id) {
		return service.getManager(id);
	}
	
	@PostMapping("/register")
	public void addManager(@RequestBody Manager m) {
		service.addManager(m);
	}
}
