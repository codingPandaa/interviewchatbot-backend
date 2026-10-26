package com.example.demo.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.service.DocumentIngestionService;

@Component
public class DocumentIngestionRunner implements CommandLineRunner{
	
	private final DocumentIngestionService ingestionService;
	
	public DocumentIngestionRunner(DocumentIngestionService ingestionService) {
		this.ingestionService = ingestionService;
	}


	@Override
	public void run(String... args) throws Exception {
		
		String filePath =
                "documents/DailyRevise.pdf";
		
		ingestionService.ingestDocument(filePath);
		
	}

}
