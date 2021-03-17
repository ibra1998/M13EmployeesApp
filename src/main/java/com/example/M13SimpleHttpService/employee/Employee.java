package com.example.M13SimpleHttpService.employee;



import java.util.HashMap;

public class Employee {
	
	private String name;
	private static int assignmentId;//auxiliary variable to assign ids to workers
	private int id;//Identifier that autoincrements and different for each variable
	private String stringJob;
	
	public static enum Job{//Variable to assign different jobs in the company
		developper, rrhh, director, secreterian
	}
	
	//Array linking different jobs and salaries
	private static HashMap<Job, Double> jobsSalaries = new HashMap<Job, Double>();
	private Double salary;
	private Job job;
	//Constructor
	public Employee(String name, String job) {
		Employee.assignDefaultSalariesToJob();
		this.stringJob = job;
		this.name = name;
		this.id = assignmentId;
		this.job = Employee.convertStringToEnumJob(job);
		assignmentId++;
		this.assignSalaryToWorker(this.job);
		
	}
	
	public Job getJob() {
		return this.job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	//Bassic getters and setters
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	
	//Method that assigns a salary dependign on the job
	public void assignSalaryToWorker(Employee.Job thisjob) {
		this.salary = Employee.jobsSalaries.get(thisjob);
	}
	
	//Method that assigns salaries for each job
	public static void assignSalaryToJob(Job job, Double salary) {
		jobsSalaries.put(job, salary);
	}
	
	
	//Method that assign the job category depending on the String recieved
	public static Job convertStringToEnumJob(String stringJob) {
		Job EnumJob = null;
		for(Job optionalJob: Job.values()){
			if(optionalJob.toString().equalsIgnoreCase(stringJob)) {
				EnumJob =optionalJob;
			}
		}
		return EnumJob;
	}
	
	//We want salaries to be able to be changed
	public static void assignDefaultSalariesToJob() {
		jobsSalaries.put(Job.developper,30000D);
		jobsSalaries.put(Job.director,45000D);
		jobsSalaries.put(Job.rrhh, 25000D);
		jobsSalaries.put(Job.secreterian, 15000D);
		
	}
	
	
	

}
