package com.luv2code.jsf.jdbc;


import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.swing.JOptionPane;

import com.sun.javafx.event.RedirectedEvent;





@ManagedBean
@SessionScoped


public class UserpwController {
	private List<Userpw> userpws;
	private UserpwDbUtil userpwDbUtil;
	private Logger logger = Logger.getLogger(getClass().getName());
	
	public UserpwController() throws Exception {
		userpws = new ArrayList<>();
		
		userpwDbUtil = UserpwDbUtil.getInstance();
	}
	public List<Userpw> getUserpws() {
		return userpws;
	}
	
	
	
	
	public String loginAction(Userpw theuserpw) throws Exception {

		logger.info("Login");
		 
		
		int result = userpwDbUtil.login(theuserpw.getUsername(), theuserpw.getPassword());
        FacesContext context = FacesContext.getCurrentInstance();
        if (result == 1 ){
        	System.out.println("location.href = 'index.xthml'");
        	return "index.xhtml";
        
        } else if (result == 0 ){
        	     
        	context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Entered Wrong password", "Entered Wrong password"));;
            
             return "login";
        
        } else if (result == -1 ){
        	
        	context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "There is no such Username", "There is no such Username"));;
            
        	System.out.println("alert('There is no such username')");
        
        } else if (result == -2 ){
        	context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "There is error in database!", "There is error in database"));;
            
         	System.out.println("alert('There is error in database')");
	}
		return null;
		
	}
	
	
}
	
