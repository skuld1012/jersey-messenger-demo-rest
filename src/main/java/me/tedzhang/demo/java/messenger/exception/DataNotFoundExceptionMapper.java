package me.tedzhang.demo.java.messenger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import me.tedzhang.demo.java.messenger.model.ErrorMessage;

@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException> {

	@Override
	public Response toResponse(DataNotFoundException exception) {
		ErrorMessage errMessage = new ErrorMessage(exception.getMessage(), 404, "teddemo");
		return Response.status(Status.NOT_FOUND).entity(errMessage).build();
	}
}
