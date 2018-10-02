package com.luv2code.jsf.jdbc;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class AppointmentDbUtil {
	private static AppointmentDbUtil instance;
	private DataSource dataSource;
	private String jndiName = "java:comp/env/jdbc/student_tracker";

	public static AppointmentDbUtil getInstance() throws Exception {
		if (instance == null) {
			instance = new AppointmentDbUtil();
		}
		
		return instance;
	}
	
	private AppointmentDbUtil() throws Exception {		
		dataSource = getDataSource();
	}

	private DataSource getDataSource() throws NamingException {
		Context context = new InitialContext();
		
		DataSource theDataSource = (DataSource) context.lookup(jndiName);
		
		return theDataSource;
	}
	
	public List<Appointment> getAppointments() throws Exception {

		List<Appointment> appointments = new ArrayList<>();

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myConn = getConnection();

			String sql = "select * from appointment";

			myStmt = myConn.createStatement();

			myRs = myStmt.executeQuery(sql);

			// process result set
			while (myRs.next()) {
				
				// retrieve data from result set row
				int appointmentId = myRs.getInt("Appointmentid");
				int patientId = myRs.getInt("Patientid");
			    int userId = myRs.getInt("Userid");
			    String title = myRs.getString("Appointmenttitle");
				String startDay = myRs.getString("Appointmentstartday");
				String startTime = myRs.getString("Appointmentstarttime");
			    String endTime = myRs.getString("Appointmentendtime");
			    String notes = myRs.getString("Appointmentnotes");

				// create new appointment object
				//Appointment tempAppointment = new Appointment(id, firstName, lastName,email);
			    Appointment tempAppointment = new Appointment(appointmentId, patientId, userId, title, startDay, startTime, endTime, notes);

				// add it to the list of appointments
				appointments.add(tempAppointment);
			}
			
			return appointments;		
		}
		finally {
			close (myConn, myStmt, myRs);
		}
	}
	
	public void addAppointment(Appointment theAppointment) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			myConn = getConnection();

			String sql = "insert into appointment (Appointmenttitle, Appointmentstartday, Appointmentstarttime, Appointmentendtime, Appointmentnotes) values (?, ?, ?, ?, ?)";

			myStmt = myConn.prepareStatement(sql);

			// set parameter
			myStmt.setString(1, theAppointment.getTitle());
			myStmt.setString(2, theAppointment.getStartDay());
			myStmt.setString(3, theAppointment.getStartTime());
			myStmt.setString(4, theAppointment.getEndTime());
			myStmt.setString(5, theAppointment.getNotes());
			
			myStmt.execute();			
		}
		finally {
			close (myConn, myStmt);
		}
		
	}
	//Bug
	public Appointment getAppointment(int appointmentId1) throws Exception {
	
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myConn = getConnection();

			String sql = "select * from appointment where Appointmentid=?";

			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, appointmentId1);
			
			myRs = myStmt.executeQuery();

			Appointment theAppointment = null;
			
			
			// retrieve data from result set row
			if (myRs.next()) {
				
				int appointmentId = myRs.getInt("Appointmentid");
				int patientId = myRs.getInt("Patientid");
			    int userId = myRs.getInt("Userid");
			    String title = myRs.getString("Appointmenttitle");
				String startDay = myRs.getString("Appointmentstartday");
				String startTime = myRs.getString("Appointmentstarttime");
			    String endTime = myRs.getString("Appointmentendtime");
			    String notes = myRs.getString("Appointmentnotes");


				theAppointment = new Appointment(appointmentId, patientId, userId, title, startDay, startTime, endTime, notes);
				
			}
			else {
				throw new Exception("Could not find an appointment with appointment ID: " + appointmentId1);
			}

			return theAppointment;
		}
		finally {
			close (myConn, myStmt, myRs);
		}
	}
	
	public void updateAppointment(Appointment theAppointment) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			myConn = getConnection();

			String sql = "update appointment"
						+ " set Appointmenttitle=?, Appointmentstartday=?, Appointmentstarttime=?, Appointmentendtime=?, Appointmentnotes=?"
						+ " where Appointmentid=?";

			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setString(1, theAppointment.getTitle());
			myStmt.setString(2, theAppointment.getStartDay());
			myStmt.setString(3, theAppointment.getStartTime());
			myStmt.setString(4, theAppointment.getEndTime());
			myStmt.setString(5, theAppointment.getNotes());
			myStmt.setInt(6,  theAppointment.getAppointmentId());
			myStmt.execute();
		}
		finally {
			close (myConn, myStmt);
		}
		
	}
	
	public void deleteAppointment(int appointmentId) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			myConn = getConnection();

			String sql = "delete from appointment where Appointmentid=?";

			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setInt(1, appointmentId);
			
			myStmt.execute();
		}
		finally {
			close (myConn, myStmt);
		}		
	}	
	
	private Connection getConnection() throws Exception {

		Connection theConn = dataSource.getConnection();
		
		return theConn;
	}
	
	private void close(Connection theConn, Statement theStmt) {
		close(theConn, theStmt, null);
	}
	
	private void close(Connection theConn, Statement theStmt, ResultSet theRs) {

		try {
			if (theRs != null) {
				theRs.close();
			}

			if (theStmt != null) {
				theStmt.close();
			}

			if (theConn != null) {
				theConn.close();
			}
			
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}
	
	
	
	
	
}
