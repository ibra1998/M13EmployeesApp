

package com.example.M13SimpleHttpService.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.M13SimpleHttpService.bean.BaseDatos;
import com.example.M13SimpleHttpService.bean.BaseDatos2;
import com.example.M13SimpleHttpService.employee.Employee;
import com.example.M13SimpleHttpService.employee.Employee.Job;
import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

@Controller
@RequestMapping("/home")
public class ControladorBrahim {

    private BaseDatos2 bd= new BaseDatos2();
    private Model modelo;
    private String index;
    private List<Employee> lista;
    
    @GetMapping("/")
    public String iniciar(Model modelo) {
        this.modelo = modelo;
        this.lista= this.bd.getEmployeesList();
        modelo.addAttribute("empleados",this.lista );
        modelo.addAttribute("empleadoscontrabajo",new ArrayList<Employee>());
        modelo.addAttribute("jobsList", Arrays.asList(Employee.Job.values()));
        this.index = "index";
        return this.index;
    }

    @PostMapping("/create")
    public String inserta(@RequestParam String name, @RequestParam String job, Model modelo) {

        try {
            if (name != null && job !=null) {
                bd.insertaEmpleado(new Employee(name, job));
            }
        }catch(Exception e) {
            System.out.print(e.getMessage());
        }
        return "redirect:/home/";
    }


	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable int id, Model model) {
		try {
			if (bd.obtenerEmpleado(id) != null) {
				bd.deleteEmployee(id);
			}	
		}catch(Exception e) {
			System.out.print(e.getMessage());
		}
        //List<Employee> lista= this.bd.getEmployeesList();
        //this.modelo.addAttribute("empleados",lista );
        //this.modelo.addAttribute("jobsList", Arrays.asList(Employee.Job.values()));
        return "redirect:/home/";
	}
	
	
	
	@PostMapping("/update/{id}")
		public String modificar(@PathVariable int id, @RequestParam String name, @RequestParam String job ) {
			if (bd.obtenerEmpleado(id) !=null) {
				Employee employee=new Employee(name, job);
				employee.setId(id);
				bd.modificarEmpleado(employee);
			}
			return "redirect:/home/";
			
		}
	
	@PostMapping("/retrieve")
	public ArrayList<Employee> retrieve() {
		return bd.getEmployeesList();
	}
	
	@GetMapping("jobs")
	public List<Job> getJobs() {
		return Arrays.asList(Employee.Job.values());
		
	}
	@PostMapping("/searchByJob/")
	public String findWorkers(@RequestParam String job, Model modelo) {
		Employee.Job jobb= Employee.convertStringToEnumJob(job);
        List<Employee> lista= this.bd.getEmployeeByJob(jobb);
        if (lista.isEmpty()) {
            modelo.addAttribute("empleados",new ArrayList<Employee>());
        }else {
            modelo.addAttribute("empleados",this.bd.getEmployeesList());
        }
        modelo.addAttribute("empleadoscontrabajo",lista);
        modelo.addAttribute("jobsList", Arrays.asList(Employee.Job.values()));
		return "index";
	}
	

}
