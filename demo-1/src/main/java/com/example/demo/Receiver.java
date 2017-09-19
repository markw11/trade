package com.example.demo;

import java.io.IOException;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class Receiver {

	private ObjectMapper m = new ObjectMapper();

	@KafkaListener(topics = "test1")
	public void processMessage(String content) {
		System.out.println(content);
		try {
			User u = m.readValue(content, User.class);
			System.out.println(u.getName());
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
