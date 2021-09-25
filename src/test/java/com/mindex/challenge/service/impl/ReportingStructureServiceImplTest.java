package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import com.mindex.challenge.service.ReportingStructureService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

//tests that ReportingStructure returns the correct numberofReports
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportingStructureServiceImplTest {

    private String employeeUrl;
    private String employeeIdUrl;
    private String reportingStrucURL;

    @Autowired
    private EmployeeService employeeService;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Before
    public void setup() {
        employeeUrl = "http://localhost:" + port + "/employee";
        employeeIdUrl = "http://localhost:" + port + "/employee/{id}";
        reportingStrucURL = "http://localhost:" + port + "/ReportingStructure/{id}";
    }

    @Test
    public void testReportingStructure(){
        //get John Lennon employee
        String employeeid = "16a596ae-edd3-4847-99fe-c4518e82c86f";
        Employee readEmployee = restTemplate.getForEntity(employeeIdUrl, Employee.class, employeeid).getBody();
        ReportingStructure report = restTemplate.getForEntity(reportingStrucURL, ReportingStructure.class, employeeid).getBody();
        assertEmployeeEquivalence(readEmployee, report.getEmployee());
        assertEquals(4, report.getNumberofReports());

        //get Ringo Star employee
        employeeid = "03aa1462-ffa9-4978-901b-7c001562cf6f";
        readEmployee = restTemplate.getForEntity(employeeIdUrl, Employee.class, employeeid).getBody();
        report = restTemplate.getForEntity(reportingStrucURL, ReportingStructure.class, employeeid).getBody();
        assertEmployeeEquivalence(readEmployee, report.getEmployee());
        assertEquals(2, report.getNumberofReports());

        //get Pete Best employee
        employeeid = "62c1084e-6e34-4630-93fd-9153afb65309";
        readEmployee = restTemplate.getForEntity(employeeIdUrl, Employee.class, employeeid).getBody();
        report = restTemplate.getForEntity(reportingStrucURL, ReportingStructure.class, employeeid).getBody();
        assertEmployeeEquivalence(readEmployee, report.getEmployee());
        assertEquals(0, report.getNumberofReports());
    }

    private static void assertEmployeeEquivalence(Employee expected, Employee actual) {
        assertEquals(expected.getFirstName(), actual.getFirstName());
        assertEquals(expected.getLastName(), actual.getLastName());
        assertEquals(expected.getDepartment(), actual.getDepartment());
        assertEquals(expected.getPosition(), actual.getPosition());
    }
}
