package com.aloy.productService.mongoDocuments;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "TaskManager")
public class Task {
    @Id
    private String id;
    private String task;
    private String description;
    private String dueDate;
    private String label;
    private String status;
}