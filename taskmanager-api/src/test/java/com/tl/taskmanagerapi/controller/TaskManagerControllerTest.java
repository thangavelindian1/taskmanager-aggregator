package com.tl.taskmanagerapi.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.tl.taskmanagerapi.exception.TaskException;
import com.tl.taskmanagerapi.model.ParentTaskDetail;
import com.tl.taskmanagerapi.model.TaskDetail;
import com.tl.taskmanagerapi.service.TaskDetailsService;

@RunWith(SpringRunner.class)
public class TaskManagerControllerTest {
	
	private TaskManagerController taskManagerController;
	private TaskDetailsService taskDetailsService;
	@Before
	public void setup(){
		taskDetailsService= Mockito.mock(TaskDetailsService.class);
		taskManagerController=new TaskManagerController(taskDetailsService);
	}

	@Test
	public void testGetTaskDetails() throws JsonParseException, JsonMappingException, TaskException, IOException {
		when(taskDetailsService.getTaskDetails()).thenReturn(getTaskDetails());
		List<TaskDetail> taskDetails=taskManagerController.getTaskDetails();
		Assert.assertNotNull(taskDetails);
		verify(taskDetailsService,times(1)).getTaskDetails();
		verifyNoMoreInteractions(taskDetailsService);
	}
	
	@Test(expected = Exception.class)
	public void testGetTaskDetailsException() throws TaskException{
		when(taskDetailsService.getTaskDetails()).thenThrow(Exception.class);
		List<TaskDetail> taskDetails=taskManagerController.getTaskDetails();
		Assert.assertNull(taskDetails);
		verify(taskDetailsService,times(1)).getTaskDetails();
		verifyNoMoreInteractions(taskDetailsService);
	}
	
	@Test
	public void testUpdateTaskDetails() throws TaskException{
		TaskDetail taskDetail=taskDetails();
		doNothing().when(taskDetailsService).updateTaskDetail(taskDetail);
		taskManagerController.updateTask(taskDetail);
		verify(taskDetailsService,times(1)).updateTaskDetail(taskDetail);
		verifyNoMoreInteractions(taskDetailsService);
	}
	
	@Test(expected = Exception.class)
	public void testUpdateTaskDetailsExceptiond() throws TaskException{
		TaskDetail taskDetail=taskDetails();
		doThrow(Exception.class).when(taskDetailsService).updateTaskDetail(taskDetail);
		taskManagerController.updateTask(taskDetail);
		verify(taskDetailsService,times(1)).updateTaskDetail(taskDetail);
		verifyNoMoreInteractions(taskDetailsService);
	}
	
	
	
	
	private List<TaskDetail> getTaskDetails() throws JsonParseException, JsonMappingException, IOException{
		List<TaskDetail> taskDetailList=new ArrayList<>();
		TaskDetail taskDetail=new TaskDetail();
		taskDetail.setEndDate("10/11/2017");
		ParentTaskDetail parentTaskDetail=new ParentTaskDetail();
		parentTaskDetail.setParentTaskName("parent name1");
		parentTaskDetail.setParentId(4);
		taskDetail.setParentTaskDetail(parentTaskDetail);
		taskDetail.setPriority("14");
		taskDetail.setStartDate("10/11/2017");
		taskDetail.setTaskId(1);
		taskDetail.setTaskName("Task 1");
		taskDetailList.add(taskDetail);
		return taskDetailList;
	}
	
	private TaskDetail taskDetails(){
		TaskDetail taskDetail=new TaskDetail();
		taskDetail.setEndDate("10/11/2017");
		ParentTaskDetail parentTaskDetail=new ParentTaskDetail();
		parentTaskDetail.setParentTaskName("parent name1");
		parentTaskDetail.setParentId(4);
		taskDetail.setParentTaskDetail(parentTaskDetail);
		taskDetail.setPriority("14");
		taskDetail.setStartDate("10/11/2017");
		taskDetail.setTaskId(1);
		taskDetail.setTaskName("Task 1");
		return taskDetail;
	}
	
	 
	


}
