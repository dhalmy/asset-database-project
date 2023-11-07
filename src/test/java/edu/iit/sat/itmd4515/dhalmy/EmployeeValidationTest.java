/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.dhalmy;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.time.LocalDate;
import java.util.Set;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import edu.iit.sat.itmd4515.dhalmy.domain.Employee;
import edu.iit.sat.itmd4515.dhalmy.domain.EmployeeDepartment;

public class EmployeeValidationTest {

    private static Validator validator;
    private Employee employee;

    @BeforeAll
    public static void beforeAll() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @BeforeEach
    public void beforeEach() {
        //create an employee
        employee = new Employee("John", "Doe", "johndoe", LocalDate.of(2022, 3, 15), EmployeeDepartment.IT);
    }

    @Test
    public void validateValidEmployee() {
        Set<ConstraintViolation<Employee>> constraintViolations = validator.validate(employee);

        for (ConstraintViolation<Employee> v : constraintViolations) {
            System.out.println(v.getMessage());
        }

        assertTrue(constraintViolations.isEmpty());
    }

    @Test
    public void validateInvalidFirstName() {
        //set an invalid first name
        employee.setFirstName("John123");

        Set<ConstraintViolation<Employee>> constraintViolations = validator.validate(employee);

        for (ConstraintViolation<Employee> v : constraintViolations) {
            System.out.println(v.getMessage());
        }

        assertEquals(1, constraintViolations.size());
    }

    @Test
    public void validateInvalidUsername() {
        //set invalid username
        employee.setAuto_username("johndoe@123");

        Set<ConstraintViolation<Employee>> constraintViolations = validator.validate(employee);

        for (ConstraintViolation<Employee> v : constraintViolations) {
            System.out.println(v.getMessage());
        }

        assertEquals(1, constraintViolations.size());
    }

    @Test
    public void validateInvalidHireDate() {
        //set invalid hire date
        employee.setHireDate(LocalDate.of(2032, 3, 15));

        Set<ConstraintViolation<Employee>> constraintViolations = validator.validate(employee);

        for (ConstraintViolation<Employee> v : constraintViolations) {
            System.out.println(v.getMessage());
        }

        assertEquals(1, constraintViolations.size());
    }
    
    @AfterEach
    public void afterEach() {
       
    }

    @AfterAll
    public static void afterAll() {
       
    }
}
