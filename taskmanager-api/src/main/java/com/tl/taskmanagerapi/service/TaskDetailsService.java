package com.tl.taskmanagerapi.service;
import java.util.List;

import com.tl.taskmanagerapi.exception.TaskException;
import com.tl.taskmanagerapi.model.TaskDetail;

public interface TaskDetailsService {
	public List<TaskDetail> getTaskDetails() throws TaskException;
	public void updateTaskDetail(TaskDetail taskDetail) throws TaskException;
}
