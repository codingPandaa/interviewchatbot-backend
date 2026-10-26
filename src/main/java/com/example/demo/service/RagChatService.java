package com.example.demo.service;

import java.util.List;
import java.util.Map;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;

@Service
public class RagChatService {

	private final ChatClient chatClient;
	private final VectorStore vectorStore;
	private final PromptTemplate promptTemplate;

	public RagChatService(ChatClient chatClient, VectorStore vectorStore,
			@Value("classpath:/prompts/interview-prompt.st") Resource promptResource) {
		this.chatClient = chatClient;
		this.vectorStore = vectorStore;
		this.promptTemplate = PromptTemplate.builder().resource(promptResource).build();
	}

	public Flux<String> askQuestion(String question) {

		List<Document> documents = vectorStore
				.similaritySearch(SearchRequest.builder().query(question).topK(3).build());

		StringBuilder contextBuilder = new StringBuilder();

		for (Document document : documents) {
			contextBuilder.append(document.getText()).append("\n\n");
		}

		String context = contextBuilder.toString();

		String prompt = promptTemplate.render(Map.of("question", question, "context", context));

		return chatClient.prompt().user(prompt).stream().content();
	}

}
