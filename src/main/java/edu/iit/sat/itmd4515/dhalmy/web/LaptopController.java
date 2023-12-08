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
 * JSF controller for managing laptops.
 * Author: David
 */
@Named
@SessionScoped
public class LaptopController implements Serializable {

    private static final Logger LOG = Logger.getLogger(LaptopController.class.getName());
    private Laptop laptop;

    @EJB
    LaptopService ltSvc;

    @Inject
    private SessionBean sb;

    /**
     * Creates a new instance of LaptopController.
     */
    public LaptopController() {
    }

    @PostConstruct
    private void postConstruct() {
        LOG.info("Inside the LaptopController.postConstruct method");
        laptop = new Laptop();
    }

    /**
     * Perform a demo action.
     *
     * @return A navigation outcome string.
     */
    public String demoAction() {
        LOG.info("LaptopController.demoAction has been invoked with laptop " + laptop.toString());
        return "confirmation.xhtml";
    }

    // MVC action methods

    /**
     * Display the read laptop page for a given laptop.
     *
     * @param l The laptop to display.
     * @return A navigation outcome string.
     */
    public String displayReadLaptopPage(Laptop l) {
        this.laptop = l;
        LOG.info("LaptopController.displayReadLaptopPage has been invoked with laptop" + laptop.toString());

        return "/read-entity/readLaptop.xhtml";
    }

    /**
     * Display the update laptop page for a given laptop.
     *
     * @param l The laptop to update.
     * @return A navigation outcome string.
     */
    public String displayUpdateLaptopPage(Laptop l) {
        this.laptop = l;
        LOG.info("LaptopController.displayUpdateLaptopPage has been invoked with laptop" + laptop.toString());

        return "/update-entity/updateLaptop.xhtml";
    }

    /**
     * Display the delete laptop page for a given laptop.
     *
     * @param l The laptop to delete.
     * @return A navigation outcome string.
     */
    public String displayDeleteLaptopPage(Laptop l) {
        this.laptop = l;
        LOG.info("LaptopController.displayReadLaptopPage has been invoked with laptop" + laptop.toString());

        return "/delete-entity/deleteLaptop.xhtml";
    }

    /**
     * Get the current laptop.
     *
     * @return The current laptop.
     */
    public Laptop getLaptop() {
        return laptop;
    }

    /**
     * Set the current laptop.
     *
     * @param laptop The laptop to set.
     */
    public void setLaptop(Laptop laptop) {
        this.laptop = laptop;
    }

    /**
     * Update a laptop.
     *
     * @return A navigation outcome string.
     */
    public String updateLaptop() {
        LOG.info("LaptopController.updateLaptop has been invoked with laptop " + laptop.toString());

        ltSvc.updateLaptopWRTRelationships(laptop);

        String returnPage = sb.getReturnPage();
        sb.returnHighestPrivilege();
        return returnPage;
    }

    /**
     * Delete a laptop.
     *
     * @return A navigation outcome string.
     */
    public String deleteLaptop() {
        LOG.info("LaptopController.deleteLaptop has been invoked with laptop " + laptop.toString());

        ltSvc.deleteLaptopWRTRelationships(laptop);

        String returnPage = sb.getReturnPage();
        sb.returnHighestPrivilege();
        return returnPage;
    }

    /**
     * Save a new laptop.
     *
     * @return A navigation outcome string.
     */
    public String saveLaptop() {
        LOG.info("LaptopController.saveLaptop has been invoked with laptop " + laptop.toString());

        ltSvc.create(laptop);

        LOG.info("LaptopController.saveLaptop has been invoked EJV call " + laptop.toString());

        String returnPage = sb.getReturnPage();
        sb.returnHighestPrivilege();

        return returnPage;
    }
}

