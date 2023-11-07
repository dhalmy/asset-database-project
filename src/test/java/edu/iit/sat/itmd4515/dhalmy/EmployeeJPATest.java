/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.dhalmy;

import edu.iit.sat.itmd4515.dhalmy.domain.Cubicle;
import edu.iit.sat.itmd4515.dhalmy.domain.DockingStation;
import edu.iit.sat.itmd4515.dhalmy.domain.Employee;
import edu.iit.sat.itmd4515.dhalmy.domain.EmployeeDepartment;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.time.LocalDate;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author David
 */
public class EmployeeJPATest {
    
    private static EntityManagerFactory emf;
    private EntityManager em;
    private EntityTransaction tx;
    
    @BeforeAll
    public static void beforeAll(){
        emf = Persistence.createEntityManagerFactory("itmd4515testPU");
    }
    @BeforeEach
    public void beforeEach(){
        em = emf.createEntityManager();
        tx = em.getTransaction();
        TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e WHERE e.auto_username = :auto_username", Employee.class);
        query.setParameter("auto_username", "ssmith");
        try {
            Employee existingEmployee = query.getSingleResult();
        } catch (NoResultException nre) {
            Employee e = new Employee("Sam", "Smith", "ssmith", LocalDate.of(2022, 12, 05), EmployeeDepartment.IT);
            tx.begin();
            em.persist(e);
            tx.commit();
        }
            
    }
    @Test
    public void createTest(){
        //create data then verify it was created in the database
        Employee p = new Employee("Davie", "Jones", "djones", LocalDate.of(2022, 1, 13), EmployeeDepartment.RPI);
        tx.begin();
        em.persist(p);
        tx.commit();
        
        assertNotNull(p);
        Employee retrievedEmployee = em.createQuery("select e from Employee e where e.auto_username = 'djones'", Employee.class).getSingleResult();
        assertEquals("djones",retrievedEmployee.getAuto_username());
        
        tx.begin();
        em.remove(retrievedEmployee);
        tx.commit();
        
    }
    
    @Test
    public void readTest(){
        //read back sample data
        //verify it was retrieved
        //assert it matches expected
        Employee e = em.createQuery("select e from Employee e where e.auto_username = 'ssmith'",Employee.class).getSingleResult();
        
        assertNotNull(e);
        assertTrue(e.getEmployeeID() > 0);
        assertEquals("ssmith",e.getAuto_username());
    }
    
    @Test
    public void updateTest(){
        //either make new data or use beforeEach data
        //data is data
        Employee e = em.createQuery("select e from Employee e where e.auto_username = 'ssmith'",Employee.class).getSingleResult();
        //modify something while adhereing to logic rules
        //Sam -> Samuel
        //if i changed the first letter of the first name, or changed the last name at all, I would have to modify whole username
        //update entity
               //either call setter on a managed entity within a transaction
                  //tx.begin();
                  //e.setFirstName("Samuel");
                  //tx.commit();
        //another way is to em.merge
        tx.begin();
        e.setFirstName("Samuel");
        em.merge(e);
        tx.commit();
        
        //read back from db and assert updated field was changed
        
        Employee employeeReadBackFromDatabaseForTestCase = em.find(Employee.class, e.getEmployeeID());
        
        assertEquals("Samuel", employeeReadBackFromDatabaseForTestCase.getFirstName());
        
        
    }
    
    @Test
    public void deleteTest(){ //em.remove
        //create test data, verify it's in the database, then remove the data, then verify it was removed
        Employee p = new Employee("Davie", "Jones", "djones", LocalDate.of(2022, 1, 13), EmployeeDepartment.RPI);
        tx.begin();
        em.persist(p);
        tx.commit();

        // verify that the new employee is in the database
        Employee retrievedEmployee = em.createQuery("select e from Employee e where e.auto_username = 'djones'", Employee.class).getSingleResult();
        assertNotNull(retrievedEmployee);

        //remove the employee
        tx.begin();
        em.remove(retrievedEmployee);
        tx.commit();

        // verify removal
        Employee deletedEmployee = em.find(Employee.class, retrievedEmployee.getEmployeeID());
        assertNull(deletedEmployee);
    }
    
    
    
    @Test
    public void uniDirectionalRelationshipsTest(){
        DockingStation d = new DockingStation("F04046", "ZVT08294", "X92723");
        Cubicle c = new Cubicle(435);
        
        c.setDockingStation(d);
        tx.begin();
        em.persist(d);
        em.persist(c);
        tx.commit();
         
        Cubicle readBackFromDB = em.find(Cubicle.class, c.getCubicleID());
         
        assertEquals("F04046",readBackFromDB.getDockingStation().getAssetTag());
        
        //remove test data
        tx.begin();
        em.remove(readBackFromDB);
        em.remove(d);
        em.remove(c);
        tx.commit();

        //verify that the data has been removed
        DockingStation deletedDock = em.find(DockingStation.class, d.getDockID());
        assertNull(deletedDock);

        Cubicle deletedCubicle = em.find(Cubicle.class, c.getCubicleID());
        assertNull(deletedCubicle);
    }
    
    
    @Test
    public void biDirectionalRelationshipsTest(){
        Employee e = new Employee("zoe","smith","zsmith");
        Cubicle c = new Cubicle(435);
        
        e.addCubicle(c);
        
//        e.setCubicle(c);
        tx.begin();
        em.persist(c);
        em.persist(e);
        tx.commit();
         
        Employee readBackFromDB = em.find(Employee.class, e.getEmployeeID());
         
        assertEquals(435,readBackFromDB.getCubicle().getCubicleID());
        
        assertFalse(c.getEmployees().isEmpty());
        assertNotNull(e.getEmployeeID());
        assertNotNull(c.getCubicleID());
        
//        System.out.println("from owning side:" + e.getCubicle().toString());
//        System.out.println("from inverse side " + c.getEmployees());
        
        
//        remove test data
        tx.begin();
        em.remove(readBackFromDB);
//        e.removeCubicle();
        em.remove(c);
        em.remove(e);
        tx.commit();

        //verify that the data has been removed
        Employee deletedEmployee = em.find(Employee.class, e.getEmployeeID());
        assertNull(deletedEmployee);

        Cubicle deletedCubicle = em.find(Cubicle.class, c.getCubicleID());
        assertNull(deletedCubicle);
    }
   
    @AfterEach
    public void afterEach(){
        Employee e = em.createQuery("select e from Employee e where e.auto_username = 'ssmith'",Employee.class).getSingleResult();
        
        tx.begin();
        em.remove(e);
        tx.commit();
    }
    @AfterAll
    public static void afterAll(){
        
    }
    
}
