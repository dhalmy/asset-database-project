package edu.iit.sat.itmd4515.dhalmy;

import edu.iit.sat.itmd4515.dhalmy.domain.Employee;
import edu.iit.sat.itmd4515.dhalmy.domain.EmployeeDepartment;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import java.time.LocalDate;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author David
 */
public class Demo {

    public static void main(String args[]) {

        Employee e = new Employee("Sam", "Smith", "ssmith", LocalDate.of(2022, 12, 05), EmployeeDepartment.IT);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("itmd4515testPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        System.out.println(em.getProperties());
        System.out.println("Before commiting transaction" + e.toString());

        tx.begin();
        em.persist(e);
        tx.commit();
        System.out.println("After commiting transaction" + e.toString());
        em.close();
    }

}
