package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.ReportingStructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//REST api implementation
@Service
public class ReportingStructureServiceImpl implements ReportingStructureService{
    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    public ReportingStructure count(String id){
        LOG.debug("Creating ReportingStructure for employee with id [{}]", id);

        Employee employee = employeeRepository.findByEmployeeId(id);

        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

        //start from -1 because counting starts at employee, not below
        int numberOfReports = -1;
        
        //tree traversal with stack
        java.util.Stack<Employee> stack = new java.util.Stack<Employee>();
        stack.push(employee);
        while(!stack.empty()){
            //employees in directReport list only have ID
            Employee curremployee= employeeRepository.findByEmployeeId(stack.pop().getEmployeeId());
            numberOfReports++;
            java.util.List<Employee> d  =  curremployee.getDirectReports();
            if (d != null) {
                d.forEach((e) -> stack.push(e));
            }
        }

        ReportingStructure report = new ReportingStructure();
        report.setEmployee(employee);
        report.setNumberofReports(numberOfReports);
        return report;

    }

}
