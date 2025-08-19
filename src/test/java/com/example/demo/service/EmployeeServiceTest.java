package com.example.demo.service;

import java.util.Collections;
import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.dto.EmployeeDto;
import com.example.demo.dto.EmployeeResponseDto;
import com.example.demo.dto.EmployeeUpdateDto;
import com.example.demo.model.Employee;
import com.example.demo.model.enums.Department;
import com.example.demo.model.enums.Status;
import com.example.demo.repository.EmployeeRepository;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    private Employee employee;

    @BeforeEach
    void setUp() {
        employee = new Employee();
        employee.setId(1);
        employee.setName("test");
        employee.setDateOfJoining(new Date());
        employee.setStatus(Status.ACTIVE);
        employee.setDepartment(Department.IT);
    }

    @Test
    void whenCreateEmployee_shouldReturnEmployee() {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("test");
        employeeDto.setDateOfJoining(new Date());
        employeeDto.setStatus(Status.ACTIVE);
        employeeDto.setDepartment(Department.IT);
        employeeDto.setSalary(80000.0); // Add salary to fix null pointer
        employeeDto.setManagerId(1); // Add managerId to be safe

        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        Employee created = employeeService.createEmployee(employeeDto);

        assertThat(created.getName()).isEqualTo(employeeDto.getName());
    }

    @Test
    void whenGetAllEmployees_shouldReturnEmployeeList() {
        when(employeeRepository.findAll()).thenReturn(Collections.singletonList(employee));

        var employees = employeeService.getAllEmployees();

        assertThat(employees).hasSize(1);
        assertThat(employees.get(0).getName()).isEqualTo(employee.getName());
    }

    @Test
    void whenUpdateEmployee_shouldReturnUpdatedEmployee() {
        EmployeeUpdateDto employeeUpdateDto = new EmployeeUpdateDto();
        employeeUpdateDto.setName("updated");

        when(employeeRepository.findById(1)).thenReturn(Optional.of(employee));
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        EmployeeResponseDto updated = employeeService.updateEmployee(1, employeeUpdateDto);

        assertThat(updated.getName()).isEqualTo("updated");
    }

    @Test
    void whenSoftDeleteEmployee_shouldUpdateStatus() {
        when(employeeRepository.findById(1)).thenReturn(Optional.of(employee));
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        employeeService.softDeleteEmployee(1);

        assertThat(employee.getStatus()).isEqualTo(Status.NOT_ACTIVE);
    }
}
