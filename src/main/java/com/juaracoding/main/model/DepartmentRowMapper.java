package com.juaracoding.main.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class DepartmentRowMapper implements RowMapper<Worker>{

	@Override
	public Worker mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		Worker department = new Worker();
		department.setDepartment(rs.getString("department"));
		
		return department;
	}
	
}
