package me.tedzhang.demo.java.messenger.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import me.tedzhang.demo.java.messenger.database.DBHandler;
import me.tedzhang.demo.java.messenger.model.Comment;
import me.tedzhang.demo.java.messenger.model.ErrorMessage;
import me.tedzhang.demo.java.messenger.model.Message;

public class CommentService {

	private Map<Long, Message> messages = DBHandler.getMessages();
	
	public List<Comment> getAllComments(long messageId) {
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		return new LinkedList<>(comments.values());
	}
	
	public Comment getComment(long messageId, long commentId) {
		ErrorMessage errMessage = new ErrorMessage("not found", 404, "teddemo");
		Response response = Response.status(Status.NOT_FOUND).entity(errMessage).build();
		Message message = messages.get(messageId);
		if(message == null) {
			throw new WebApplicationException(response);
		}
		Map<Long, Comment> comments = message.getComments();
		Comment comment = comments.get(commentId);
		if(comment == null) {
			throw new WebApplicationException(response);
		}
		return comments.get(commentId);
	}
	
	public Comment addComment(long messageId, Comment comment) {
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		comment.setId(comments.size() + 1);
		comments.put(comment.getId(), comment);
		return comment;
	}
	
	public Comment updateComment(long messageId, Comment comment) {
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		if(comment.getId() <= 0) {
			return null;
		}
		comments.put(comment.getId(), comment);
		return comment;
	}
	
	public Comment removeComment(long messageId, long commentId) {
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		return comments.remove(commentId);
	}
}
