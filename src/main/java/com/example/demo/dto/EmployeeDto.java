package com.example.demo.dto;

import java.util.Date;

import com.example.demo.model.enums.Department;
import com.example.demo.model.enums.Status;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EmployeeDto {

    @NotNull(message = "Name is mandatory")
    private String name;

    @NotNull(message = "Date of joining is mandatory")
    private Date dateOfJoining;

    @NotNull(message = "Status is mandatory")
    private Status status;

    @NotNull(message = "Department is mandatory")
    private Department department;

    private Double salary;

    private Integer managerId;
}
