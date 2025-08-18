package com.example.demo.dto;

import com.example.demo.model.enums.Department;
import com.example.demo.model.enums.Status;

import lombok.Data;

@Data
public class EmployeeUpdateDto {
    private String name;
    private Status status;
    private Department department;
    private Double salary;
    private Integer managerId;
}
