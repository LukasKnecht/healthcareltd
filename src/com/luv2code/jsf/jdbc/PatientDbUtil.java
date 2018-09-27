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

public class PatientDbUtil {

	private static PatientDbUtil instance;
	private DataSource dataSource;
	private String jndiName = "java:comp/env/jdbc/student_tracker";
	
	public static PatientDbUtil getInstance() throws Exception {
		if (instance == null) {
			instance = new PatientDbUtil();
		}
		
		return instance;
	}
	
	private PatientDbUtil() throws Exception {		
		dataSource = getDataSource();
	}

	private DataSource getDataSource() throws NamingException {
		Context context = new InitialContext();
		
		DataSource theDataSource = (DataSource) context.lookup(jndiName);
		
		return theDataSource;
	}
		
	public List<Patient> getPatients() throws Exception {

		List<Patient> patients = new ArrayList<>();

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myConn = getConnection();

			String sql = "select * from patient";

			myStmt = myConn.createStatement();

			myRs = myStmt.executeQuery(sql);

			// process result set
			while (myRs.next()) {
				
				// retrieve data from result set row
				int id = myRs.getInt("Patientid");
			    String title = myRs.getString("Title");
				String firstName = myRs.getString("Firstname");
				String lastName = myRs.getString("Lastname");
			    String mobilephonenumber = myRs.getString("Mobilephonenumber");
			    String homephonenumber = myRs.getString("Homephonenumber");
				String email = myRs.getString("Email");
				String gender = myRs.getString("Gender");
				String dateofbirth = myRs.getString("Dateofbirth");
				String streetname = myRs.getString("Streetname");
			    String streetnumber = myRs.getString("Streetnumber");
			    String suburb = myRs.getString("Suburb");
			    String postcode = myRs.getString("Postcode");
			    String city = myRs.getString("city");
			    String state = myRs.getString("State");

				// create new patient object
				//Patient tempPatient = new Patient(id, firstName, lastName,email);
			    Patient tempPatient = new Patient(id, title, firstName, lastName, mobilephonenumber, homephonenumber,email, gender, dateofbirth, streetname, streetnumber, suburb, postcode, city, state);

				// add it to the list of patients
				patients.add(tempPatient);
			}
			
			return patients;		
		}
		finally {
			close (myConn, myStmt, myRs);
		}
	}

	public void addPatient(Patient thePatient) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			myConn = getConnection();

			String sql = "insert into patient (Firstname, Lastname, Mobilephonenumber, Email, City) values (?, ?, ?, ?, ?)";

			myStmt = myConn.prepareStatement(sql);

			// set parameter
			myStmt.setString(1, thePatient.getFirstName());
			myStmt.setString(2, thePatient.getLastName());
			myStmt.setString(3, thePatient.getMobilephonenumber());
			myStmt.setString(4, thePatient.getEmail());
			myStmt.setString(5, thePatient.getCity());
			
			myStmt.execute();			
		}
		finally {
			close (myConn, myStmt);
		}
		
	}
	//Bug
	public Patient getPatient(int patientId) throws Exception {
	
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myConn = getConnection();

			String sql = "select * from patient where Patientid=?";

			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, patientId);
			
			myRs = myStmt.executeQuery();

			Patient thePatient = null;
			
			// retrieve data from result set row
			if (myRs.next()) {
				//int id = myRs.getInt("id");
				//String firstName = myRs.getString("first_name");
				//String lastName = myRs.getString("last_name");
				//String email = myRs.getString("email");
				
				int id = myRs.getInt("Patientid");
			    String title = myRs.getString("Title");
				String firstName = myRs.getString("Firstname");
				String lastName = myRs.getString("Lastname");
			    String mobilephonenumber = myRs.getString("Mobilephonenumber");
			    String homephonenumber = myRs.getString("Homephonenumber");
				String email = myRs.getString("Email");
				String gender = myRs.getString("Gender");
				String dateofbirth = myRs.getString("Dateofbirth");
				String streetname = myRs.getString("Streetname");
			    String streetnumber = myRs.getString("Streetnumber");
			    String suburb = myRs.getString("Suburb");
			    String postcode = myRs.getString("Postcode");
			    String city = myRs.getString("city");
			    String state = myRs.getString("State");

				thePatient = new Patient(id, title, firstName, lastName, mobilephonenumber, homephonenumber,email, gender, dateofbirth, streetname, streetnumber, suburb, postcode, city, state);
				
			}
			else {
				throw new Exception("Could not find patient id: " + patientId);
			}

			return thePatient;
		}
		finally {
			close (myConn, myStmt, myRs);
		}
	}
	
	public void updatePatient(Patient thePatient) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			myConn = getConnection();

			String sql = "update patient"
						+ " set Firstname=?, Lastname=?, Email=?"
						+ " where Patientid=?";

			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setString(1, thePatient.getFirstName());
			myStmt.setString(2, thePatient.getLastName());
			myStmt.setString(3, thePatient.getEmail());
			myStmt.setInt(4, thePatient.getId());
			
			myStmt.execute();
		}
		finally {
			close (myConn, myStmt);
		}
		
	}
	
	public void deletePatient(int patientId) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			myConn = getConnection();

			String sql = "delete from patient where Patientid=?";

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
