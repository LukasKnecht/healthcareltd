package com.luv2code.jsf.jdbc;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Student {

    private int id;
    private String title;
	private String firstName;
	private String lastName;
    private String mobilephonenumber;
    private String homephonenumber;
	private String email;
	private String gender;
	private String dateofbirth;
	private String streetname;
    private String streetnumber;
    private String suburb;
    private String postcode;
    private String city;
    private String state;
	
	public Student() {
	}
	
	public Student(int id, String title, String firstName, String lastName, String mobilephonenumber, String homephonenumber, String email, String gender,String dateofbirth, String streetname, String streetnumber, String suburb, String postcode, String city, String state) {
		
		this.id = id;
		this.title = title;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobilephonenumber = mobilephonenumber;
		this.homephonenumber = homephonenumber;
		this.email = email;
		this.gender = gender;
		this.dateofbirth = dateofbirth;
		this.streetname = streetname;
		this.suburb = suburb;
		this.postcode = postcode;
		this.city = city; 
		this.state = state;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMobilephonenumber() {
		return mobilephonenumber;
	}

	public void setMobilephonenumber(String mobilephonenumber) {
		this.mobilephonenumber = mobilephonenumber;
	}

	public String getHomephonenumber() {
		return homephonenumber;
	}

	public void setHomephonenumber(String homephonenumber) {
		this.homephonenumber = homephonenumber;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDateofbirth() {
		return dateofbirth;
	}

	public void setDateofbirth(String dateofbirth) {
		this.dateofbirth = dateofbirth;
	}

	public String getStreetname() {
		return streetname;
	}

	public void setStreetname(String streetname) {
		this.streetname = streetname;
	}

	public String getStreetnumber() {
		return streetnumber;
	}

	public void setStreetnumber(String streetnumber) {
		this.streetnumber = streetnumber;
	}

	public String getSuburb() {
		return suburb;
	}

	public void setSuburb(String suburb) {
		this.suburb = suburb;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		//return "Student [id=" + id + ", firstName=" + firstName + ", lastName="+ lastName + ", email=" + email + "]";
		//return "Student[id=" + id + ", title=" + title + ", firstName=" + firstName +", lastName=" + lastName + ", mobilephonenumber=" + mobilephonenumber + ", "+ "homephonenumber=" + homephonenumber +", "+ "email=" + email +", "+ "gender=" + gender + ", "+ "streetname=" + streetname +", "+ "streetnumber=" + streetnumber + ","+ "suburb=" + suburb + ","+ "postcode=" + postcode +","+ "city=" + city + ","+ "state=" + state + ","]";
		return "Student[id=" + id + ", title=" + title + ", firstName=" + firstName +", lastName=" + lastName + ", mobilephonenumber=" + mobilephonenumber + ", "+ "homephonenumber=" + homephonenumber +", "+ "email=" + email +", "+ "gender=" + gender + "]";
	}

}
