package com.digital.entity;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name="employeeTable")
@JsonPOJOBuilder
public class Employee {
	
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;


	private String employeeName;


	private String role;


	private double salary;


	private String qualification;


	private String dateOfJoin;


	private String email;


	private String address;


	@Lob
	@Column(length = 1000000)
	private byte[] photo;  // Change type from String to byte[]





	//constructor using fields
	public Employee(long id, String employeeName, String role, double salary, String qualification,
			String dateOfJoin, String email, String address, byte[] photo) {
		super();
		this.id = id;
		this.employeeName = employeeName;
		this.role = role;
		this.salary = salary;
		this.qualification = qualification;
		this.dateOfJoin = dateOfJoin;
		this.email = email;
		this.address = address;
		this.photo = photo;
	}



	//Constructor from super class
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}




	//generate getter and setter method



	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public String getEmployeeName() {
		return employeeName;
	}



	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}



	public String getRole() {
		return role;
	}



	public void setRole(String role) {
		this.role = role;
	}



	public double getSalary() {
		return salary;
	}



	public void setSalary(double salary) {
		this.salary = salary;
	}



	public String getQualification() {
		return qualification;
	}



	public void setQualification(String qualification) {
		this.qualification = qualification;
	}



	public String getDateOfJoin() {
		return dateOfJoin;
	}



	public void setDateOfJoin(String dateOfJoin) {
		this.dateOfJoin = dateOfJoin;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	// Getters and setters for photo
	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}



	//to string method
	@Override
	public String toString() {
		return "Employee [id=" + id + ", employeeName=" + employeeName + ", role=" + role + ", salary=" + salary
				+ ", qualification=" + qualification + ", dateOfJoin=" + dateOfJoin + ", email=" + email
				+ ", address=" + address + ", photo=" + photo + "]";
	}
	
	
	
	

}