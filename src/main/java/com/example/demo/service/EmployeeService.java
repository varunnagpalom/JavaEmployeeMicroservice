package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.EmployeeDto;
import com.example.demo.dto.EmployeeResponseDto;
import com.example.demo.dto.EmployeeUpdateDto;
import com.example.demo.model.Employee;
import com.example.demo.model.enums.Status;
import com.example.demo.repository.EmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee createEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        employee.setName(employeeDto.getName());
        employee.setDateOfJoining(employeeDto.getDateOfJoining());
        employee.setStatus(employeeDto.getStatus());
        employee.setDepartment(employeeDto.getDepartment());
        employee.setSalary(employeeDto.getSalary());
        if (employeeDto.getManagerId() != null) {
            employee.setManagerId(employeeDto.getManagerId());
        }
        return employeeRepository.save(employee);
    }

    public List<EmployeeResponseDto> getAllEmployees() {
        return employeeRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private EmployeeResponseDto convertToDto(Employee employee) {
        EmployeeResponseDto dto = new EmployeeResponseDto();
        dto.setId(employee.getId());
        dto.setName(employee.getName());
        dto.setDateOfJoining(employee.getDateOfJoining());
        dto.setStatus(employee.getStatus());
        dto.setDepartment(employee.getDepartment());
        if (employee.getSalary() != 0) {
            dto.setSalary(employee.getSalary());
        }
        if (employee.getManagerId() != 0) {
            dto.setManagerId(employee.getManagerId());
        }
        return dto;
    }

    public EmployeeResponseDto updateEmployee(int id, EmployeeUpdateDto employeeUpdateDto) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isEmpty()) {
            throw new RuntimeException("Employee not found with id: " + id);
        }
        Employee employee = optionalEmployee.get();
        if (employeeUpdateDto.getName() != null) {
            employee.setName(employeeUpdateDto.getName());
        }
        if (employeeUpdateDto.getStatus() != null) {
            employee.setStatus(employeeUpdateDto.getStatus());
        }
        if (employeeUpdateDto.getDepartment() != null) {
            employee.setDepartment(employeeUpdateDto.getDepartment());
        }
        if (employeeUpdateDto.getSalary() != null) {
            employee.setSalary(employeeUpdateDto.getSalary());
        }
        if (employeeUpdateDto.getManagerId() != null) {
            employee.setManagerId(employeeUpdateDto.getManagerId());
        }
        Employee updatedEmployee = employeeRepository.save(employee);
        return convertToDto(updatedEmployee);
    }

    public void softDeleteEmployee(int id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isEmpty()) {
            throw new RuntimeException("Employee not found with id: " + id);
        }
        Employee employee = optionalEmployee.get();
        employee.setStatus(Status.NOT_ACTIVE);
        employeeRepository.save(employee);
    }
}
