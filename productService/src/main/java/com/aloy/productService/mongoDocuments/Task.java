package com.aloy.productService.mongoDocuments;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

// To save an object into the Redis database, we define a model object that must implement serializable:

@Data
@Document(collection = "TaskManager")
public class Task implements Serializable {
    @Id
    private String id;
    private String task;
    private String description;
    private String dueDate;
    private String label;
    private String status;
}