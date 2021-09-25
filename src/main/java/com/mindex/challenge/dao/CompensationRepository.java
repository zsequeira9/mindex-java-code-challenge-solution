package com.mindex.challenge.dao;

import com.mindex.challenge.data.Compensation;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;

//create repository for Compensation
@Repository
public interface CompensationRepository extends MongoRepository<Compensation, String> {
    Compensation findCompensationByEmployee_EmployeeId(String employeeId);
}
