package com.juaracoding.main.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.juaracoding.main.model.Bonus;
import com.juaracoding.main.model.DepartmentRowMapper;
import com.juaracoding.main.model.Title;
import com.juaracoding.main.model.Worker;
import com.juaracoding.main.model.WorkerRowMapper;

@RestController
@RequestMapping("/")
public class WorkerController {

	@Autowired
	JdbcTemplate jdbc;

	public List<Worker> getTopSalary() {

		String sql = "select * from worker order by salary desc limit 5";

		List<Worker> topsalary = jdbc.query(sql, new WorkerRowMapper());

		return topsalary;
	}

	public List<Worker> getSameSalary() {
		String sql = "select * from worker where salary in(select salary from worker group by salary having count(*)>1)order by salary desc";

		List<Worker> samesalary = jdbc.query(sql, new WorkerRowMapper());

		return samesalary;
	}
	
	public List<Worker> getDepartmentInfo() {
		String sql = "select department, count(*) from worker group by department;";

		List<Worker> departmentinfo = jdbc.query(sql, new DepartmentRowMapper());
		return departmentinfo;
		
	}

	public int insertWorker(Worker worker) {
		return jdbc.update("insert into worker(worker_id,first_name,last_name,salary,joining_date,department) values("+ worker.getWorker_id() +",'" + worker.getFirst_name() + "','" + worker.getLast_name() + "'," + worker.getSalary() + ",'" + worker.getJoining_date() + "','" + worker.getDepartment() + "')");
	}
	
	public int updateWorker(int idw, Worker worker) {
		return jdbc.update("update worker set `first_name` = '" + worker.getFirst_name() + "', `last_name` = '" + worker.getLast_name() + "', `salary` = " + worker.getSalary() + ", `joining_date` = '" + worker.getJoining_date() + "', `department` = '" + worker.getDepartment() + "' where `worker_id` = " + idw + "");
	}
	
	public int deleteWorker(int id) {
		return jdbc.update("delete from `worker` where `worker_id` = " + id + "");
	}
	
	public int insertBonus(Bonus bonus) {
		return jdbc.update("insert into bonus(working_ref_id,bonus_date,bonus_amount) values(" + bonus.getWorking_ref_id() +", '" + bonus.getBonus_date() + "', " + bonus.getBonus_amount() + ")");
	}
	
	public int updateBonus(int bonus1, Bonus bonus) {
		return jdbc.update("update bonus set `working_ref_id` = " + bonus.getWorking_ref_id() + ", `bonus_date` = '" + bonus.getBonus_date() + "' where `bonus_amount` = " + bonus1 + "");
	}
	
	public int deleteBonus(int id) {
		return jdbc.update("delete from `worker` where `working_ref_id` = " + id + "");
	}
	
	public int insertTitle(Title title) {
		return jdbc.update("insert into title(working_ref_id,worker_title,affected_from) values(" + title.getWorking_ref_id() + ",'" + title.getWorker_title() + "', '" + title.getAffected_from() + "')");
	}
	
	public int updateTitle(int idt, Title title) {
		return jdbc.update("update title set `worker_title` = '" + title.getWorker_title() + "', `affected_from` = '" + title.getAffected_from() + "' where `working_ref_id` = " + idt + "");
	}
	
	public int deleteTitle(int id) {
		return jdbc.update("delete from `worker` where `working_ref_id` = " + id + "");
	}

	// TOP 5 SALARY
	@GetMapping("/topsalary")
	public List<Worker> topSalary() {
		return getTopSalary();
	}

	// SAME SALARY
	@GetMapping("/samesalary")
	public List<Worker> sameSalary() {
		return getSameSalary();
	}
	
	//DEPARTMENT INFO
	@GetMapping("/department")
	public List<Worker> departmentInfo() {
		return getDepartmentInfo();
	}
	
	//INSERT TABEL WORKER
	@PostMapping("/insertworker")
	public String addWorker(@RequestBody Worker worker) {
		
		
		if (this.insertWorker(worker) == 1) {
			return "Insert data berhasil";
		} else {
			return "Insert data gagal";
		}
	}
	
	
	//INSERT TABEL BONUS
	@PostMapping("/insertbonus")
	public String addBonus(@RequestBody Bonus bonus) {
		
		
		if (this.insertBonus(bonus) == 1) {
			return "Insert data berhasil";
		} else {
			return "Insert data gagal";
		}
	}
	
	
	//INSERT TABEL TITLE
	@PostMapping("/inserttitle")
	public String addTitle(@RequestBody Title title ) {
		
		if (this.insertTitle(title) == 1) {
			return "Insert data berhasil";
		} else {
			return "Insert data gagal";
		}
	}
	
	//UPDATE TABEL WORKER
	@PutMapping("/updateworker/{idw}")
	public ResponseEntity<?> update(@RequestBody Worker worker, @PathVariable int idw) {
		 try {
	            updateWorker(idw, worker);
	            return new ResponseEntity<>(HttpStatus.OK);
	        } catch (NoSuchElementException e) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
		 
	 }
	
	//UPDATE TABEL BONUS
	@PutMapping("/updatebonus/{bonus1}")
	public ResponseEntity<?> update(@RequestBody Bonus bonus, @PathVariable int bonus1) {
		 try {
	            updateBonus(bonus1, bonus);
	            return new ResponseEntity<>(HttpStatus.OK);
	        } catch (NoSuchElementException e) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
		 
	 }
	
	//UPDATE TABEL TITLE
	@PutMapping("/updatetitle/{idt}")
	public ResponseEntity<?> update(@RequestBody Title title, @PathVariable int idt) {
		 try {
	            updateTitle(idt, title);
	            return new ResponseEntity<>(HttpStatus.OK);
	        } catch (NoSuchElementException e) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
		 
	 }
	
	//DELETE TABEL WORKER
	@DeleteMapping("/deleteworker/{id}")
	public void dw(@PathVariable int id) {
		deleteWorker(id);
	}
	
	
	//DELETE TABEL BONUS
	@DeleteMapping("/deletebonus/{id}")
	public void db(@PathVariable int id) {
		deleteBonus(id);
	}
	
	
	//DELETE TABEL TITLE
	@DeleteMapping("/deletetitle/{id}")
	public void dt(@PathVariable int id) {
		deleteTitle(id);
	}
	
	
	
	
}
