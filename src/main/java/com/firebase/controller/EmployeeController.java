package com.firebase.controller;

import com.firebase.model.Employee;
import com.firebase.service.FireBaseInitializer;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/employees")
public class EmployeeController {


    @Autowired
    private FireBaseInitializer initializer;

    @GetMapping("/getAllEmployees")
    public List<Employee> getAllEmployees() throws ExecutionException, InterruptedException {
        List<Employee> employeeList = new ArrayList<>();

        CollectionReference reference = initializer.getFireBase().collection("Employee");
        ApiFuture<QuerySnapshot> snapshotApiFuture = reference.get();

        for (DocumentSnapshot doc: snapshotApiFuture.get().getDocuments())
            employeeList.add(doc.toObject(Employee.class));

        return employeeList;
    }


    @PostMapping("/saveEmployee")
    public String saveEmployee(@RequestBody Employee employee){
        CollectionReference reference = initializer.getFireBase().collection("Employee");
        reference.document(String.valueOf(employee.getId())).set(employee);
        return "Success";
    }


    @PutMapping("/updateEmployee")
    public String updateEmployee(@@RequestBody Employee employee){
        CollectionReference reference = initializer.getFireBase().collection("Employee");
        reference.document(String.valueOf(employee.getId())).set(employee);
        return "Success";
    }

}
