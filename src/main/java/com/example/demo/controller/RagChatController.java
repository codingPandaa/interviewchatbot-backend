package com.example.demo.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.RagChatService;

import reactor.core.publisher.Flux;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/rag")
public class RagChatController {

	private final RagChatService ragChatService;

	public RagChatController(RagChatService ragChatService) {
		this.ragChatService = ragChatService;
	}

	@GetMapping(value = "/chat", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<String> chat(@RequestParam String question) {

		return ragChatService.askQuestion(question);
	}

}
