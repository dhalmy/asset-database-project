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
 * JSF controller for -monitor.xhtml
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
     *
     */
    public MonitorController() {
    }
    
    @PostConstruct
    private void postConstruct(){
        LOG.info("Inside the MonitorController.postConstruct method");
        monitor = new Monitor();
    }
    
    /**
     *
     * @return
     */
    public String demoAction(){
        LOG.info("MonitorController.demoAction has been invoked with monitor " + monitor.toString());
        return "confirmation.xhtml";
    }
    
    //MVC action methods

    /**
     *
     * @param m
     * @return
     */
    public String displayReadMonitorPage(Monitor m){
        this.monitor = m;
        LOG.info("MonitorController.displayReadMonitorPage has been invoked with monitor" + monitor.toString());
        return "/read-entity/readMonitor.xhtml";
    }
    
    /**
     *
     * @param m
     * @return
     */
    public String displayUpdateMonitorPage(Monitor m){
        this.monitor = m;
        LOG.info("MonitorController.displayUpdateMonitorPage has been invoked with monitor" + monitor.toString());
        return "/update-entity/updateMonitor.xhtml";
    }
    
    /**
     *
     * @param m
     * @return
     */
    public String displayDeleteMonitorPage(Monitor m){
        this.monitor = m;
        LOG.info("MonitorController.displayDeleteMonitorPage has been invoked with monitor" + monitor.toString());
        return "/delete-entity/deleteMonitor.xhtml";
    }
    
    /**
     *
     * @return
     */
    public Monitor getMonitor() {
        return monitor;
    }

    /**
     *
     * @param monitor
     */
    public void setMonitor(Monitor monitor) {
        this.monitor = monitor;
    }
    
    /**
     *
     * @return
     */
    public String updateMonitor(){
        LOG.info("MonitorController.updateMonitor has been invoked with monitor " + monitor.toString());
        monitorService.updateMonitorWRTRelationships(monitor);
        String returnPage = sb.getReturnPage();
        sb.returnHighestPrivilege();
        return returnPage;
    }
    
    /**
     *
     * @return
     */
    public String deleteMonitor(){
        LOG.info("MonitorController.deleteMonitor has been invoked with monitor " + monitor.toString());
        monitorService.deleteMonitorWRTRelationships(monitor);
        String returnPage = sb.getReturnPage();
        sb.returnHighestPrivilege();
        return returnPage;
    }
    
    /**
     *
     * @return
     */
    public String saveMonitor(){
        LOG.info("MonitorController.saveMonitor has been invoked with monitor " + monitor.toString());
        monitorService.create(monitor);
        LOG.info("MonitorController.saveMonitor has been invoked EJV call " + monitor.toString());
        String returnPage = sb.getReturnPage();
        sb.returnHighestPrivilege();
        return returnPage;
    }
}
