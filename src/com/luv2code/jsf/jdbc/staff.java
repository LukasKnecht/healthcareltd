package com.luv2code.jsf.jdbc;

public class staff {
	private String Username;
	private String Firstname;
	private String Lastname;
	private String Mobilephonenumber;
	private String Homephonenumber;
	private String Email;
	private String Gender;
	private String Dateofbirth;
	private String Streetnumber;
	private String Streetname;
	private String Suburb;
	private String Postcode;
	private String City;
	private String State;
	private String Usertype;
	
	
	public staff(String username, String firstname, String lastname, String mobilephonenumber, String homephonenumber,
			String email, String gender, String dateofbirth, String streetnumber, String streetname, String suburb,
			String postcode, String city, String state, String usertype) {
		;
		this.Username = username;
		this.Firstname = firstname;
		this.Lastname = lastname;
		this.Mobilephonenumber = mobilephonenumber;
		this.Homephonenumber = homephonenumber;
		this.Email = email;
		this.Gender = gender;
		this.Dateofbirth = dateofbirth;
		this.Streetnumber = streetnumber;
		this.Streetname = streetname;
		this.Suburb = suburb;
		this.Postcode = postcode;
		this.City = city;
		this.State = state;
		this.Usertype = usertype;
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getFirstname() {
		return Firstname;
	}
	public void setFirstname(String firstname) {
		Firstname = firstname;
	}
	public String getLastname() {
		return Lastname;
	}
	public void setLastname(String lastname) {
		Lastname = lastname;
	}
	public String getMobilephonenumber() {
		return Mobilephonenumber;
	}
	public void setMobilephonenumber(String mobilephonenumber) {
		Mobilephonenumber = mobilephonenumber;
	}
	public String getHomephonenumber() {
		return Homephonenumber;
	}
	public void setHomephonenumber(String homephonenumber) {
		Homephonenumber = homephonenumber;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getGender() {
		return Gender;
	}
	public void setGender(String gender) {
		Gender = gender;
	}
	public String getDateofbirth() {
		return Dateofbirth;
	}
	public void setDateofbirth(String dateofbirth) {
		Dateofbirth = dateofbirth;
	}
	public String getStreetnumber() {
		return Streetnumber;
	}
	public void setStreetnumber(String streetnumber) {
		Streetnumber = streetnumber;
	}
	public String getStreetname() {
		return Streetname;
	}
	public void setStreetname(String streetname) {
		Streetname = streetname;
	}
	public String getSuburb() {
		return Suburb;
	}
	public void setSuburb(String suburb) {
		Suburb = suburb;
	}
	public String getPostcode() {
		return Postcode;
	}
	public void setPostcode(String postcode) {
		Postcode = postcode;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	public String getUsertype() {
		return Usertype;
	}
	public void setUsertype(String usertype) {
		Usertype = usertype;
	}
	

	
}
