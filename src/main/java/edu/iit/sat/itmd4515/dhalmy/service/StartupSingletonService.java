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
        
        User iwitty = new User("iwitty", "iwitty", true);
        iwitty.addGroup(HRGroup);
        userSvc.create(iwitty);
        
        User kreigster = new User("kreigster", "kreigster", true);
        kreigster.addGroup(HRGroup);
        userSvc.create(kreigster);
        
        // This IT user is just an intern. They don't need admin permissions.
        User gcolossalmer = new User("gcolossalmer", "gcolossalmer", true);
        gcolossalmer.addGroup(ITGroup);
        gcolossalmer.addGroup(HRGroup);
        userSvc.create(gcolossalmer);
        
        // This IT user is a superuser. Has admin permissions but is still part of IT.
        User sbarksalot = new User("sbarksalot", "sbarksalot", true);
        sbarksalot.addGroup(ITGroup);
        sbarksalot.addGroup(adminGroup);
        sbarksalot.addGroup(HRGroup);
        userSvc.create(sbarksalot);
        
        User admin = new User("admin", "admin", true);
        admin.addGroup(adminGroup);
        admin.addGroup(ITGroup);
        admin.addGroup(HRGroup);
        userSvc.create(admin);
        

//        //These users are admins
        User jkusionator = new User("jkusionator", "jkusionator", true);
        jkusionator.addGroup(adminGroup);
        jkusionator.addGroup(ITGroup);
        jkusionator.addGroup(HRGroup);
        userSvc.create(jkusionator);
        
        User apedrazzle = new User("apedrazzle", "apedrazzle", true);
        apedrazzle.addGroup(adminGroup);
        apedrazzle.addGroup(ITGroup);
        apedrazzle.addGroup(HRGroup);
        userSvc.create(apedrazzle);
        
        User dhalmy = new User("dhalmy", "dhalmy", true);
        dhalmy.addGroup(adminGroup);
        dhalmy.addGroup(ITGroup);
        dhalmy.addGroup(HRGroup);
        userSvc.create(dhalmy);
        
        // First entities created are ones that don't own anything else
        

        DockingStation d21 = new DockingStation("F04077", "4537595362");
        DockingStation d22 = new DockingStation("F04079", "6684007078");
        DockingStation d23 = new DockingStation("F04080", "9822888877");
        DockingStation d24 = new DockingStation("F04081", "8952943990");
        DockingStation d25 = new DockingStation("F04082", "8304883558");
        DockingStation d26 = new DockingStation("F04083", "9034885495");
        DockingStation d27 = new DockingStation("F04085", "5102532488");
        DockingStation d28 = new DockingStation("F04086", "5061196906");
        DockingStation d29 = new DockingStation("F04087", "1013125442");
        DockingStation d30 = new DockingStation("F04088", "2308482029", "X75430");
        DockingStation d31 = new DockingStation("F04090", "999317528");
        DockingStation d32 = new DockingStation("F04091", "3784615847");
        DockingStation d33 = new DockingStation("F03935", "3562318534", "X98668");
        DockingStation d34 = new DockingStation("F03994", "8407481205", "X87812");
        DockingStation d35 = new DockingStation("F03991", "3322922532", "X88749");
        DockingStation d36 = new DockingStation("F03910", "4337693850", "X87846");
        DockingStation d37 = new DockingStation("F03929", "390127399", "X98872");
        DockingStation d38 = new DockingStation("F03992", "5316766016", "X98541");
        DockingStation d39 = new DockingStation("F03934", "1946172213", "X78674");
        DockingStation d40 = new DockingStation("F03903", "2055019292", "X78733");
        DockingStation d41 = new DockingStation("F03924", "5387098057", "X98667");
        DockingStation d42 = new DockingStation("F03936", "5717974279", "X88716");
        DockingStation d43 = new DockingStation("F03880", "4383406883", "X78735");
        DockingStation d44 = new DockingStation("F03897", "4543017309", "X98841");
        DockingStation d45 = new DockingStation("F03894", "6802018666", "X98969");
        DockingStation d46 = new DockingStation("F03898", "7255435569", "X78913");
        DockingStation d47 = new DockingStation("F03892", "7336271818", "X97791");
        DockingStation d48 = new DockingStation("F03885", "9882278696", "X98843");
        DockingStation d49 = new DockingStation("F03879", "2174072099", "X78649");
        DockingStation d50 = new DockingStation("F03883", "7395656917", "X97795");
        
        
        

        dockSvc.create(d21);
        dockSvc.create(d22);
        dockSvc.create(d23);
        dockSvc.create(d24);
        dockSvc.create(d25);
        dockSvc.create(d26);
        dockSvc.create(d27);
        dockSvc.create(d28);
        dockSvc.create(d29);
        dockSvc.create(d30);
        dockSvc.create(d31);
        dockSvc.create(d32);
        dockSvc.create(d33);
        dockSvc.create(d34);
        dockSvc.create(d35);
        dockSvc.create(d36);
        dockSvc.create(d37);
        dockSvc.create(d38);
        dockSvc.create(d39);
        dockSvc.create(d40);
        dockSvc.create(d41);
        dockSvc.create(d42);
        dockSvc.create(d43);
        dockSvc.create(d44);
        dockSvc.create(d45);
        dockSvc.create(d46);
        dockSvc.create(d47);
        dockSvc.create(d48);
        dockSvc.create(d49);
        dockSvc.create(d50);


        
        
        Monitor m4 = new Monitor("F00645", "6CM6461J7K", "HP EliteDisplay E240");
        monSvc.create(m4);

        Monitor m5 = new Monitor("F00646", "6CM6461HPD", "HP EliteDisplay E240");
        monSvc.create(m5);

        Monitor m6 = new Monitor("F03693", "CN0CF4C1QDC0014J3QSLA18", "Dell P2419HC");
        monSvc.create(m6);

        Monitor m7 = new Monitor("F03694", "CN0CF4C1QDC001661SZBA18", "DELL P2419HC");
        monSvc.create(m7);

        Monitor m8 = new Monitor("F01799", "6CM8441DLX", "HP EliteDisplay E243i");
        monSvc.create(m8);

        Monitor m9 = new Monitor("F01801", "6CM8441DLZ", "HP EliteDisplay E243i");
        monSvc.create(m9);

        Monitor m10 = new Monitor("F03689", "CN0CF4C1QDC0014C1YULA18", "Dell P2419HC");
        monSvc.create(m10);

        Monitor m11 = new Monitor("F03696", "CN0CF4C1QDC001660BKBA18", "Dell P2419HC");
        monSvc.create(m11);

        Monitor m12 = new Monitor("F04479", "CN056D99QDC0034B0LMBA05", "Dell P2422HE");
        monSvc.create(m12);

        Monitor m13 = new Monitor("F04505", "CN056D99QDC0034B0LKBA05", "Dell P2422HE");
        monSvc.create(m13);

        Monitor m14 = new Monitor("F01797", "6CM8441DLV", "HP EliteDisplay E243i");
        monSvc.create(m14);

        Monitor m15 = new Monitor("F01798", "6CM8441DLW", "HP EliteDisplay E243i");
        monSvc.create(m15);

        Monitor m16 = new Monitor("F01805", "6CM84600RV", "HP EliteDisplay E243i");
        monSvc.create(m16);

        Monitor m17 = new Monitor("F01806", "6CM846015J", "HP EliteDisplay E243i");
        monSvc.create(m17);

        Monitor m18 = new Monitor("F04301", "CN0CC0F0TV2002C40ZDBA02", "Dell P2422HE");
        monSvc.create(m18);

        Monitor m19 = new Monitor("F04302", "CN0CC0F0TV2002C40Z7BA02", "Dell P2422HE");
        monSvc.create(m19);

        Monitor m20 = new Monitor("F04504", "CN056D99QDC0034B0KHBA05", "Dell P2422HE");
        monSvc.create(m20);

        Monitor m21 = new Monitor("F03671", "CN0CF4C1QDC0016514RBA18", "Dell P2419HC");
        monSvc.create(m21);

        Monitor m22 = new Monitor("F03672", "CN0CF4C1QDC0014C1Y4LA18", "Dell P2419HC");
        monSvc.create(m22);

        Monitor m23 = new Monitor("F04299", "CN0CC0F0TV2002C40Z9BA02", "Dell P2422HE");
        monSvc.create(m23);

        Monitor m24 = new Monitor("F04300", "CN0CC0F0TV2002C40ZGBA02", "Dell P2422HE");
        monSvc.create(m24);

        Monitor m25 = new Monitor("F00822", "CN44030GX8", "HP Compaq LA2405wg");
        monSvc.create(m25);

        Monitor m26 = new Monitor("F00824", "CN420907RC", "HP Compaq LA2405wg");
        monSvc.create(m26);

        Monitor m27 = new Monitor("F01592", "6CM6461HPQ", "HP EliteDisplay E240");
        monSvc.create(m27);

        Monitor m28 = new Monitor("F01604", "6CM6461J77", "HP EliteDisplay E240");
        monSvc.create(m28);

        Monitor m29 = new Monitor("F04292", "CN0CC0F0TV2002C40ZABA02", "Dell P2422HE");
        monSvc.create(m29);

        Monitor m30 = new Monitor("F04293", "CN0CC0F0TV2002C40P9BA02", "Dell P2422HE");
        monSvc.create(m30);

        Monitor m31 = new Monitor("F04494", "CN056D99QDC0034D0TQBA05", "Dell P2422HE");
        monSvc.create(m31);

        Monitor m32 = new Monitor("F04303", "CN0CC0F0TV2002C40Z6BA02", "Dell P2422HE");
        monSvc.create(m32);

        Monitor m33 = new Monitor("F04304", "CN0CC0F0TV2002C40PCBA02", "Dell P2422HE");
        monSvc.create(m33);

        Monitor m34 = new Monitor("F01357", "CN444915LS", "HP EliteDisplay E241i");
        monSvc.create(m34);

        Monitor m35 = new Monitor("F01358", "CN444916GG", "HP EliteDisplay E241i");
        monSvc.create(m35);

        Monitor m36 = new Monitor("F01457", "CN444915KG", "HP EliteDisplay E241i");
        monSvc.create(m36);

        Monitor m37 = new Monitor("F01458", "CN444915J9", "HP EliteDisplay E241i");
        monSvc.create(m37);

        Monitor m38 = new Monitor("F01290", "CN43150SK0", "HP Compaq LA2405x");
        monSvc.create(m38);

        Monitor m39 = new Monitor("F01291", "CN43150V1V", "HP Compaq LA2405x");
        monSvc.create(m39);

        Monitor m40 = new Monitor("F01353", "CN444915HY", "HP EliteDisplay E241i");
        monSvc.create(m40);

        Monitor m41 = new Monitor("F01354", "CN44007D5", "HP EliteDisplay E241i");
        monSvc.create(m41);

        Monitor m42 = new Monitor("F04510", "CN056D99QDC0034B0KFBA05", "Dell P2422HE");
        monSvc.create(m42);

        Monitor m43 = new Monitor("F04519", "CN056D99QDC0034B0LABA05", "Dell P2422HE");
        monSvc.create(m43);

        Monitor m44 = new Monitor("F04513", "CN056D99QDC0034B0MPBA05", "Dell P2422HE");
        monSvc.create(m44);

        Monitor m45 = new Monitor("F04516", "CN056D99QDC0034D0VMBA05", "Dell P2422HE");
        monSvc.create(m45);

        Monitor m46 = new Monitor("F04612", "TH01C8XNTVH0035P0A7BA03", "Dell P2422HE");
        monSvc.create(m46);

        Monitor m47 = new Monitor("F04650", "TH01C8XNTVH0035Q0QGLA03", "Dell P2422HE");
        monSvc.create(m47);

        Monitor m48 = new Monitor("F04475", "CN056D99QDC0034B0MHBA05", "Dell P2422HE");
        monSvc.create(m48);

        Monitor m49 = new Monitor("F04487", "CN056D99QDC0034B0MMBA05", "Dell P2422HE");
        monSvc.create(m49);

        Monitor m50 = new Monitor("F03691", "CN0CF4C1QDC0014J1QLLA18", "Dell P2419HC");
        monSvc.create(m50);

        Monitor m51 = new Monitor("F03692", "CN0CF4C1QDC0014D1S3LA18", "Dell P2419HC");
        monSvc.create(m51);

        Monitor m52 = new Monitor("F01785", "6CM8441D4Z", "HP EliteDisplay E243i");
        monSvc.create(m52);

        Monitor m53 = new Monitor("F04295", "CN0CC0F0TV2002C40P1BA02", "Dell P2422HE");
        monSvc.create(m53);

        Monitor m54 = new Monitor("F04298", "CN0CC0F0TV2002C40P4BA02", "Dell P2422HE");
        monSvc.create(m54);

        Monitor m55 = new Monitor("F04308", "CN0CC0F0TV2002C40Z5BA02", "Dell P2422HE");
        monSvc.create(m55);

        Monitor m56 = new Monitor("F04309", "CN0CC0F0TV2002C40PBBA02", "Dell P2422HE");
        monSvc.create(m56);

        Monitor m57 = new Monitor("F01610", "6CM6461J7B", "HP EliteDisplay E240");
        monSvc.create(m57);

        Monitor m58 = new Monitor("F01611", "6CM6461HPY", "HP EliteDisplay E240");
        monSvc.create(m58);

        Monitor m59 = new Monitor("F01790", "6CM8441D9Y", "HP EliteDisplay E243i");
        monSvc.create(m59);

        Monitor m60 = new Monitor("F01802", "6CM8441DM0", "HP EliteDisplay E243i");
        monSvc.create(m60);

        Monitor m61 = new Monitor("F01279", "CN43150TMR", "HP Compaq LA2405x");
        monSvc.create(m61);

        Monitor m62 = new Monitor("F01281", "CN44030GZQ", "HP Compaq LA2405x");
        monSvc.create(m62);

        Monitor m63 = new Monitor("F01349", "CN444915K6", "HP EliteDisplay E241i");
        monSvc.create(m63);

        Monitor m64 = new Monitor("F01350", "CN444915KD", "HP EliteDisplay E241i");
        monSvc.create(m64);
        
        Cubicle c1 = new Cubicle(415);
        c1.addMonitor(m4);
        c1.addMonitor(m5);
        c1.setDockingStation(d21);

        Cubicle c2 = new Cubicle(425);
        c2.addMonitor(m6);
        c2.addMonitor(m7);
        c2.setDockingStation(d22);

        Cubicle c3 = new Cubicle(427);
        c3.addMonitor(m8);
        c3.addMonitor(m9);
        c3.setDockingStation(d23);

        Cubicle c4 = new Cubicle(429);
        c4.addMonitor(m10);
        c4.addMonitor(m11);
        c4.setDockingStation(d24);

        Cubicle c5 = new Cubicle(430);
        c5.addMonitor(m12);
        c5.addMonitor(m13);
        c5.setDockingStation(d25);

        Cubicle c6 = new Cubicle(431);
        c6.addMonitor(m14);
        c6.addMonitor(m15);
        c6.setDockingStation(d26);

        Cubicle c7 = new Cubicle(434);
        c7.addMonitor(m16);
        c7.addMonitor(m17);
        c7.setDockingStation(d27);

        Cubicle c8 = new Cubicle(435);
        c8.addMonitor(m18);
        c8.addMonitor(m19);
        c8.setDockingStation(d28);

        Cubicle c9 = new Cubicle(437);
        c9.addMonitor(m21);

        Cubicle c10 = new Cubicle(438);
        c10.addMonitor(m22);
        c10.addMonitor(m23);
        c10.setDockingStation(d30);

        Cubicle c11 = new Cubicle(439);
        c11.addMonitor(m24);
        c11.addMonitor(m25);
        c11.setDockingStation(d31);

        Cubicle c12 = new Cubicle(443);
        c12.addMonitor(m26);
        c12.addMonitor(m27);
        c12.setDockingStation(d32);

        Cubicle c13 = new Cubicle(444);
        c13.addMonitor(m28);
        c13.addMonitor(m29);
        c13.setDockingStation(d33);

        Cubicle c14 = new Cubicle(450);
        c14.addMonitor(m30);
        c14.addMonitor(m31);
        c14.setDockingStation(d34);

        Cubicle c15 = new Cubicle(453);
        c15.addMonitor(m33);

        Cubicle c16 = new Cubicle(454);
        c16.addMonitor(m34);
        c16.addMonitor(m35);
        c16.setDockingStation(d36);

        Cubicle c17 = new Cubicle(456);
        c17.addMonitor(m36);
        c17.addMonitor(m37);
        c17.setDockingStation(d37);

        Cubicle c18 = new Cubicle(457);
        c18.addMonitor(m38);
        c18.addMonitor(m39);
        c18.setDockingStation(d38);

        Cubicle c19 = new Cubicle(458);
        c19.addMonitor(m40);
        c19.addMonitor(m41);
        c19.setDockingStation(d39);

        Cubicle c20 = new Cubicle(460);
        c20.addMonitor(m42);
        c20.addMonitor(m43);
        c20.setDockingStation(d40);

        Cubicle c21 = new Cubicle(462);
        c21.addMonitor(m44);
        c21.addMonitor(m45);
        c21.setDockingStation(d41);

        Cubicle c22 = new Cubicle(463);
        c22.addMonitor(m46);
        c22.addMonitor(m47);
        c22.setDockingStation(d42);

        Cubicle c23 = new Cubicle(464);
        c23.addMonitor(m48);
        c23.addMonitor(m49);
        c23.setDockingStation(d43);

        Cubicle c24 = new Cubicle(465);
        c24.addMonitor(m50);
        c24.addMonitor(m51);
        c24.setDockingStation(d44);

        Cubicle c25 = new Cubicle(467);
        c25.addMonitor(m52);
        c25.addMonitor(m53);
        c25.setDockingStation(d45);

        Cubicle c26 = new Cubicle(468);
        c26.addMonitor(m55);

        Cubicle c27 = new Cubicle(470);
        c27.addMonitor(m56);
        c27.addMonitor(m57);
        c27.setDockingStation(d47);

        Cubicle c28 = new Cubicle(512);
        c28.addMonitor(m58);
        c28.addMonitor(m59);
        c28.setDockingStation(d48);

        Cubicle c29 = new Cubicle(513);
        c29.addMonitor(m60);
        c29.addMonitor(m61);
        c29.setDockingStation(d49);

        Cubicle c30 = new Cubicle(514);
        c30.addMonitor(m62);
        c30.addMonitor(m63);
        c30.setDockingStation(d50);

        
        cbSvc.create(c1);
        cbSvc.create(c2);
        cbSvc.create(c3);
        cbSvc.create(c4);
        cbSvc.create(c5);
        cbSvc.create(c6);
        cbSvc.create(c7);
        cbSvc.create(c8);
        cbSvc.create(c9);
        cbSvc.create(c10);
        cbSvc.create(c11);
        cbSvc.create(c12);
        cbSvc.create(c13);
        cbSvc.create(c14);
        cbSvc.create(c15);
        cbSvc.create(c16);
        cbSvc.create(c17);
        cbSvc.create(c18);
        cbSvc.create(c19);
        cbSvc.create(c20);
        cbSvc.create(c21);
        cbSvc.create(c22);
        cbSvc.create(c23);
        cbSvc.create(c24);
        cbSvc.create(c25);
        cbSvc.create(c26);
        cbSvc.create(c27);
        cbSvc.create(c28);
        cbSvc.create(c29);
        cbSvc.create(c30);
        
        Employee e1 = new Employee("Ingy", "Witty", "iwitty");
        e1.setType(EmployeeDepartment.HR);
        e1.setCubicle(c1);
        e1.setUser(iwitty);

        Employee e2 = new Employee("Kooky", "Reigster");
        e2.setType(EmployeeDepartment.HR);
        e2.setCubicle(c1);
        e2.setUser(kreigster);

        Employee e3 = new Employee("Johnny", "Carp");
        e3.setType(EmployeeDepartment.GOVAFFAIRS);
        e3.setCubicle(c2);

        Employee e4 = new Employee("Blanky", "Schnitzel");
        e4.setType(EmployeeDepartment.EXECUTIVE);
        e4.setCubicle(c3);

        Employee e5 = new Employee("Nutty", "Kurioctopus");
        e5.setType(EmployeeDepartment.COMMS);
        e5.setCubicle(c3);

        Employee e6 = new Employee("Jolly", "Groverfield");
        e6.setType(EmployeeDepartment.COMMS);
        e6.setCubicle(c4);

        Employee e7 = new Employee("Al", "Dente");
        e7.setType(EmployeeDepartment.COMMS);
        e7.setCubicle(c5);

        Employee e8 = new Employee("Munchkin", "Krayfish");
        e8.setType(EmployeeDepartment.RPI);
        e8.setCubicle(c5);

        Employee e9 = new Employee("Prankster", "Daydream");
        e9.setType(EmployeeDepartment.PLANNING);
        e9.setCubicle(c6);

        Employee e10 = new Employee("Phoebulous", "Downpour");
        e10.setType(EmployeeDepartment.RAP);
        e10.setCubicle(c7);

        Employee e11 = new Employee("Terrible", "Dixie");
        e11.setType(EmployeeDepartment.RAP);
        e11.setCubicle(c8);

        Employee e12 = new Employee("Amazing", "Leek");
        e12.setType(EmployeeDepartment.RAP);
        e12.setCubicle(c9);

        Employee e13 = new Employee("Crayon", "Heitherspoon");
        e13.setType(EmployeeDepartment.RAP);
        e13.setCubicle(c10);

        Employee e14 = new Employee("Junk", "Kusionator");
        e14.setType(EmployeeDepartment.IT);
        e14.setCubicle(c10);
        e14.setUser(jkusionator);

        Employee e15 = new Employee("Laughing", "Ahiablammo");
        e15.setType(EmployeeDepartment.RAP);
        e15.setCubicle(c11);

        Employee e16 = new Employee("Alpaca", "Giggles");
        e16.setType(EmployeeDepartment.FINANCE);
        e16.setCubicle(c12);

        Employee e17 = new Employee("Megabyte", "Funara");
        e17.setType(EmployeeDepartment.FINANCE);
        e17.setCubicle(c13);

        Employee e18 = new Employee("Eliza", "Scarletto");
        e18.setType(EmployeeDepartment.RPI);
        e18.setCubicle(c14);

        Employee e19 = new Employee("Toasty", "Marmalade");
        e19.setType(EmployeeDepartment.PLANNING);
        e19.setCubicle(c14);

        Employee e20 = new Employee("Bouncy", "Barnesworth");
        e20.setType(EmployeeDepartment.RPI);
        e20.setCubicle(c15);

        Employee e21 = new Employee("Jellybean", "Navotale");
        e21.setType(EmployeeDepartment.RPI);
        e21.setCubicle(c16);

        Employee e22 = new Employee("Jolly", "Rancher");
        e22.setType(EmployeeDepartment.PLANNING);
        e22.setCubicle(c16);

        Employee e23 = new Employee("Kitten", "Cernacopia");
        e23.setType(EmployeeDepartment.COMMS);
        e23.setCubicle(c16);

        Employee e24 = new Employee("McFlurry", "Brownie");
        e24.setType(EmployeeDepartment.PLANNING);
        e24.setCubicle(c17);

        Employee e25 = new Employee("Rainbow", "Thompthunder");
        e25.setType(EmployeeDepartment.RPI);
        e25.setCubicle(c17);

        Employee e26 = new Employee("Lollipop", "Wilkinsmiles");
        e26.setType(EmployeeDepartment.GOVAFFAIRS);
        e26.setCubicle(c18);

        Employee e27 = new Employee("Stegosaurus", "Phiferrari");
        e27.setType(EmployeeDepartment.PLANNING);
        e27.setCubicle(c18);

        Employee e28 = new Employee("Iscream", "Veleztastic");
        e28.setType(EmployeeDepartment.EXECUTIVE);
        e28.setCubicle(c19);

        Employee e29 = new Employee("Eruption", "Alemaniac");
        e29.setType(EmployeeDepartment.EXECUTIVE);
        e29.setCubicle(c20);

        Employee e30 = new Employee("Amethyst", "McEwander");
        e30.setType(EmployeeDepartment.EXECUTIVE);
        e30.setCubicle(c21);

        Employee e31 = new Employee("Jigsaw", "Vananoodle");
        e31.setType(EmployeeDepartment.COMMS);
        e31.setCubicle(c21);

        Employee e32 = new Employee("Grinning", "Colossalmer");
        e32.setType(EmployeeDepartment.IT);
        e32.setCubicle(c22);
        e32.setUser(gcolossalmer);
        

        Employee e33 = new Employee("Smiles", "Barksalot");
        e33.setType(EmployeeDepartment.IT);
        e33.setCubicle(c23);
        e33.setUser(sbarksalot);

        Employee e34 = new Employee("Arcade", "Pedrazzle");
        e34.setType(EmployeeDepartment.IT);
        e34.setCubicle(c24);
        e34.setUser(apedrazzle);
        
        Employee e35 = new Employee("david", "halmy", "dhalmy", LocalDate.of(1999,01,01), EmployeeDepartment.IT);
        e35.setType(EmployeeDepartment.IT);
        e35.setCubicle(c25);
        e35.setUser(dhalmy);
        
        empSvc.create(e1);
        empSvc.create(e2);
        empSvc.create(e3);
        empSvc.create(e4);
        empSvc.create(e5);
        empSvc.create(e6);
        empSvc.create(e7);
        empSvc.create(e8);
        empSvc.create(e9);
        empSvc.create(e10);
        empSvc.create(e11);
        empSvc.create(e12);
        empSvc.create(e13);
        empSvc.create(e14);
        empSvc.create(e15);
        empSvc.create(e16);
        empSvc.create(e17);
        empSvc.create(e18);
        empSvc.create(e19);
        empSvc.create(e20);
        empSvc.create(e21);
        empSvc.create(e22);
        empSvc.create(e23);
        empSvc.create(e24);
        empSvc.create(e25);
        empSvc.create(e26);
        empSvc.create(e27);
        empSvc.create(e28);
        empSvc.create(e29);
        empSvc.create(e30);
        empSvc.create(e31);
        empSvc.create(e32);
        empSvc.create(e33);
        empSvc.create(e34);
        empSvc.create(e35);
        
        Laptop t1 = new Laptop("F02138", "PF28E1S3", "LT-0144-T490", "Lenovo T490");
        t1.addEmployee(e1);

        Laptop t2 = new Laptop("F02132", "0F00F2923113FB", "LT-0145-T490", "Lenovo T490");
        t2.addEmployee(e2);

        Laptop t3 = new Laptop("F02141", "X9AC4B2YZ", "LT-0146-T490", "Lenovo T490");
        t3.addEmployee(e3);

        Laptop t4 = new Laptop("F02118", "1B3456CD", "LT-0147-T490", "Lenovo T490");
        t4.addEmployee(e4);

        Laptop t5 = new Laptop("F02119", "PF1WDHE4", "LT-0148-T490", "Lenovo T490");
        t5.addEmployee(e5);

        Laptop t6 = new Laptop("F02116", "2T3G7TQR", "LT-0149-T490", "Lenovo T490");
        t6.addEmployee(e6);

        Laptop t7 = new Laptop("F02120", "PF1JY9MD", "LT-0150-T490", "Lenovo T490");
        t7.addEmployee(e7);

        Laptop t8 = new Laptop("F02122", "PF56DX2A", "LT-0151-T490", "Lenovo T490");
        t8.addEmployee(e8);

        Laptop t9 = new Laptop("F02150", "3F8F9C7D", "LT-0152-T490", "Lenovo T490");
        t9.addEmployee(e9);

        Laptop t10 = new Laptop("F03007", "4L09E1PH", "LT-0216-T15", "Lenovo T15");
        t10.addEmployee(e10);

        Laptop t11 = new Laptop("F02990", "PF1XJYQ4", "LT-0217-T15", "Lenovo T15");
        t11.addEmployee(e11);

        Laptop t12 = new Laptop("F03002", "7X5R3PBF", "LT-0218-T15", "Lenovo T15");
        t12.addEmployee(e12);

        Laptop t13 = new Laptop("F03011", "PF11G2D3", "LT-0219-T15", "Lenovo T15");
        t13.addEmployee(e13);

        Laptop t14 = new Laptop("F04142", "PF3S4K0A", "LT-0284-P15S", "Lenovo P15s Gen 2");
        t14.addEmployee(e14);

        Laptop t15 = new Laptop("F04135", "Y00A7A1B", "LT-0285-P15S", "Lenovo P15s Gen 2");
        t15.addEmployee(e15);

        Laptop t16 = new Laptop("F04204", "PF2X12AB", "LT-0291-MS5", "Microsoft Surface 5");
        t16.addEmployee(e16);

        Laptop t17 = new Laptop("F04203", "PF0DK32B", "LT-0292-MS4", "Microsoft Surface 4");
        t17.addEmployee(e17);

        Laptop t18 = new Laptop("F04197", "9G6H5C8J", "LT-0293-MS4", "Microsoft Surface 4");
        t18.addEmployee(e18);

        Laptop t19 = new Laptop("F04199", "PF0V8A2C", "LT-0294-MS5", "Microsoft Surface 5");
        t19.addEmployee(e19);

        Laptop t20 = new Laptop("F04291", "PF4B8G9C", "LT-0309-MSS", "Microsoft Surface Studio");
        t20.addEmployee(e20);

        Laptop t21 = new Laptop("F04261", "0P1F2A3E4G5H", "LT-0310-MS5", "Microsoft Surface Studio");
        t21.addEmployee(e21);

        Laptop t22 = new Laptop("F04323", "PF1T6Y8A", "LT-0311-MS5", "Microsoft Surface 5");
        t22.addEmployee(e22);

        Laptop t23 = new Laptop("F04320", "PF1G2C1B", "LT-0312-MS5", "Microsoft Surface 5");
        t23.addEmployee(e23);

        Laptop t24 = new Laptop("F04316", "6M7M8M9M", "LT-0313-MS5", "Microsoft Surface 5");
        t24.addEmployee(e24);

        Laptop t25 = new Laptop("F04315", "PF3SBKAW", "LT-0314-MS5", "Microsoft Surface 5");
        t25.addEmployee(e25);

        Laptop t26 = new Laptop("F04325", "PF9SB8H0", "LT-0315-MS5", "Microsoft Surface 5");
        t26.addEmployee(e26);

        Laptop t27 = new Laptop("F04321", "PF4W5Y6A", "LT-0316-MS5", "Microsoft Surface 5");
        t27.addEmployee(e27);

        Laptop t28 = new Laptop("F04319", "PF4Y8Q9B", "LT-0317-MS5", "Microsoft Surface 5");
        t28.addEmployee(e28);

        Laptop t29 = new Laptop("F04317", "PF1S2B3H4G", "LT-0318-MS5", "Microsoft Surface 5");
        t29.addEmployee(e29);

        Laptop t30 = new Laptop("F04318", "P3F4S1B2A3C", "LT-0319-MS5", "Microsoft Surface 5");
        t30.addEmployee(e30);

        Laptop t31 = new Laptop("F04324", "PF5G4A6D7B", "LT-0320-MS5", "Microsoft Surface 5");
        t31.addEmployee(e31);

        Laptop t32 = new Laptop("F04322", "PF4F4G4F4A", "LT-0321-MS5", "Microsoft Surface 5");
        t32.addEmployee(e32);

        Laptop t33 = new Laptop("F04310", "PF2H2A2A2A2B", "LT-0322-MS5", "Microsoft Surface 5");
        t33.addEmployee(e33);

        Laptop t34 = new Laptop("F04313", "PF4A8Y7A", "LT-0323-MS5", "Microsoft Surface 5");
        t34.addEmployee(e34);

        Laptop t35 = new Laptop("F04311", "PF0S2C8T4", "LT-0324-MS5", "Microsoft Surface 5");
        t35.addEmployee(e35);
        
        ltSvc.create(t1);
        ltSvc.create(t2);
        ltSvc.create(t3);
        ltSvc.create(t4);
        ltSvc.create(t5);
        ltSvc.create(t6);
        ltSvc.create(t7);
        ltSvc.create(t8);
        ltSvc.create(t9);
        ltSvc.create(t10);
        ltSvc.create(t11);
        ltSvc.create(t12);
        ltSvc.create(t13);
        ltSvc.create(t14);
        ltSvc.create(t15);
        ltSvc.create(t16);
        ltSvc.create(t17);
        ltSvc.create(t18);
        ltSvc.create(t19);
        ltSvc.create(t20);
        ltSvc.create(t21);
        ltSvc.create(t22);
        ltSvc.create(t23);
        ltSvc.create(t24);
        ltSvc.create(t25);
        ltSvc.create(t26);
        ltSvc.create(t27);
        ltSvc.create(t28);
        ltSvc.create(t29);
        ltSvc.create(t30);
        ltSvc.create(t31);
        ltSvc.create(t32);
        ltSvc.create(t33);
        ltSvc.create(t34);
        ltSvc.create(t35);

        
        LOG.info("=-=-=-=-=- JPA Relationships =-=-=-=-=-");
        
        // Laptop to employee
        for(Laptop l : ltSvc.findAll()){
            if(l.getEmployee() != null){
                LOG.info("=-=-=-=-=- Laptop " + l.getName() + " relationship to Employee =-=-=-=-=-");
                LOG.info(l.toString());
                LOG.info("\t" + l.getEmployee().toString());
                LOG.info(" - - - - - - - - - - - - - - - - - - - -");
            }
            
        }
        
        // Employee to laptops
        for (Employee e : empSvc.findAll()) {
            if(e.getLaptops() != null) {
                LOG.info("=-=-=-=-=- Employee " + e.getEmployeeID() + " relationship to Laptops =-=-=-=-=-");
                LOG.info(e.toString());
                for (Laptop l : e.getLaptops()) {
                    LOG.info("=-=-=-=-=- Laptop ID:" + l.getLaptopID() + " info  =-=-=-=-=-");
                    LOG.info("\t" + l.toString());
                }
                LOG.info(" - - - - - - - - - - - - - - - - - - - -");
            }
            
        }
        
        for(Cubicle c : cbSvc.findAll()){
            if (c.getEmployees() != null) {
                // Cubicle to employees
                LOG.info("=-=-=-=-=- Cubicle " + c.getCubicleID() + " relationship to Employees =-=-=-=-=-");
                LOG.info(c.toString());
                for (Employee e : c.getEmployees()) {
                    LOG.info("=-=-=-=-=- Employee ID:" + e.getEmployeeID() + " info  =-=-=-=-=-");
                    LOG.info("\t" + e.toString());
                }
            }
            
            
            LOG.info("=-=-=-=-=- Cubicle " + c.getCubicleID()+ " relationship to Monitors & Docking Station =-=-=-=-=-");
            LOG.info(c.toString());
            
            if (c.getMonitors() != null){
                // Cubicle to monitors
                for (Monitor m : c.getMonitors()) {
                    LOG.info("=-=-=-=-=- Monitor ID:" + m.getMonitorID() + " info  =-=-=-=-=-");
                    LOG.info("\t" + m.toString());
                }
            }
            
            // Cubicle to docking station
            if (c.getDockingStation() != null){
                LOG.info("=-=-=-=-=- DockingStation ID:" + c.getDockingStation().getDockID() + " info  =-=-=-=-=-");
                LOG.info("\t" + c.getDockingStation().toString());
                LOG.info(" - - - - - - - - - - - - - - - - - - - -");
            }
            
        }
        
        LOG.info("=-=-=-=-=- JPA Relationships =-=-=-=-=-");
    }

    
}
