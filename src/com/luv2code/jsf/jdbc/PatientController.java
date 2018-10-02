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
public class PatientController {

	private List<Patient> patients;
	private PatientDbUtil patientDbUtil;
	private Logger logger = Logger.getLogger(getClass().getName());
	
	public PatientController() throws Exception {
		patients = new ArrayList<>();
		
		patientDbUtil = PatientDbUtil.getInstance();
	}
	
	public List<Patient> getPatients() {
		return patients;
	}

	public void loadPatients() {

		logger.info("Loading patients");
		
		patients.clear();

		try {
			
			// get all patients from database
			patients = patientDbUtil.getPatients();
			
		} catch (Exception exc) {
			// send this to server logs
			logger.log(Level.SEVERE, "Error loading patients", exc);
			
			// add error message for JSF page
			addErrorMessage(exc);
		}
	}
		
	public String addPatient(Patient thePatient) {

		logger.info("Adding patient: " + thePatient);

		try {
			
			// add patient to the database
			patientDbUtil.addPatient(thePatient);
			
		} catch (Exception exc) {
			// send this to server logs
			logger.log(Level.SEVERE, "Error adding patients", exc);
			
			// add error message for JSF page
			addErrorMessage(exc);

			return null;
		}
		
		return "list-patients?faces-redirect=true";
	}

	public String loadPatient(int patientId) {
		
		logger.info("loading patient: " + patientId);
		
		try {
			// get patient from database
			Patient thePatient = patientDbUtil.getPatient(patientId);
			
			// put in the request attribute ... so we can use it on the form page
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();		

			Map<String, Object> requestMap = externalContext.getRequestMap();
			requestMap.put("patient", thePatient);	
			
		} catch (Exception exc) {
			// send this to server logs
			logger.log(Level.SEVERE, "Error loading patient id:" + patientId, exc);
			
			// add error message for JSF page
			addErrorMessage(exc);
			
			return null;
		}
				
		return "update-patient-form.xhtml";
	}	
	
	public String updatePatient(Patient thePatient) {

		logger.info("updating patient: " + thePatient);
		
		try {
			
			// update patient in the database
			patientDbUtil.updatePatient(thePatient);
			
		} catch (Exception exc) {
			// send this to server logs
			logger.log(Level.SEVERE, "Error updating patient: " + thePatient, exc);
			
			// add error message for JSF page
			addErrorMessage(exc);
			
			return null;
		}
		
		return "list-patients?faces-redirect=true";		
	}
	
	public String deletePatient(int patientId) {

		logger.info("Deleting patient id: " + patientId);
		
		try {

			// delete the patient from the database
			patientDbUtil.deletePatient(patientId);
			
		} catch (Exception exc) {
			// send this to server logs
			logger.log(Level.SEVERE, "Error deleting patient id: " + patientId, exc);
			
			// add error message for JSF page
			addErrorMessage(exc);
			
			return null;
		}
		
		return "list-patients";	
	}	
	
	private void addErrorMessage(Exception exc) {
		FacesMessage message = new FacesMessage("Error: " + exc.getMessage());
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
}
