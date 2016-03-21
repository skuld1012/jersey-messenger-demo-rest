package me.tedzhang.demo.java.messenger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import me.tedzhang.demo.java.messenger.model.ErrorMessage;

public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

	@Override
	public Response toResponse(Throwable exception) {
		System.out.println("generic exception mapper");
		ErrorMessage errMessage = new ErrorMessage(exception.getMessage(), 500, "teddemo");
		return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errMessage).build();
	}

}
