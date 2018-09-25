package com.luv2code.jsf.jdbc;

import javax.faces.bean.ManagedBean;

@ManagedBean

public class Appointment {
	private int patientId;
	private int userId;
	private String appointmentStartDay;
	private String appointmentStartTime;
	private String appointmentEndTime;
	private String appointmentNotes;

	public Appointment() {

	}
	
	

	public Appointment(int patientId, int userId, String appointmentStartDay, String appointmentStartTime,
			String appointmentEndTime, String appointmentNotes) {
		super();
		this.patientId = patientId;
		this.userId = userId;
		this.appointmentStartDay = appointmentStartDay;
		this.appointmentStartTime = appointmentStartTime;
		this.appointmentEndTime = appointmentEndTime;
		this.appointmentNotes = appointmentNotes;
	}



	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getAppointmentStartDay() {
		return appointmentStartDay;
	}

	public void setAppointmentStartDay(String appointmentStartDay) {
		this.appointmentStartDay = appointmentStartDay;
	}

	public String getAppointmentStartTime() {
		return appointmentStartTime;
	}

	public void setAppointmentStartTime(String appointmentStartTime) {
		this.appointmentStartTime = appointmentStartTime;
	}

	public String getAppointmentEndTime() {
		return appointmentEndTime;
	}

	public void setAppointmentEndTime(String appointmentEndTime) {
		this.appointmentEndTime = appointmentEndTime;
	}

	public String getAppointmentNotes() {
		return appointmentNotes;
	}

	public void setAppointmentNotes(String appointmentNotes) {
		this.appointmentNotes = appointmentNotes;
	}

}
