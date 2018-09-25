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
				int patientId = myRs.getInt("Patientid");
			    int userId = myRs.getInt("Userid");
				String appointmentStartDay = myRs.getString("Appointmentstartday");
				String appointmentStartTime = myRs.getString("Appointmentstarttime");
			    String appointmentEndTime = myRs.getString("Appointmentendtime");
			    String appointmentNotes = myRs.getString("Appointmentnotes");

				// create new appointment object
				//Appointment tempAppointment = new Appointment(id, firstName, lastName,email);
			    Appointment tempAppointment = new Appointment(patientId, userId, appointmentStartDay, appointmentStartTime, appointmentEndTime, appointmentNotes);

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

			String sql = "insert into appointment (Appointmentstartday, Appointmentstarttime, Appointmentendtime, Appointmentnotes) values (?, ?, ?, ?)";

			myStmt = myConn.prepareStatement(sql);

			// set parameter
			myStmt.setString(1, theAppointment.getAppointmentStartDay());
			myStmt.setString(2, theAppointment.getAppointmentStartTime());
			myStmt.setString(3, theAppointment.getAppointmentEndTime());
			myStmt.setString(4, theAppointment.getAppointmentNotes());
			
			myStmt.execute();			
		}
		finally {
			close (myConn, myStmt);
		}
		
	}
	//Bug
	public Appointment getAppointment(int patientId) throws Exception {
	
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myConn = getConnection();

			String sql = "select * from appointment where Patientid=?";

			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, patientId);
			
			myRs = myStmt.executeQuery();

			Appointment theAppointment = null;
			
			// retrieve data from result set row
			if (myRs.next()) {
				//int id = myRs.getInt("id");
				//String firstName = myRs.getString("first_name");
				//String lastName = myRs.getString("last_name");
				//String email = myRs.getString("email");
				
				//int patientId = myRs.getInt("Patientid");
			    int userId = myRs.getInt("Userid");
				String appointmentStartDay = myRs.getString("Appointmentstartday");
				String appointmentStartTime = myRs.getString("Appointmentstarttime");
			    String appointmentEndTime = myRs.getString("Appointmentendtime");
			    String appointmentNotes = myRs.getString("Appointmentnotes");


				theAppointment = new Appointment(patientId, userId, appointmentStartDay, appointmentStartTime, appointmentEndTime, appointmentNotes);
				
			}
			else {
				throw new Exception("Could not find an appointment with the patient id: " + patientId);
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

			String sql = "update appointment "
						+ " set first_name=?, last_name=?, email=?"
						+ " where id=?";

			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setString(1, theAppointment.getAppointmentStartDay());
			myStmt.setString(2, theAppointment.getAppointmentStartTime());
			myStmt.setString(3, theAppointment.getAppointmentEndTime());
			myStmt.setString(4, theAppointment.getAppointmentNotes());
			
			myStmt.execute();
		}
		finally {
			close (myConn, myStmt);
		}
		
	}
	
	public void deleteAppointment(int patientId) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			myConn = getConnection();

			String sql = "delete from appointment where Patientid=?";

			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setInt(1, patientId);
			
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
