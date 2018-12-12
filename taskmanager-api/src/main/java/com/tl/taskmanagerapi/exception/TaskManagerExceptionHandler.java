package com.tl.taskmanagerapi.exception;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tl.taskmanagerapi.controller.TaskManagerController;

@ControllerAdvice(assignableTypes = { TaskManagerController.class })
public class TaskManagerExceptionHandler {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(TaskManagerExceptionHandler.class);
	
	@ExceptionHandler(Throwable.class)
	public @ResponseBody RestError handleThrowableException(Exception ex,
			HttpServletResponse response) {
		response.setHeader("Content-Type", "application/json");
		LOGGER.error("Throwable exceptions",ex);
		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		RestError restError = returnRestError();
		return restError;
		
	}
	
	@ExceptionHandler(TaskException.class)
	public @ResponseBody RestError handleTaskExceptionException(Exception ex,
			HttpServletResponse response) {
		response.setHeader("Content-Type", "application/json");
		LOGGER.error("TaskException exceptions",ex);
		response.setStatus(((TaskException) ex).getReturnStatus());
		return ((TaskException) ex).transformException();
	}
	

	public RestError returnRestError() {
		RestError restError = new RestError();
		Exceptions exception = new Exceptions();
		exception.setType("E");
		exception.setCode("TK009");
		exception
				.setMessage("Mandatory fields missing/System error");
		exception
				.setDetail("Mandatory fields missing. Please contact admin.");
		Exceptions exceptions[] = new Exceptions[1];
		exceptions[0] = exception;
		restError.setExceptions(exceptions);
		return restError;
	}
}
