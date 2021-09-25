package com.mindex.challenge.service.impl;

import com.mindex.challenge.service.CompensationService;
//import com.mindex.challenge.dao.EmployeeRepository;
//import com.mindex.challenge.data.Employee;
import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.data.Compensation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompensationServiceImpl implements CompensationService{

    private static final Logger LOG = LoggerFactory.getLogger(CompensationServiceImpl.class);

    @Autowired
    private CompensationRepository compensationRepository;

    @Override
    public Compensation create(Compensation compensation){
        LOG.debug("creating compensation [{}]", compensation);
        
        compensationRepository.insert(compensation);

        return compensation;
    }

    @Override
    public Compensation read(String id){
        LOG.debug("Reading compensation for id [{}]", id);

        Compensation compensation = compensationRepository.findCompensationByEmployee_EmployeeId(id);

        if (compensation == null){
            throw new RuntimeException("No compensation for employee id: " + id);
        }

        return compensation;
    }
    
}
