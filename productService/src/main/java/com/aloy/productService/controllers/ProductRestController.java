package com.aloy.productService.controllers;

import com.aloy.productService.dto.Coupon;
import com.aloy.productService.model.Product;
import com.aloy.productService.mongoDocuments.Task;
import com.aloy.productService.mongoRepos.TasksRepository;
import com.aloy.productService.sqlRepos.ProductRepo;
import com.aloy.productService.restClients.CouponClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/productapi")
public class ProductRestController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    CouponClient couponClient;

    @Autowired
    private ProductRepo repo;

    @PostMapping("/products")
    @Retry(name = "product-api", fallbackMethod = "handleError")
//    @Retry(name = "product-api")
    public Product create(@RequestBody Product product) {
        Coupon coupon = couponClient.getCoupon(product.getCouponCode());
        product.setPrice(product.getPrice().subtract(coupon.getDiscount()));
        return repo.save(product);
    }

// Method signature should be same as the api causing the trouble
    public Product handleError(Product product, Exception exception) {
//        Other logic can be added here to handle the exception, Something like trying out other services
        System.out.println("Inside handle error method");
        product.setDescription(exception.getMessage());
        return product;
    }

    // MongoDB tests

    @Autowired
    private TasksRepository tasksRepository;

    @PostMapping(path = "/addTask")
    public Task addTask(@RequestBody Task task){
        Task newTask = this.tasksRepository.insert(task);
        return newTask;
    }


//    In the above mapping, getTaskById method will put a task into a cache named as ‘tasks’, identifies that task by the key as ‘id’.
//    This makes sure that cache is populated with tasks which are very popular and are often queried for.

    @Cacheable(value = "tasks", key = "#id")
    @GetMapping(path = "/getTask/{id}")
    public Optional<Task> getTaskById(@PathVariable String id){
        LOG.info("Getting task with ID {}.", id);
        Optional<Task> task = this.tasksRepository.findById(id);
        return task;
    }

    @GetMapping(path = "/getTask/all")
    public List<Task> getAllTasks(){
        List<Task> tasks = this.tasksRepository.findAll();
        return tasks;
    }

//    @CachePut(value = "tasks", key = "#user.id")
    @PutMapping(path = "/updateTask")
    public String updateTask(@RequestBody Task task){
        Task updatedTask = this.tasksRepository.save(task);
        return "update task " + updatedTask;
    }


//    If some data is to be deleted from actual Database, there won’t be a point to keep it in cache anymore.
//    We can clear cache data using @CacheEvict annotation

//    @CacheEvict(value = "users", allEntries=true)
//    @DeleteMapping("/{id}")
//    public void deleteUserByID(@PathVariable Long id) {
//        LOG.info("deleting person with id {}", id);
//        userRepository.delete(id);
//    }


}
