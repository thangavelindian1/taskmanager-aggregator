package com.tl.taskmanagerapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tl.taskmanagerapi.exception.TaskException;
import com.tl.taskmanagerapi.model.TaskDetail;
import com.tl.taskmanagerapi.service.TaskDetailsService;
@RestController
public class TaskManagerController {
	
	private TaskDetailsService taskDetailsService;
	
	@Autowired
	public TaskManagerController (TaskDetailsService taskDetailsService){
		this.taskDetailsService=taskDetailsService;
	}

	@RequestMapping(value = "/viewtasks", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<TaskDetail> getTaskDetails() throws TaskException{
		return taskDetailsService.getTaskDetails();
	}
	
	@RequestMapping(value = "/updatetasks", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateTask(@RequestBody TaskDetail taskDetail) throws TaskException{
		taskDetailsService.updateTaskDetail(taskDetail);
    }
}
