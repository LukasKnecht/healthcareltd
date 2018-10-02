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

public class UserpwDbUtil {

	private static UserpwDbUtil instance;
	private DataSource dataSource;
	private String jndiName = "java:comp/env/jdbc/student_tracker";

	public static UserpwDbUtil getInstance() throws Exception {
		if (instance == null) {
			instance = new UserpwDbUtil();
		}

		return instance;
	}

	private UserpwDbUtil() throws Exception {
		dataSource = getDataSource();
	}

	private DataSource getDataSource() throws NamingException {
		Context context = new InitialContext();

		DataSource theDataSource = (DataSource) context.lookup(jndiName);

		return theDataSource;
	}

	public List<Userpw> getUserpws() throws Exception {

		List<Userpw> userpws = new ArrayList<>();

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			myConn = getConnection();

			String sql = "select * from userpw";

			myStmt = myConn.createStatement();

			myRs = myStmt.executeQuery(sql);

			// process result set
			while (myRs.next()) {

				// retrieve data from result set row
				String username = myRs.getString("Username");
				String password = myRs.getString("Password");

				// create new student object
				// Student tempStudent = new Student(id, firstName, lastName,email);
				Userpw tempUserpw = new Userpw(username, password);

				// add it to the list of students
				userpws.add(tempUserpw);
			}

			return userpws;
		} finally {
			close(myConn, myStmt, myRs);
		}
	}


	public int login(String username, String password) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			myConn = getConnection();

			String sql = "select Password from Userpw where Username=?";

			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setString(1, username);

			myRs = myStmt.executeQuery();

			// retrieve data from result set row
			if (myRs.next()) {
				if (myRs.getString(1).equals(password)) {
					return 1; // successful login.
				} else
					return 0; // when the password is wrong.
			}
			return -1; // when there is no such id in database, returns -1;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return -2; // when there is error in database.
	}
	

	private Connection getConnection() throws Exception {

		Connection theConn = dataSource.getConnection();

		return theConn;
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








	




