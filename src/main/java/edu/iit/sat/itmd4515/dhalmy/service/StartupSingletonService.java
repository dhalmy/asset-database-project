/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.dhalmy.service;

import edu.iit.sat.itmd4515.dhalmy.domain.Cubicle;
import edu.iit.sat.itmd4515.dhalmy.domain.DockingStation;
import edu.iit.sat.itmd4515.dhalmy.domain.Employee;
import edu.iit.sat.itmd4515.dhalmy.domain.EmployeeDepartment;
import edu.iit.sat.itmd4515.dhalmy.domain.Laptop;
import edu.iit.sat.itmd4515.dhalmy.domain.Monitor;
import edu.iit.sat.itmd4515.dhalmy.security.Group;
import edu.iit.sat.itmd4515.dhalmy.security.GroupService;
import edu.iit.sat.itmd4515.dhalmy.security.User;
import edu.iit.sat.itmd4515.dhalmy.security.UserService;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import java.time.LocalDate;
import java.time.Month;
import java.util.logging.Logger;

/**
 * A singleton service class responsible for startup operations and initializing
 * data for various entities in the application.
 * This class is automatically instantiated and executed on application startup.
 *
 * @author David
 */
@Startup
@Singleton
public class StartupSingletonService {

    private static final Logger LOG = Logger.getLogger(StartupSingletonService.class.getName());

    @EJB EmployeeService empSvc;
    @EJB LaptopService ltSvc;
    @EJB CubicleService cbSvc;
    @EJB MonitorService monSvc;
    @EJB DockingStationService dockSvc;
    
    // Services for security domain
    @EJB UserService userSvc;
    @EJB GroupService groupSvc;
    
    /**
     * Constructs a StartupSingletonService instance.
     * Payara controls the instantiation of this class.
     */
    public StartupSingletonService() {
        // Not in control of when this instance is constructed; Payara controls this.
    }
    
    /**
     * Performs initialization tasks during post-construction.
     * This method is automatically executed after the constructor.
     */
    @PostConstruct
    private void postConstruct(){
        LOG.info("StartupSingletonService.postConstruct()");
        
        // Create non-owning entities first
        Group HRGroup = new Group("HR_GROUP", "This group represents human resources department access");
        Group ITGroup = new Group("IT_GROUP", "This group is dedicated to the IT department for general use and interns");
        Group adminGroup = new Group("ADMIN_GROUP", "This group is also for the IT department but includes delete permissions");
        
        // Admin group has the highest privilege. Admin has access to IT and HR.
        // IT has privilege over IT and HR.
        // HR can only access HR.
        // HR view is just a view-only IT view.
        groupSvc.create(HRGroup);
        groupSvc.create(ITGroup);
        groupSvc.create(adminGroup);
        
        User HR1 = new User("HR1", "HR1", true);
        HR1.addGroup(HRGroup);
        userSvc.create(HR1);
        
        User HR2 = new User("HR2", "HR2", true);
        HR2.addGroup(HRGroup);
        userSvc.create(HR2);
        
        // This IT user is just an intern. They don't need admin permissions.
        User IT1 = new User("IT1", "IT1", true);
        IT1.addGroup(ITGroup);
        IT1.addGroup(HRGroup);
        userSvc.create(IT1);
        
        // This IT user is a superuser. Has admin permissions but is still part of IT.
        User IT2 = new User("IT2", "IT2", true);
        IT2.addGroup(ITGroup);
        IT2.addGroup(adminGroup);
        IT2.addGroup(HRGroup);
        userSvc.create(IT2);
        
        User admin = new User("admin", "admin", true);
        admin.addGroup(adminGroup);
        admin.addGroup(ITGroup);
        admin.addGroup(HRGroup);
        userSvc.create(admin);
        
        // First entities created are ones that don't own anything else
        
        DockingStation d1 = new DockingStation("F03935", "ZKT1B864", "X98668");
        DockingStation d2 = new DockingStation("F03994", "ZKT1E0D1", "X87812");
        DockingStation d3 = new DockingStation("F03991", "ZKT1E0DD", "X88749");
        
        
        
        dockSvc.create(d1);
        dockSvc.create(d2);
        dockSvc.create(d3);
        
        Monitor m1 = new Monitor("F04301", "CN0CC0F0TV2002C40ZGBA02", "Dell P2422HE");
        Monitor m2 = new Monitor("F04302", "CN0CC0F0TV2002C40ZDBA02", "Dell P2422HE");
        Monitor m3 = new Monitor("F04303", "CN0CC0F0TV2002C40Z7BA02", "Dell P2422HE");
        
        monSvc.create(m1);
        monSvc.create(m2);
        monSvc.create(m3);
        
        Cubicle c1 = new Cubicle(520);
        c1.addMonitor(m1);
        c1.addMonitor(m2);
        c1.setDockingStation(d1);
        Cubicle c2 = new Cubicle(521);
        c2.addMonitor(m3);
        c2.setDockingStation(d2);
        Cubicle c3 = new Cubicle(522);
        c3.setDockingStation(d3);
        
        cbSvc.create(c1);
        cbSvc.create(c2);
        cbSvc.create(c3);
        
        Employee e1 = new Employee("first", "employee", "femployee");
        e1.setType(EmployeeDepartment.RAP);
        e1.setCubicle(c1);
        
        Employee e2 = new Employee("second", "employee", "semployee");
        e2.setType(EmployeeDepartment.IT);
        e2.setCubicle(c2);
        e2.setUser(IT1);
        
        Employee e3 = new Employee("third", "employee", "temployee");
        e3.setType(EmployeeDepartment.HR);
        e3.setCubicle(c3);
        e3.setUser(HR1);
        
        Employee e4 = new Employee("david", "halmy", "dhalmy", LocalDate.of(1999,01,01), EmployeeDepartment.IT);
        e4.setType(EmployeeDepartment.IT);
        e4.setCubicle(c3);
        e4.setUser(IT2);
        
        empSvc.create(e1);
        empSvc.create(e2);
        empSvc.create(e3);
        empSvc.create(e4);
        
        Laptop t1 = new Laptop("LT-0322-MS5", "Microsoft Surface 5", "0F01GAD23133FB");
        t1.addEmployee(e1);
        Laptop t2 = new Laptop("LT-0323-MS5", "Microsoft Surface 5", "0F01G8U23133FB");
        t2.addEmployee(e2);
        Laptop t3 = new Laptop("LT-0324-MS5", "Microsoft Surface 5", "0F01GAL23133FB");
        t3.addEmployee(e3);
        
        ltSvc.create(t1);
        ltSvc.create(t2);
        ltSvc.create(t3);
        
        LOG.info("=-=-=-=-=- JPA Relationships =-=-=-=-=-");
        
        // Laptop to employee
        for(Laptop l : ltSvc.findAll()){
            LOG.info("=-=-=-=-=- Laptop " + l.getName() + " relationship to Employee =-=-=-=-=-");
            LOG.info(l.toString());
            LOG.info("\t" + l.getEmployee().toString());
            LOG.info(" - - - - - - - - - - - - - - - - - - - -");
        }
        
        // Employee to laptops
        for (Employee e : empSvc.findAll()) {
            LOG.info("=-=-=-=-=- Employee " + e.getEmployeeID() + " relationship to Laptops =-=-=-=-=-");
            LOG.info(e.toString());
            for (Laptop l : e.getLaptops()) {
                LOG.info("=-=-=-=-=- Laptop ID:" + l.getLaptopID() + " info  =-=-=-=-=-");
                LOG.info("\t" + l.toString());
            }
            LOG.info(" - - - - - - - - - - - - - - - - - - - -");
        }
        
        for(Cubicle c : cbSvc.findAll()){
            // Cubicle to employees
            LOG.info("=-=-=-=-=- Cubicle " + c.getCubicleID() + " relationship to Employees =-=-=-=-=-");
            LOG.info(c.toString());
            for (Employee e : c.getEmployees()) {
                LOG.info("=-=-=-=-=- Employee ID:" + e.getEmployeeID() + " info  =-=-=-=-=-");
                LOG.info("\t" + e.toString());
            }
            
            LOG.info("=-=-=-=-=- Cubicle " + c.getCubicleID()+ " relationship to Monitors & Docking Station =-=-=-=-=-");
            LOG.info(c.toString());
            
            // Cubicle to monitors
            for (Monitor m : c.getMonitors()) {
                LOG.info("=-=-=-=-=- Monitor ID:" + m.getMonitorID() + " info  =-=-=-=-=-");
                LOG.info("\t" + m.toString());
            }
            // Cubicle to docking station
            LOG.info("=-=-=-=-=- DockingStation ID:" + c.getDockingStation().getDockID()+ " info  =-=-=-=-=-");
            LOG.info("\t" + c.getDockingStation().toString());
            LOG.info(" - - - - - - - - - - - - - - - - - - - -");
        }
        
        LOG.info("=-=-=-=-=- JPA Relationships =-=-=-=-=-");
    }

    
}
