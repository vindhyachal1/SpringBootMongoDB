package com.mongodb.spring.dao;

import com.mongodb.spring.model.EmployeeModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDAO extends MongoRepository<EmployeeModel, Long> {

}
