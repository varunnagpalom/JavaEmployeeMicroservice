package com.example.demo.repository;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.example.demo.model.Employee;
import com.example.demo.model.enums.Department;
import com.example.demo.model.enums.Status;

@DataJpaTest
public class EmployeeRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void whenFindByName_thenReturnEmployee() {
        // given
        Employee employee = new Employee();
        employee.setName("test");
        employee.setDateOfJoining(new Date());
        employee.setStatus(Status.ACTIVE);
        employee.setDepartment(Department.IT);
        entityManager.persist(employee);
        entityManager.flush();

        // when
        Employee found = employeeRepository.findById(employee.getId()).orElse(null);

        // then
        assertThat(found).isNotNull();
        assertThat(found.getName()).isEqualTo(employee.getName());
    }
}
