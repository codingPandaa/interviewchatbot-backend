package com.example.demo.service;

import java.util.List;

import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

@Service
public class VectorStoreTestService {

	private final VectorStore vectorStore;

	public VectorStoreTestService(VectorStore vectorStore) {

		this.vectorStore = vectorStore;

	}

	public String addDocuments() {

		Document document1 = new Document(
				"Dependency Injection is a design pattern where Spring provides the required dependencies to a class.");

		Document document2 = new Document(
				"Spring Boot is a framework used to create production-ready Java applications with minimal configuration.");

		Document document3 = new Document(
				"Spring Security provides authentication and authorization features for Spring applications.");

		vectorStore.add(List.of(document1, document2, document3));

		return "Documents added successfully";
	}
	
	public List<Document> search(String question){
		
		return vectorStore.similaritySearch(SearchRequest.builder().query(question).topK(1).build());
	}
}
