/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.dhalmy.web;

import jakarta.faces.event.PhaseEvent;
import jakarta.faces.event.PhaseId;
import jakarta.faces.event.PhaseListener;
import java.util.logging.Logger;

/**
 * This class implements a JSF PhaseListener to log JSF lifecycle phases.
 * Author: David
 */
public class JSFPhaseListenener implements PhaseListener {

    private static final Logger LOG = Logger.getLogger(JSFPhaseListenener.class.getName());

    /**
     * Get the current phase ID.
     *
     * @return The PhaseId representing the current phase.
     */
    @Override
    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }

    /**
     * Method executed before the start of a JSF phase.
     *
     * @param event The PhaseEvent object containing phase information.
     */
    @Override
    public void beforePhase(PhaseEvent event) {
        if (event.getPhaseId() == PhaseId.RESTORE_VIEW) {
            LOG.info("############################# NEW JSF REQUEST #############################");
        }

        LOG.info("BEFORE the JSF Phase ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ >>>>>> " + event.getPhaseId());
    }

    /**
     * Method executed after the completion of a JSF phase.
     *
     * @param event The PhaseEvent object containing phase information.
     */
    @Override
    public void afterPhase(PhaseEvent event) {
        LOG.info("AFTER the JSF Phase ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ >>>>>> " + event.getPhaseId());

        if (event.getPhaseId() == PhaseId.RENDER_RESPONSE) {
            LOG.info("############################# JSF REQUEST COMPLETED #############################");
        }
    }
}
