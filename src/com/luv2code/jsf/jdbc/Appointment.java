package com.luv2code.jsf.jdbc;

import javax.faces.bean.ManagedBean;

@ManagedBean

public class Appointment {
	private int appointmentId;
	private int patientId;
	private int userId;
	private String title;
	private String startDay;
	private String startTime;
	private String endTime;
	private String notes;

	public Appointment() {

	}
	
	

	public Appointment(int appointmentId, int patientId, int userId, String title, String startDay, String startTime,
			String endTime, String notes) {
		super();
		this.appointmentId = appointmentId;
		this.patientId = patientId;
		this.userId = userId;
		this.title = title;
		this.startDay = startDay;
		this.startTime = startTime;
		this.endTime = endTime;
		this.notes = notes;
	}



	public int getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStartDay() {
		return startDay;
	}

	public void setStartDay(String startDay) {
		this.startDay = startDay;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	

}
