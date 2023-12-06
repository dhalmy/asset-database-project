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
 * JSF controller for -dockingStation.xhtml
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

    public DockingStationController() {
    }

    @PostConstruct
    private void postConstruct() {
        LOG.info("Inside the DockingStationController.postConstruct method");
        dockingStation = new DockingStation();
    }

    public String demoAction() {
        LOG.info("DockingStationController.demoAction has been invoked with dockingStation " + dockingStation.toString());
        return "confirmation.xhtml";
    }

    // MVC action methods
    public String displayReadDockingStationPage(DockingStation ds) {
        this.dockingStation = ds;
        LOG.info("DockingStationController.displayReadDockingStationPage has been invoked with dockingStation" + dockingStation.toString());

        return "/read-entity/readDockingStation.xhtml";
    }

    public String displayUpdateDockingStationPage(DockingStation ds) {
        this.dockingStation = ds;
        LOG.info("DockingStationController.displayUpdateDockingStationPage has been invoked with dockingStation" + dockingStation.toString());

        return "/update-entity/updateDockingStation.xhtml";
    }
    
    public String displayDeleteDockingStationPage(DockingStation ds){
        this.dockingStation = ds;
        LOG.info("DockingStationController.displayReadDockingStationPage has been invoked with dockingStation" + dockingStation.toString());
        
        return "/delete-entity/deleteDockingStation.xhtml";
    }


    public DockingStation getDockingStation() {
        return dockingStation;
    }

    public void setDockingStation(DockingStation dockingStation) {
        this.dockingStation = dockingStation;
    }

    public String updateDockingStation() {
        LOG.info("DockingStationController.updateDockingStation has been invoked with dockingStation " + dockingStation.toString());

        dsSvc.updateDockingStationWRTRelationships(dockingStation);

        String returnPage = sb.getReturnPage();
        sb.returnHighestPrivilege();
        return returnPage;
    }

    public String deleteDockingStation() {
        LOG.info("DockingStationController.deleteDockingStation has been invoked with dockingStation " + dockingStation.toString());

        dsSvc.deleteDockingStationWRTRelationships(dockingStation);

        String returnPage = sb.getReturnPage();
        sb.returnHighestPrivilege();
        return returnPage;
    }

    public String saveDockingStation() {
        LOG.info("DockingStationController.saveDockingStation has been invoked with dockingStation " + dockingStation.toString());

        dsSvc.create(dockingStation);

        LOG.info("DockingStationController.saveDockingStation has been invoked EJV call " + dockingStation.toString());

        String returnPage = sb.getReturnPage();
        sb.returnHighestPrivilege();

        return returnPage;
    }
}
