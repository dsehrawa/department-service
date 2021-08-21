package com.dailycodebuffer.department.repository;

import com.dailycodebuffer.department.entity.Department;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class DepartmentRepositoryTest {
    @Autowired
    public DepartmentRepository departmentRepository;

    @Test
    public void shouldPerformCRUDOperations(){
        Department department = new Department();
        department.setDepartmentName("IT");
        department.setDepartmentAddress("3rd Cross, First Street");
        department.setDepartmentCode("IT-006");

        departmentRepository.save(department);
        Assertions.assertTrue(departmentRepository.findAll().size() == 1);
        Assertions.assertNotNull(departmentRepository.findByDepartmentId(departmentRepository.findAll().get(0).getDepartmentId()));
    }


}
