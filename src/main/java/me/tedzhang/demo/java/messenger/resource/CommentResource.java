package me.tedzhang.demo.java.messenger.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import me.tedzhang.demo.java.messenger.model.Comment;
import me.tedzhang.demo.java.messenger.service.CommentService;

@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CommentResource {

	private CommentService service = new CommentService();
	
	@GET
	public List<Comment> getAllComments(@PathParam("messageId") long messageId) {
		return service.getAllComments(messageId);
	}
	
	@POST
	public Comment addCommentToMessage(@PathParam("messageId") long messageId, Comment comment) {
		return service.addComment(messageId, comment);
	}
	
	@PUT
	@Path("/{commentId}")
	public Comment updateComment(@PathParam("messageId") long messageId, 
								 @PathParam("commentId") long commentId, 
								 Comment comment) {
		comment.setId(commentId);
		return service.updateComment(messageId, comment);
	}
	
	@DELETE
	@Path("/{commentId}")
	public Comment removeComment(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId) {
		return service.removeComment(messageId, commentId);
	}
	
	@GET
	@Path("/{commentId}")
	public Comment getCommentFromMessage(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId) {
		return service.getComment(messageId, commentId);
	}
}
