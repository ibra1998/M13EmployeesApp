package com.example.M13SimpleHttpService.bean;


import java.sql.*;
import java.util.ArrayList;

import com.example.M13SimpleHttpService.employee.Employee;

public class BaseDatos {
	
	private Connection conexion;
	
	public BaseDatos(){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String conex="jdbc:mysql://localhost:3306/empleados";
			this.conexion = DriverManager.getConnection(conex, "root", "");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Method used to insert employee in database
	public void insertarEmpleado(Employee employee) {
		String query= "  insert into empleados (id, name, job, salary" +" values(?,?,?,?)";
		try {
			PreparedStatement preparedStmt;
			preparedStmt = conexion.prepareStatement(query);
			preparedStmt.setInt(1, employee.getId());
			preparedStmt.setString(2, employee.getName());
			preparedStmt.setString(3, employee.getJob().toString());
			preparedStmt.setDouble(4, employee.getSalary());
			preparedStmt.executeUpdate();
		}catch (SQLException ex) {
			System.out.print(ex.getMessage());
		}
	}
	
	//Method to get an employee by its id
	public Employee getEmployee(int id) {
		Employee employee = null;
		try {
			Statement st = conexion.createStatement();
			String sentencia= " SELECT * FROM empleados where id = "+id;
			st.execute(sentencia);
			ResultSet rs=st.getResultSet();
			employee= new Employee(rs.getString(2), (rs.getNString(3)));
			employee.setId(rs.getInt(1));
		}catch (SQLException e) {
			System.out.print(e.getMessage());
		}
		return employee;
	}
	
	//To get all employees in database
	public ArrayList<Employee> getAllEmployees(){
		ArrayList<Employee> listEmployees = new ArrayList<Employee>();
		Employee employee;
		try {
			Statement st = conexion.createStatement();
			String sentencia = "SELECT * FROM empleados ";
			st.execute(sentencia);
			ResultSet rs=st.getResultSet();
			while(rs.next()) {
				employee= new Employee(rs.getString(2),(rs.getNString(3)));
				employee.setId(rs.getInt(1));
				listEmployees.add(employee);
			}
		}catch(SQLException e) {
			System.out.print(e.getMessage());
		}
		return listEmployees;
	}
	
	//To delete an employee from database
	public void deleteEmployee(int id) {
		try {
			Statement st = conexion.createStatement();
			String sentencia = "DELETE * FROM empleados where id=" + id;
			st.execute(sentencia);
		}catch(SQLException e) {
			System.out.print(e.getMessage());
		}	
	}
	
	//Function that changes employees data by id
	public void modifyEmployee(Employee employee) {
		String query= "  update empleados set name=?, job=?, salary=? " + " where id=?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setString(1, employee.getName());
			ps.setString(2, employee.getJob().toString());
			ps.setDouble(3, employee.getSalary());
			ps.setInt(4, employee.getId());
			ps.executeUpdate();
			
		}catch(SQLException e) {
			System.out.print(e.getMessage());
		}	
		
	}
	public ArrayList<Employee> getEmployeeByJob(String job) {
		ArrayList<Employee> listEmployees = new ArrayList<Employee>();
		Employee employee;
		try {
			Statement st = conexion.createStatement();
			String sentencia = "SELECT * FROM empleados where job='"+ job + "'";
			st.execute(sentencia);
			ResultSet rs=st.getResultSet();
			while(rs.next()) {
				employee= new Employee(rs.getString(2), (rs.getNString(3)));
				employee.setId(rs.getInt(1));
				listEmployees.add(employee);
			}
		}catch(SQLException e) {
			System.out.print(e.getMessage());
		}
		return listEmployees;
	}
	
	public ArrayList<Employee> getEmployeeByEnumJob(Employee.Job job) {
		ArrayList<Employee> listEmployees = new ArrayList<Employee>();
		Employee employee;
		try {
			Statement st = conexion.createStatement();
			String sentencia = "SELECT * FROM empleados where job='"+ job.toString() + "'";
			st.execute(sentencia);
			ResultSet rs=st.getResultSet();
			while(rs.next()) {
				employee= new Employee(rs.getString(2), (rs.getNString(3)));
				employee.setId(rs.getInt(1));
				listEmployees.add(employee);
			}
		}catch(SQLException e) {
			System.out.print(e.getMessage());
		}
		return listEmployees;
	}
	
	
	

}
