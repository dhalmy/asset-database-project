package edu.iit.sat.itmd4515.dhalmy.web;

import edu.iit.sat.itmd4515.dhalmy.domain.DockingStation;
import edu.iit.sat.itmd4515.dhalmy.service.DockingStationService;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.logging.Logger;

/**
 * JSF controller for managing docking stations in the application. This
 * controller handles actions related to docking stations, such as displaying,
 * updating, deleting, and saving docking station information.
 *
 * @author David
 */
@Named
@SessionScoped
public class DockingStationController implements Serializable {

    private static final Logger LOG = Logger.getLogger(DockingStationController.class.getName());
    private DockingStation dockingStation;

    @EJB
    DockingStationService dsSvc;

    @Inject
    private SessionBean sb;

    /**
     * Constructs a new instance of DockingStationController. This constructor
     * initializes a new docking station instance.
     */
    public DockingStationController() {
    }

    @PostConstruct
    private void postConstruct() {
        LOG.info("Inside the DockingStationController.postConstruct method");
        dockingStation = new DockingStation();
    }

    /**
     * A demo action for testing purposes.
     *
     * @return The navigation outcome to confirmation.xhtml.
     */
    public String demoAction() {
        LOG.info("DockingStationController.demoAction has been invoked with dockingStation " + dockingStation.toString());
        return "confirmation.xhtml";
    }

    // MVC action methods
    /**
     * Displays the read page for a specific docking station.
     *
     * @param ds The docking station to be displayed.
     * @return The navigation outcome to the readDockingStation.xhtml page.
     */
    public String displayReadDockingStationPage(DockingStation ds) {
        this.dockingStation = ds;
        LOG.info("DockingStationController.displayReadDockingStationPage has been invoked with dockingStation" + dockingStation.toString());

        return "/read-entity/readDockingStation.xhtml";
    }

    /**
     * Displays the update page for a specific docking station.
     *
     * @param ds The docking station to be updated.
     * @return The navigation outcome to the updateDockingStation.xhtml page.
     */
    public String displayUpdateDockingStationPage(DockingStation ds) {
        this.dockingStation = ds;
        LOG.info("DockingStationController.displayUpdateDockingStationPage has been invoked with dockingStation" + dockingStation.toString());

        return "/update-entity/updateDockingStation.xhtml";
    }

    /**
     * Displays the delete page for a specific docking station.
     *
     * @param ds The docking station to be deleted.
     * @return The navigation outcome to the deleteDockingStation.xhtml page.
     */
    public String displayDeleteDockingStationPage(DockingStation ds) {
        this.dockingStation = ds;
        LOG.info("DockingStationController.displayDeleteDockingStationPage has been invoked with dockingStation" + dockingStation.toString());

        return "/delete-entity/deleteDockingStation.xhtml";
    }

    /**
     * Gets the current docking station.
     *
     * @return The current docking station.
     */
    public DockingStation getDockingStation() {
        return dockingStation;
    }

    /**
     * Sets the current docking station.
     *
     * @param dockingStation The docking station to set.
     */
    public void setDockingStation(DockingStation dockingStation) {
        this.dockingStation = dockingStation;
    }

    /**
     * Updates the information of the current docking station.
     *
     * @return The navigation outcome based on the success of the update.
     */
    public String updateDockingStation() {
        LOG.info("DockingStationController.updateDockingStation has been invoked with dockingStation " + dockingStation.toString());

        dsSvc.updateDockingStationWRTRelationships(dockingStation);

        String returnPage = sb.getReturnPage();
        sb.returnHighestPrivilege();
        return returnPage;
    }

    /**
     * Deletes the current docking station.
     *
     * @return The navigation outcome based on the success of the delete
     * operation.
     */
    public String deleteDockingStation() {
        LOG.info("DockingStationController.deleteDockingStation has been invoked with dockingStation " + dockingStation.toString());

        dsSvc.deleteDockingStationWRTRelationships(dockingStation);

        String returnPage = sb.getReturnPage();
        sb.returnHighestPrivilege();
        return returnPage;
    }

    /**
     * Saves a new docking station.
     *
     * @return The navigation outcome based on the success of the save
     * operation.
     */
    public String saveDockingStation() {
        LOG.info("DockingStationController.saveDockingStation has been invoked with dockingStation " + dockingStation.toString());

        dsSvc.create(dockingStation);

        LOG.info("DockingStationController.saveDockingStation has been invoked EJV call " + dockingStation.toString());

        String returnPage = sb.getReturnPage();
        sb.returnHighestPrivilege();

        return returnPage;
    }
}
