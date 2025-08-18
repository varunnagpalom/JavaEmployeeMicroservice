package com.example.demo.dto;

import com.example.demo.model.enums.Department;
import com.example.demo.model.enums.Status;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeResponseDto {
    private int id;
    private String name;
    private Date dateOfJoining;
    private Status status;
    private Department department;
    private Double salary;
    private Integer managerId;
}
