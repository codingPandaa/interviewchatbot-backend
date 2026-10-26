package com.example.demo.service;

import java.util.List;

import org.springframework.ai.document.Document;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

@Service
public class DocumentIngestionService {

	private final VectorStore vectorStore;

	public DocumentIngestionService(VectorStore vectorStore) {
		this.vectorStore = vectorStore;
	}

	public void ingestDocument(String filePath) {

		PagePdfDocumentReader pdfDocumentReader = new PagePdfDocumentReader(filePath);

		List<Document> documents = pdfDocumentReader.read();

		System.out.println("PDF pages loaded: " + documents.size());

		TokenTextSplitter splitter = TokenTextSplitter.builder().withChunkSize(800).withMinChunkSizeChars(200).build();
		
		List<Document> chunks = splitter.apply(documents);
		
		System.out.println("Chunks created: " + chunks.size());
		
		vectorStore.add(chunks);
		
		System.out.println("Documents successfully stored in PGVector.");
		
	}

}
