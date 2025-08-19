package com.example.demo.controller;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.dto.EmployeeDto;
import com.example.demo.dto.EmployeeResponseDto;
import com.example.demo.dto.EmployeeUpdateDto;
import com.example.demo.model.Employee;
import com.example.demo.model.enums.Department;
import com.example.demo.model.enums.Status;
import com.example.demo.service.EmployeeService;

@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    private Employee employee;
    private EmployeeResponseDto employeeResponseDto;
    private EmployeeDto employeeDto;
    private EmployeeUpdateDto employeeUpdateDto;

    @BeforeEach
    void setUp() {
        // Setup employee
        employee = new Employee();
        employee.setId(1);
        employee.setName("test");
        employee.setDateOfJoining(new Date());
        employee.setStatus(Status.ACTIVE);
        employee.setDepartment(Department.IT);

        // Setup employee response DTO
        employeeResponseDto = new EmployeeResponseDto();
        employeeResponseDto.setId(1);
        employeeResponseDto.setName("test");
        employeeResponseDto.setDateOfJoining(new Date());
        employeeResponseDto.setStatus(Status.ACTIVE);
        employeeResponseDto.setDepartment(Department.IT);

        // Setup employee create DTO
        employeeDto = new EmployeeDto();
        employeeDto.setName("test");
        employeeDto.setDateOfJoining(new Date());
        employeeDto.setStatus(Status.ACTIVE);
        employeeDto.setDepartment(Department.IT);

        // Setup employee update DTO
        employeeUpdateDto = new EmployeeUpdateDto();
        employeeUpdateDto.setName("updated");
    }

    @Test
    void whenCreateEmployee_shouldReturnCreated() {
        // Arrange
        when(employeeService.createEmployee(any(EmployeeDto.class))).thenReturn(employee);

        // Act
        ResponseEntity<Employee> response = employeeController.createEmployee(employeeDto);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualTo(employee);
        verify(employeeService, times(1)).createEmployee(employeeDto);
    }

    @Test
    void whenGetAllEmployees_shouldReturnOk() {
        // Arrange
        when(employeeService.getAllEmployees()).thenReturn(Collections.singletonList(employeeResponseDto));

        // Act
        ResponseEntity<List<EmployeeResponseDto>> response = employeeController.getAllEmployees();

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).hasSize(1);
        assertThat(response.getBody().get(0)).isEqualTo(employeeResponseDto);
        verify(employeeService, times(1)).getAllEmployees();
    }

    @Test
    void whenUpdateEmployee_shouldReturnOk() {
        // Arrange
        when(employeeService.updateEmployee(anyInt(), any(EmployeeUpdateDto.class))).thenReturn(employeeResponseDto);

        // Act
        ResponseEntity<EmployeeResponseDto> response = employeeController.updateEmployee(1, employeeUpdateDto);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(employeeResponseDto);
        verify(employeeService, times(1)).updateEmployee(1, employeeUpdateDto);
    }

    @Test
    void whenDeleteEmployee_shouldReturnNoContent() {
        // Arrange
        doNothing().when(employeeService).softDeleteEmployee(anyInt());

        // Act
        ResponseEntity<Void> response = employeeController.softDeleteEmployee(1);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        verify(employeeService, times(1)).softDeleteEmployee(1);
    }
}
