package com.aloy.productService.mongoRepos;

import com.aloy.productService.mongoDocuments.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface TasksRepository extends MongoRepository<Task, String> {
}
