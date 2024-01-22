package com.ced.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ced.app.service.MarqueService;

import java.util.List;
import com.ced.app.model.Marque;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class MarqueController {
    @Autowired
    private MarqueService marqueservice;

    @GetMapping("/marques")
	public List<Marque> getAllMarques(){
		return marqueservice.getAllMarques();
	}
}
