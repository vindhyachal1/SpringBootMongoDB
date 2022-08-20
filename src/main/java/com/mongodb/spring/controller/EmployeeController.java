package com.mongodb.spring.controller;

import com.mongodb.spring.dao.EmployeeDAO;
import com.mongodb.spring.model.EmployeeModel;
import com.mongodb.spring.service.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@Document(collection = "mygrocerylist")
public class EmployeeController {

    @Autowired
    EmployeeDAO employeeDAO;

    @Autowired
    SequenceGeneratorService seqGeneratorService;

    @PostMapping("/create")
    public EmployeeModel create(@RequestBody EmployeeModel newEmployeeObject) {
        newEmployeeObject.setId(seqGeneratorService.generateSequence(EmployeeModel.SEQUENCE_NAME));
        return employeeDAO.save(newEmployeeObject);
    }

    @GetMapping("/read")
    public List<EmployeeModel> read() {
        return employeeDAO.findAll();
    }

    @GetMapping("/read/{id}")
    public EmployeeModel read(@PathVariable Long id) {
        Optional<EmployeeModel> employeeObj = employeeDAO.findById(id);
        if (employeeObj.isPresent()) {
            return employeeObj.get();
        } else {
            throw new RuntimeException("Employee not found with id " + id);
        }
    }

    @PutMapping("/update")
    public EmployeeModel update(@RequestBody EmployeeModel modifiedEmployeeObject) {
        return employeeDAO.save(modifiedEmployeeObject);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        Optional<EmployeeModel> employeeObj = employeeDAO.findById(id);
        if (employeeObj.isPresent()) {
            employeeDAO.delete(employeeObj.get());
            return "Employee deleted with id " + id;
        } else {
            throw new RuntimeException("Employee not found for id " + id);
        }
    }

}
