package com.example.M13SimpleHttpService.bean;
import java.util.ArrayList;

import com.example.M13SimpleHttpService.employee.*;

public class BaseDatos2 {
	private ArrayList<Employee> employeesList;
	public BaseDatos2() {
		this.employeesList = new ArrayList<Employee>();
	}
	
	public void insertaEmpleado(Employee employee) {
		this.employeesList.add(employee);
	}
	public Employee obtenerEmpleado(int id) {
		Employee foundEmployee = null;
		for (Employee employee : this.employeesList) {
			if (employee.getId() == id) {
				foundEmployee = employee;
			}
		}
		return foundEmployee;
	}

	public ArrayList<Employee> getEmployeesList() {
		return employeesList;
	}

	public void setEmployeesList(ArrayList<Employee> employeesList) {
		this.employeesList = employeesList;
	}
	
	public void modificarEmpleado(Employee modifiedEmployee) {
		Employee foundEmployee = null;
		for (Employee employee : this.employeesList) {
			if (employee.getId() == modifiedEmployee.getId()) {
				this.employeesList.remove(employee.getId());
				this.employeesList.add(modifiedEmployee);
			}
		}
	}
	public void deleteEmployee(int id) {
		for (Employee employee : this.employeesList) {
			if (employee.getId() == id) {
				this.employeesList.remove(id);
			}
		}
	}
	
	public ArrayList<Employee> getEmployeeByJob(Employee.Job job) {
		ArrayList<Employee> foundEmployees = new ArrayList<Employee>();
		for (Employee employee : this.employeesList) {
			if (employee.getJob() == job) {
				foundEmployees.add(employee);
			}
		}
		return foundEmployees;
	}

	
}
