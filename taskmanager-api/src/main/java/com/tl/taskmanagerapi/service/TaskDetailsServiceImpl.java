package com.tl.taskmanagerapi.service;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.tl.taskmanagerapi.dao.TaskDetailRepository;
import com.tl.taskmanagerapi.exception.TaskException;
import com.tl.taskmanagerapi.model.TaskDetail;
import com.tl.taskmanagerapi.model.TaskSequence;


@Service
public class TaskDetailsServiceImpl implements TaskDetailsService {


	private TaskDetailRepository taskDetailRepository;

	private MongoOperations mongo;
	
	@Autowired
	public TaskDetailsServiceImpl (TaskDetailRepository taskDetailRepository,MongoOperations mongo){
		this.taskDetailRepository=taskDetailRepository;
		this.mongo=mongo;
	}

	@Override
	public List<TaskDetail> getTaskDetails() throws TaskException {
		List<TaskDetail> taskDetails = null;
		try {
			taskDetails = taskDetailRepository.findAll();
		} catch (Exception e) {
			throw new TaskException("100", "System error", 500);
		}
		return taskDetails;
	}

	@Override
	public void updateTaskDetail(TaskDetail taskDetail) throws TaskException {
		try {
			if (taskDetail.getTaskId() == 0) {
				taskDetail.setTaskId(getNextSequence("TaskSequence"));
			}
			if(StringUtils.isEmpty(taskDetail.getParentTaskDetail().getParentTaskName())){
				taskDetail.setParentTaskDetail(null);
			}
			taskDetailRepository.save(taskDetail);
		} catch (Exception e) {
			throw new TaskException("100", "System error", 500);
		}
		
	}
	
	private int getNextSequence(String seqName) {
		TaskSequence counter = mongo.findAndModify(
				query(where("_id").is(seqName)), new Update().inc("seq", 1),
				options().returnNew(true).upsert(true), TaskSequence.class);
		return counter.getSeq();
	}

	

	
}
