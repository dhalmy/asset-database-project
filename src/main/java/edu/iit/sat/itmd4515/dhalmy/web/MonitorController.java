package edu.iit.sat.itmd4515.dhalmy.web;

import edu.iit.sat.itmd4515.dhalmy.domain.Monitor;
import edu.iit.sat.itmd4515.dhalmy.service.MonitorService;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.logging.Logger;

/**
 * JSF controller for managing monitor-related actions and views.
 */
@Named
@SessionScoped
public class MonitorController implements Serializable {

    private static final Logger LOG = Logger.getLogger(MonitorController.class.getName());
    private Monitor monitor;

    @EJB
    MonitorService monitorService;

    @Inject
    private SessionBean sb;

    /**
     * Default constructor for MonitorController.
     */
    public MonitorController() {
    }

    /**
     * Initialize the controller during post-construction.
     */
    @PostConstruct
    private void postConstruct() {
        LOG.info("Inside the MonitorController.postConstruct method");
        monitor = new Monitor();
    }

    /**
     * Perform a demo action with the monitor.
     *
     * @return The confirmation page.
     */
    public String demoAction() {
        LOG.info("MonitorController.demoAction has been invoked with monitor " + monitor.toString());
        return "confirmation.xhtml";
    }

    //MVC action methods
    /**
     * Display the read monitor page for a given monitor.
     *
     * @param m The monitor to display.
     * @return The read monitor page.
     */
    public String displayReadMonitorPage(Monitor m) {
        this.monitor = m;
        LOG.info("MonitorController.displayReadMonitorPage has been invoked with monitor" + monitor.toString());
        return "/read-entity/readMonitor.xhtml";
    }

    /**
     * Display the update monitor page for a given monitor.
     *
     * @param m The monitor to update.
     * @return The update monitor page.
     */
    public String displayUpdateMonitorPage(Monitor m) {
        this.monitor = m;
        LOG.info("MonitorController.displayUpdateMonitorPage has been invoked with monitor" + monitor.toString());
        return "/update-entity/updateMonitor.xhtml";
    }

    /**
     * Display the delete monitor page for a given monitor.
     *
     * @param m The monitor to delete.
     * @return The delete monitor page.
     */
    public String displayDeleteMonitorPage(Monitor m) {
        this.monitor = m;
        LOG.info("MonitorController.displayDeleteMonitorPage has been invoked with monitor" + monitor.toString());
        return "/delete-entity/deleteMonitor.xhtml";
    }

    /**
     * Get the current monitor object.
     *
     * @return The current monitor.
     */
    public Monitor getMonitor() {
        return monitor;
    }

    /**
     * Set the monitor object.
     *
     * @param monitor The monitor to set.
     */
    public void setMonitor(Monitor monitor) {
        this.monitor = monitor;
    }

    /**
     * Update the monitor and its relationships.
     *
     * @return The return page after updating.
     */
    public String updateMonitor() {
        LOG.info("MonitorController.updateMonitor has been invoked with monitor " + monitor.toString());
        monitorService.updateMonitorWRTRelationships(monitor);
        String returnPage = sb.getReturnPage();
        sb.returnHighestPrivilege();
        return returnPage;
    }

    /**
     * Delete the monitor and its relationships.
     *
     * @return The return page after deletion.
     */
    public String deleteMonitor() {
        LOG.info("MonitorController.deleteMonitor has been invoked with monitor " + monitor.toString());
        monitorService.deleteMonitorWRTRelationships(monitor);
        String returnPage = sb.getReturnPage();
        sb.returnHighestPrivilege();
        return returnPage;
    }

    /**
     * Save the monitor and create a new entry.
     *
     * @return The return page after saving.
     */
    public String saveMonitor() {
        LOG.info("MonitorController.saveMonitor has been invoked with monitor " + monitor.toString());
        monitorService.create(monitor);
        LOG.info("MonitorController.saveMonitor has been invoked EJV call " + monitor.toString());
        String returnPage = sb.getReturnPage();
        sb.returnHighestPrivilege();
        return returnPage;
    }
}
