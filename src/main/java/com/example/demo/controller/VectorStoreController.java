package com.example.demo.controller;

import java.util.List;

import org.springframework.ai.document.Document;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.VectorStoreTestService;

@RestController
@RequestMapping("/api/vector")
public class VectorStoreController {

	
	private final VectorStoreTestService vectorStoreTestService;

	public VectorStoreController(VectorStoreTestService vectorStoreTestService) {
		this.vectorStoreTestService = vectorStoreTestService;
	}
	
	@GetMapping("/add")
	public String addDocuments() {
		return vectorStoreTestService.addDocuments();
	}
	
	@GetMapping("/search")
	public List<Document> search( @RequestParam String question){
		return vectorStoreTestService.search(question);
	}
	
}
