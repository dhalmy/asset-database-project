/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.dhalmy.web;

import edu.iit.sat.itmd4515.dhalmy.model.City;
import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

/**
 *
 * @author David
 */
@WebServlet(urlPatterns = {"/cit","/c","/city"})
public class CityServlet extends HttpServlet {
    
    @Resource
    Validator validator;
    
    @Resource(name = "java:app/jdbc/itmd4515DS")
    DataSource ds;

    private static final Logger LOG = Logger.getLogger(CityServlet.class.getName());
    
    

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.fine("This is a FINE message");
        LOG.info("This is an INFO message");
        LOG.severe("This is a SEVERE message");
        
        LOG.info("CityServlet.doPost - Getting parameters of form in my servlet");
        
        String cityIDParameter = req.getParameter("cityID");
        String cityNameParameter = req.getParameter("cityName");
        String cityDistrictParameter = req.getParameter("district");
        String cityPopulationParameter = req.getParameter("population");
        
        LOG.info("cityIDParameter\t" + cityIDParameter);
        LOG.info("cityNameParameter\t" + cityNameParameter);
        LOG.info("cityDistrictParameter\t" + cityDistrictParameter);
        LOG.info("cityPopulationParameter\t" + cityPopulationParameter);
        
        
//        Changed cityID and Population from Integer to String in order to apply pattern constraint to prevent letters from being entered where numbers belong.
//        Integer cityID = null;
//        if(cityIDParameter != null && !cityIDParameter.isBlank()){
//            cityID = Integer.valueOf(cityIDParameter);
//        }
//        Integer population = null;
//        if(cityIDParameter != null && !cityIDParameter.isBlank()){
//            population = Integer.valueOf(cityPopulationParameter);
//        }
        
        City c = new City(cityIDParameter, cityNameParameter, "USA", cityDistrictParameter, cityPopulationParameter);
        
        LOG.info(c.toString());
        
        Set<ConstraintViolation<City>> violations = validator.validate(c);
        
        if(violations.size() > 0){
            //if there are violations, the city failed validation
            for (ConstraintViolation<City> v : violations) {
                LOG.info(v.toString());
                LOG.config("test here");
            }
            
            req.setAttribute("violations", violations);
            req.setAttribute("city", c);
            RequestDispatcher rd = req.getRequestDispatcher("city.jsp");
            rd.forward(req, resp);
        } else{
            createACity(c);
            //user passed validation
            req.setAttribute("city", c);
            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/confirmation.jsp");
            rd.forward(req, resp);
        } 
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.info("CityServlet.doGet - Redirecting user to Create A City page"); 
        
        resp.sendRedirect(req.getContextPath() + "/city.jsp");
    }
    
    private void createACity(City c) {
        String insertCity = "insert into city "
                + "(id,`name`,countrycode,district,population) "
                + "values (?,?,?,?,?)";
        
        try( Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(insertCity)){
            ps.setInt(1, Integer.parseInt(c.getID()));
            ps.setString(2,c.getName());
            ps.setString(3,c.getCountryCode());
            ps.setString(4,c.getDistrict());
            ps.setInt(5,Integer.parseInt(c.getPopulation()));
            
            ps.executeUpdate();
        } catch (SQLException ex){
             LOG.log(Level.SEVERE,null,ex);
        }    
        
    }
    
}
