package com.tl.taskmanagerapi.dao;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.tl.taskmanagerapi.model.TaskDetail;

@Repository
public interface TaskDetailRepository extends MongoRepository<TaskDetail, String>{
	List<TaskDetail> findAll();
}

