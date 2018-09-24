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

public class StudentDbUtil {

	private static StudentDbUtil instance;
	private DataSource dataSource;
	private String jndiName = "java:comp/env/jdbc/student_tracker";
	
	public static StudentDbUtil getInstance() throws Exception {
		if (instance == null) {
			instance = new StudentDbUtil();
		}
		
		return instance;
	}
	
	private StudentDbUtil() throws Exception {		
		dataSource = getDataSource();
	}

	private DataSource getDataSource() throws NamingException {
		Context context = new InitialContext();
		
		DataSource theDataSource = (DataSource) context.lookup(jndiName);
		
		return theDataSource;
	}
		
	public List<Student> getStudents() throws Exception {

		List<Student> students = new ArrayList<>();

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myConn = getConnection();

			String sql = "select * from patient2";

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

				// create new student object
				//Student tempStudent = new Student(id, firstName, lastName,email);
			    Student tempStudent = new Student(id, title, firstName, lastName, mobilephonenumber, homephonenumber,email, gender, dateofbirth, streetname, streetnumber, suburb, postcode, city, state);

				// add it to the list of students
				students.add(tempStudent);
			}
			
			return students;		
		}
		finally {
			close (myConn, myStmt, myRs);
		}
	}

	public void addStudent(Student theStudent) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			myConn = getConnection();

			String sql = "insert into patient2 (Firstname, Lastname, Mobilephonenumber, Email, City) values (?, ?, ?, ?, ?)";

			myStmt = myConn.prepareStatement(sql);

			// set parameter
			myStmt.setString(1, theStudent.getFirstName());
			myStmt.setString(2, theStudent.getLastName());
			myStmt.setString(3, theStudent.getMobilephonenumber());
			myStmt.setString(4, theStudent.getEmail());
			myStmt.setString(5, theStudent.getCity());
			
			myStmt.execute();			
		}
		finally {
			close (myConn, myStmt);
		}
		
	}
	
	public Student getStudent(int studentId) throws Exception {
	
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myConn = getConnection();

			String sql = "select * from patient2 where Patientid=?";

			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, studentId);
			
			myRs = myStmt.executeQuery();

			Student theStudent = null;
			
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

				theStudent = new Student(id, title, firstName, lastName, mobilephonenumber, homephonenumber,email, gender, dateofbirth, streetname, streetnumber, suburb, postcode, city, state);
				
			}
			else {
				throw new Exception("Could not find student id: " + studentId);
			}

			return theStudent;
		}
		finally {
			close (myConn, myStmt, myRs);
		}
	}
	
	public void updateStudent(Student theStudent) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			myConn = getConnection();

			String sql = "update patient2 "
						+ " set first_name=?, last_name=?, email=?"
						+ " where id=?";

			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setString(1, theStudent.getFirstName());
			myStmt.setString(2, theStudent.getLastName());
			myStmt.setString(3, theStudent.getEmail());
			myStmt.setInt(4, theStudent.getId());
			
			myStmt.execute();
		}
		finally {
			close (myConn, myStmt);
		}
		
	}
	
	public void deleteStudent(int studentId) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			myConn = getConnection();

			String sql = "delete from patient2 where id=?";

			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setInt(1, studentId);
			
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
