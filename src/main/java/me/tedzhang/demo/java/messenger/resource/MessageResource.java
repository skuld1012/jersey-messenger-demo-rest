package me.tedzhang.demo.java.messenger.resource;

import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import me.tedzhang.demo.java.messenger.model.Message;
import me.tedzhang.demo.java.messenger.resource.beans.MessageFilterBean;
import me.tedzhang.demo.java.messenger.service.MessageService;

@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {

	MessageService service = new MessageService();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	//public List<Message> getMessages(@BeanParam MessageFilterBean filterBean) {
	public List<Message> getMessages(@QueryParam("year") int year,
									 @QueryParam("start") int start,
									 @QueryParam("size") int size) {
		MessageFilterBean filterBean = new MessageFilterBean(year, start, size);
		if (filterBean.getYear() > 0)
			return service.getAllMessagesForYear(filterBean.getYear());
		if (filterBean.getStart() >= 0 && filterBean.getSize() > 0)
			return service.getAllMessagesPaginated(filterBean.getStart(), filterBean.getSize());
		return service.listAllMessages();
	}

	@POST
	public Response addMessage(@Context UriInfo uriInfo, Message message) {
		Message newMessage = service.addMessage(message);
		URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(newMessage.getId())).build();
		return Response.created(uri)
					   .entity(newMessage)
					   .build();
	}

	@PUT
	@Path("/{messageId}")
	public Message updateMessage(@PathParam("messageId") long messageId,
			Message message) {
		message.setId(messageId);
		return service.updateMessage(message);
	}

	@DELETE
	@Path("/{messageId}")
	public void deleteMessage(@PathParam("messageId") long messageId) {
		service.removeMessage(messageId);
	}

	@GET
	@Path("/{messageId}")
	public Message getMessage(@PathParam("messageId") long messageId, @Context UriInfo uriInfo) {
		Message message =  service.getMessage(messageId);
		message.addLink(getUrlForSelf(message, uriInfo), "self");
		message.addLink(getUrlForProfile(message, uriInfo), "profile");
		message.addLink(getUrlForComments(message, uriInfo), "comments");
		return service.getMessage(messageId);
	}
	
	private String getUrlForComments(Message message, UriInfo uriInfo) {
		String url = uriInfo.getBaseUriBuilder()
				   .path(MessageResource.class)
				   .path(MessageResource.class, "getCommentResource")
				   .path(CommentResource.class)
				   //.resolveTemplate("messageId", message.getId())
				   .build().toString();
		return url;
	}
	
	private String getUrlForProfile(Message message, UriInfo uriInfo) {
		String url = uriInfo.getBaseUriBuilder()
				   .path(ProfileResource.class)
				   .path(message.getAuthor())
				   .build().toString();
		return url;
	}

	private String getUrlForSelf(Message message, UriInfo uriInfo) {
		String url = uriInfo.getBaseUriBuilder()
				   .path(MessageResource.class)
				   .path(String.valueOf(message.getId()))
				   .build().toString();
		return url;
	}
	
	@Path("/{messageId}/comments")
	public CommentResource getCommentResource() {
		return new CommentResource();
	}
}
