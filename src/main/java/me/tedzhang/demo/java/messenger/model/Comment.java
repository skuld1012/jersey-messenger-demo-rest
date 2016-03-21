package me.tedzhang.demo.java.messenger.model;

import java.time.LocalDateTime;

public class Comment {

	private long id;
	
	private String message;
	
	private LocalDateTime date;
	
	private String author;

	public Comment(){}
	
	public Comment(long id, String message, String author) {
		this.id = id;
		this.message = message;
		this.date = LocalDateTime.now();
		this.author = author;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
}
