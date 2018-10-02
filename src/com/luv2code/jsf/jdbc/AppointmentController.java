package com.luv2code.jsf.jdbc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;


@ManagedBean
@SessionScoped

public class AppointmentController {
	private List<Appointment> appointments;
	private List<Appointment> searchResult;
	private AppointmentDbUtil appointmentDbUtil;
	private Logger logger = Logger.getLogger(getClass().getName());
	private String userInput;
	
	public AppointmentController() throws Exception {
		appointments = new ArrayList<>();
		
		appointmentDbUtil = AppointmentDbUtil.getInstance();
		searchResult = new ArrayList<>();
	}
	
	public String getUserInput() {return userInput; }
	public void setUserInput(String userInput) {this.userInput = userInput;}
	
	public List<Appointment> getSearchResult(){return searchResult;}
	
	public List<Appointment> getAppointments() {
		return appointments;
	}
	

	public void loadAppointments() {

		logger.info("Loading appointments");
		
		appointments.clear();

		try {
			
			// get all appointments from database
			appointments = appointmentDbUtil.getAppointments();
			
		} catch (Exception exc) {
			// send this to server logs
			logger.log(Level.SEVERE, "Error loading appointments", exc);
			
			// add error message for JSF page
			addErrorMessage(exc);
		}
	}
		
	public String addAppointment(Appointment theAppointment) {

		logger.info("Adding appointment: " + theAppointment);

		try {
			
			// add appointment to the database
			appointmentDbUtil.addAppointment(theAppointment);
			
		} catch (Exception exc) {
			// send this to server logs
			logger.log(Level.SEVERE, "Error adding appointments", exc);
			
			// add error message for JSF page
			addErrorMessage(exc);

			return null;
		}
		
		return "list-appointments?faces-redirect=true";
	}

	public String loadAppointment(int appointmentId) {
		
		logger.info("loading appointment: " + appointmentId);
		
		try {
			// get appointment from database
			Appointment theAppointment = appointmentDbUtil.getAppointment(appointmentId);
			searchResult.add(theAppointment);
			// put in the request attribute ... so we can use it on the form page
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();		

			Map<String, Object> requestMap = externalContext.getRequestMap();
			requestMap.put("appointment", theAppointment);	
			
		} catch (Exception exc) {
			// send this to server logs
			logger.log(Level.SEVERE, "Error loading appointment id:" + appointmentId, exc);
			
			// add error message for JSF page
			addErrorMessage(exc);
			
			return null;
		}
				
		return "update-appointment-form.xhtml";
	}	
	
	public String updateAppointment(Appointment theAppointment) {

		logger.info("updating appointment: " + theAppointment);
		
		try {
			
			// update appointment in the database
			appointmentDbUtil.updateAppointment(theAppointment);
			
		} catch (Exception exc) {
			// send this to server logs
			logger.log(Level.SEVERE, "Error updating appointment: " + theAppointment, exc);
			
			// add error message for JSF page
			addErrorMessage(exc);
			
			return null;
		}
		
		return "list-appointments?faces-redirect=true";		
	}
	
	public String deleteAppointment(int appointmentId) {

		logger.info("Deleting appointment id: " + appointmentId);
		
		try {

			// delete the appointment from the database
			appointmentDbUtil.deleteAppointment(appointmentId);
			
		} catch (Exception exc) {
			// send this to server logs
			logger.log(Level.SEVERE, "Error deleting appointment id: " + appointmentId, exc);
			
			// add error message for JSF page
			addErrorMessage(exc);
			
			return null;
		}
		
		return "list-appointments";	
	}	
	
	private void addErrorMessage(Exception exc) {
		FacesMessage message = new FacesMessage("Error: " + exc.getMessage());
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	/*public List<Appointment> searchAppointments(String input){
		userInput = input; 
		//Search for appointment from user input
		List<Appointment> searchResult = new ArrayList<Appointment>();
		for(int i = 0; i < searchResult.size(); i++) {
			if(searchResult.get(i).getTitle() == input) {
				searchResult.add(appointments.get(i)); 
				//displayAppointment(i);
				//loadAppointment(i); 
			}
		}
		loadAppointment(i);
		return searchResult;	
	}
	
	*/
	
	public String searchAppointments() {
		try {
			if(!appointments.isEmpty()) {
				
				for(int i = 0; i < appointments.size(); i++) {
					if (appointments.get(i).getTitle().equals(userInput)) {
						loadAppointment(appointments.get(i).getAppointmentId());
						System.out.println("Found");}
					
				}
			}
			else { System.out.println("Not found");}
		} catch (Exception exc) {
			// send this to server logs
			logger.log(Level.SEVERE, "Error finding appointment id:" + exc);
			
			// add error message for JSF page
			addErrorMessage(exc);
			
			return null;
		}
		return "list-results";
	}
	
	
	
}
