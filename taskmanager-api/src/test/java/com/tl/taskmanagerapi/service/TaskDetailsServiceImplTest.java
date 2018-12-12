package com.tl.taskmanagerapi.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.tl.taskmanagerapi.dao.TaskDetailRepository;
import com.tl.taskmanagerapi.exception.TaskException;
import com.tl.taskmanagerapi.model.ParentTaskDetail;
import com.tl.taskmanagerapi.model.TaskDetail;

@RunWith(SpringRunner.class)
public class TaskDetailsServiceImplTest {

	private TaskDetailsServiceImpl taskDetailsServiceImpl;
	private TaskDetailRepository taskDetailRepository;
	private MongoOperations mongo;
	@Before
	public void setup(){
		taskDetailRepository= Mockito.mock(TaskDetailRepository.class);
		mongo= Mockito.mock(MongoOperations.class);
		taskDetailsServiceImpl=new TaskDetailsServiceImpl(taskDetailRepository,mongo);
	}
	
	@Test
	public void testGetTaskDetails() throws JsonParseException, JsonMappingException, TaskException, IOException {
		when(taskDetailRepository.findAll()).thenReturn(getTaskDetails());
		List<TaskDetail> taskDetails=taskDetailsServiceImpl.getTaskDetails();
		Assert.assertNotNull(taskDetails);
		verify(taskDetailRepository,times(1)).findAll();
		verifyNoMoreInteractions(taskDetailRepository);
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
