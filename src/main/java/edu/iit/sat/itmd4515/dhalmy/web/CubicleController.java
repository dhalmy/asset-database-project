package edu.iit.sat.itmd4515.dhalmy.web;

import edu.iit.sat.itmd4515.dhalmy.domain.Cubicle;
import edu.iit.sat.itmd4515.dhalmy.service.CubicleService;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.logging.Logger;

/**
 * JSF controller for -cubicle.xhtml
 */
@Named
@SessionScoped
public class CubicleController implements Serializable {

    private static final Logger LOG = Logger.getLogger(CubicleController.class.getName());
    private Cubicle cubicle;

    @EJB 
    CubicleService cbSvc;

    @Inject
    private SessionBean sb;

    public CubicleController() {
    }

    @PostConstruct
    private void postConstruct() {
        LOG.info("Inside the CubicleController.postConstruct method");
        cubicle = new Cubicle();
    }

    public String demoAction() {
        LOG.info("CubicleController.demoAction has been invoked with cubicle " + cubicle.toString());
        return "confirmation.xhtml";
    }

    // MVC action methods
    public String displayReadCubiclePage(Cubicle c) {
        this.cubicle = c;
        LOG.info("CubicleController.displayReadCubiclePage has been invoked with cubicle" + cubicle.toString());

        return "/read-entity/readCubicle.xhtml";
    }

    public String displayUpdateCubiclePage(Cubicle c) {
        this.cubicle = c;
        LOG.info("CubicleController.displayUpdateCubiclePage has been invoked with cubicle" + cubicle.toString());

        return "/update-entity/updateCubicle.xhtml";
    }


    public Cubicle getCubicle() {
        return cubicle;
    }

    public void setCubicle(Cubicle cubicle) {
        this.cubicle = cubicle;
    }

    public String updateCubicle() {
        LOG.info("CubicleController.updateCubicle has been invoked with cubicle " + cubicle.toString());

        cbSvc.updateCubicleWRTRelationships(cubicle);

        String returnPage = sb.getReturnPage();
        sb.returnHighestPrivilege();
        return returnPage;
    }

//    Cubicles are PERMANENT. no deleting office space
//    public String deleteCubicle() {
//        LOG.info("CubicleController.deleteCubicle has been invoked with cubicle " + cubicle.toString());
//
//        cbSvc.deleteCubicleWRTRelationships(cubicle);
//
//        String returnPage = sb.getReturnPage();
//        sb.returnHighestPrivilege();
//        return returnPage;
//    }

    public String saveCubicle() {
        LOG.info("CubicleController.saveCubicle has been invoked with cubicle " + cubicle.toString());

        cbSvc.create(cubicle);

        LOG.info("CubicleController.saveCubicle has been invoked EJV call " + cubicle.toString());

        String returnPage = sb.getReturnPage();
        sb.returnHighestPrivilege();

        return returnPage;
    }
}
