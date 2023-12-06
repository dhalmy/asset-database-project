package edu.iit.sat.itmd4515.dhalmy.web;

import edu.iit.sat.itmd4515.dhalmy.domain.Laptop;
import edu.iit.sat.itmd4515.dhalmy.service.LaptopService;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.logging.Logger;

/**
 *
 * @author David
 */

//JSF controller for -laptop.xhtml
@Named
@SessionScoped
public class LaptopController implements Serializable{


    private static final Logger LOG = Logger.getLogger(LaptopController.class.getName());
    private Laptop laptop;
    
    @EJB LaptopService ltSvc;
    
    @Inject
    private SessionBean sb;

    public LaptopController() {
    }
    
    @PostConstruct
    private void postConstruct(){
        LOG.info("Inside the LaptopController.postConstruct method");
        laptop = new Laptop();
    }
    
    
    public String demoAction(){
        LOG.info("LaptopController.demoAction has been invoked with laptop " + laptop.toString());
        return "confirmation.xhtml";
    }
    
    
    //MVC action methods
    public String displayReadLaptopPage(Laptop l){
        this.laptop = l;
        LOG.info("LaptopController.displayReadLaptopPage has been invoked with laptop" + laptop.toString());
        
        return "/read-entity/readLaptop.xhtml";
    }
    
    public String displayUpdateLaptopPage(Laptop l){
        this.laptop = l;
        LOG.info("LaptopController.displayUpdateLaptopPage has been invoked with laptop" + laptop.toString());
        
        return "/update-entity/updateLaptop.xhtml";
    }
    
    public String displayDeleteLaptopPage(Laptop l){
        this.laptop = l;
        LOG.info("LaptopController.displayReadLaptopPage has been invoked with laptop" + laptop.toString());
        
        return "/delete-entity/deleteLaptop.xhtml";
    }
    
    public Laptop getLaptop() {
        return laptop;
    }

    public void setLaptop(Laptop laptop) {
        this.laptop = laptop;
    }
    

    
    public String updateLaptop(){
        LOG.info("LaptopController.updateLaptop has been invoked with laptop " + laptop.toString());
        
        ltSvc.updateLaptopWRTRelationships(laptop);
        
        String returnPage = sb.getReturnPage();
        sb.returnHighestPrivilege();
        return returnPage;
    }
    
    public String deleteLaptop(){
        LOG.info("LaptopController.deleteLaptop has been invoked with laptop " + laptop.toString());
        
        ltSvc.deleteLaptopWRTRelationships(laptop);
        
        String returnPage = sb.getReturnPage();
        sb.returnHighestPrivilege();
        return returnPage;
    }
    
    public String saveLaptop(){
        LOG.info("LaptopController.saveLaptop has been invoked with laptop " + laptop.toString());
        
        ltSvc.create(laptop);
        
        LOG.info("LaptopController.saveLaptop has been invoked EJV call " + laptop.toString());
        
        String returnPage = sb.getReturnPage();
        sb.returnHighestPrivilege();
        
//        LOG.info("THIS IS MY RETURN PAGE FIND ME 123"+sb.getReturnPage());
        
        return returnPage;
    }
    


    
}
