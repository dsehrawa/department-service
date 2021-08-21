package com.dailycodebuffer.department.controller;

import com.dailycodebuffer.department.entity.Department;
import com.dailycodebuffer.department.service.DepartmentService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(value = DepartmentController.class)
public class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartmentService departmentService;

    @Test
    public void testFindDepartmentById() throws Exception {
        Department department = new Department();
        department.setDepartmentId(1l);
        department.setDepartmentName("IT");
        department.setDepartmentAddress("3rd Cross, First Street");
        department.setDepartmentCode("IT-006");
        Mockito.when(departmentService.findDepartmentById(Mockito.anyLong())).thenReturn(department);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/departments/1")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expectedStr = "{\"departmentId\":1,\"departmentName\":\"IT\",\"departmentAddress\":\"3rd Cross, First Street\",\"departmentCode\":\"IT-006\"}";
        JSONAssert.assertEquals(expectedStr, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void testSaveDepartment() throws Exception {
        Department department = new Department();
        department.setDepartmentId(1l);
        department.setDepartmentName("IT");
        department.setDepartmentAddress("3rd Cross, First Street");
        department.setDepartmentCode("IT-006");
        Mockito.when(departmentService.saveDepartment(Mockito.any(Department.class))).thenReturn(department);

        String expectedStr = "{\"departmentId\":1,\"departmentName\":\"IT\",\"departmentAddress\":\"3rd Cross, First Street\",\"departmentCode\":\"IT-006\"}";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/departments/").accept(MediaType.APPLICATION_JSON)
                .content(expectedStr).contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        JSONAssert.assertEquals(expectedStr, result.getResponse().getContentAsString(), false);
    }


}
