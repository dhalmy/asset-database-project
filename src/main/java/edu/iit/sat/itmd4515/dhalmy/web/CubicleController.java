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
 * JSF controller for managing cubicles in the application. This controller
 * handles actions related to cubicles, such as displaying, updating, and saving
 * cubicle information.
 *
 * @author David
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

    /**
     * Constructs a new instance of CubicleController.
     */
    public CubicleController() {
    }

    @PostConstruct
    private void postConstruct() {
        LOG.info("Inside the CubicleController.postConstruct method");
        cubicle = new Cubicle();
    }

    /**
     * A demo action for testing purposes.
     *
     * @return The navigation outcome to confirmation.xhtml.
     */
    public String demoAction() {
        LOG.info("CubicleController.demoAction has been invoked with cubicle " + cubicle.toString());
        return "confirmation.xhtml";
    }

    // MVC action methods
    /**
     * Displays the read page for a specific cubicle.
     *
     * @param c The cubicle to be displayed.
     * @return The navigation outcome to the readCubicle.xhtml page.
     */
    public String displayReadCubiclePage(Cubicle c) {
        this.cubicle = c;
        LOG.info("CubicleController.displayReadCubiclePage has been invoked with cubicle" + cubicle.toString());

        return "/read-entity/readCubicle.xhtml";
    }

    /**
     * Displays the update page for a specific cubicle.
     *
     * @param c The cubicle to be updated.
     * @return The navigation outcome to the updateCubicle.xhtml page.
     */
    public String displayUpdateCubiclePage(Cubicle c) {
        this.cubicle = c;
        LOG.info("CubicleController.displayUpdateCubiclePage has been invoked with cubicle" + cubicle.toString());

        return "/update-entity/updateCubicle.xhtml";
    }

    /**
     * Gets the current cubicle.
     *
     * @return The current cubicle.
     */
    public Cubicle getCubicle() {
        return cubicle;
    }

    /**
     * Sets the current cubicle.
     *
     * @param cubicle The cubicle to set.
     */
    public void setCubicle(Cubicle cubicle) {
        this.cubicle = cubicle;
    }

    /**
     * Updates the information of the current cubicle.
     *
     * @return The navigation outcome based on the success of the update.
     */
    public String updateCubicle() {
        LOG.info("CubicleController.updateCubicle has been invoked with cubicle " + cubicle.toString());

        cbSvc.updateCubicleWRTRelationships(cubicle);

        String returnPage = sb.getReturnPage();
        sb.returnHighestPrivilege();
        return returnPage;
    }

    /**
     * Saves a new cubicle.
     *
     * @return The navigation outcome based on the success of the save
     * operation.
     */
    public String saveCubicle() {
        LOG.info("CubicleController.saveCubicle has been invoked with cubicle " + cubicle.toString());

        cbSvc.create(cubicle);

        LOG.info("CubicleController.saveCubicle has been invoked EJV call " + cubicle.toString());

        String returnPage = sb.getReturnPage();
        sb.returnHighestPrivilege();

        return returnPage;
    }
}
