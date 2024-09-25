package com.digital.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;  // Correct import
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;  // Correct import
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.digital.entity.Employee;
import com.digital.repository.EmployeeRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    // POST method for creating a new employee with photo upload
    @PostMapping("/employees")
    public ResponseEntity<String> createEmployee(
            @RequestParam("employeeName") String employeeName,
            @RequestParam("role") String role,
            @RequestParam("salary") double salary,
            @RequestParam("qualification") String qualification,
            @RequestParam("dateOfJoin") String dateOfJoin,
            @RequestParam("email") String email,
            @RequestParam("address") String address,
            @RequestParam("photo") MultipartFile photo) {

        try {
            // Create and save the employee entity with the photo byte data
            Employee employee = new Employee();
            employee.setEmployeeName(employeeName);
            employee.setRole(role);
            employee.setSalary(salary);
            employee.setQualification(qualification);
            employee.setDateOfJoin(dateOfJoin);
            employee.setEmail(email);
            employee.setAddress(address);
            employee.setPhoto(photo.getBytes());  // Store photo as a byte array

            employeeRepository.save(employee);

            return ResponseEntity.status(HttpStatus.CREATED).body("Employee added successfully"); // Success response
        } catch (IOException e) {
            e.printStackTrace();  // Logging for debugging
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File upload failed: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();  // Logging for debugging
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating employee: " + e.getMessage());
        }
    }
    
    
    
    
    
    
    
    
    
    

    // New GET method to retrieve the employee photo
    @GetMapping("/employees/{id}/photo")
    public ResponseEntity<byte[]> getEmployeePhoto(@PathVariable long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        
        if (employee.isPresent() && employee.get().getPhoto() != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);  // Assuming the image is JPEG

            return new ResponseEntity<>(employee.get().getPhoto(), headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    
    
    
    
    
    

    // GET method to retrieve all employees
    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
}
