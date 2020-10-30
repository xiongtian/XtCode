package com.example.cache.controller;

import com.example.cache.bean.Department;
import com.example.cache.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeptController {

    
    @Autowired
    DeptService deptService;
    
    @GetMapping("/dept/{id}")
    public Department getDept(@PathVariable("id") Integer id){
        return deptService.getDept(id);
    }
}
