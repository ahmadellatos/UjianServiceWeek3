package com.juaracoding.main.model;

public class Title {
	private int working_ref_id;
	private String worker_title;
	private String affected_from;
	
	public Title() {
		
	}

	public int getWorking_ref_id() {
		return working_ref_id;
	}

	public void setWorking_ref_id(int working_ref_id) {
		this.working_ref_id = working_ref_id;
	}

	public String getWorker_title() {
		return worker_title;
	}

	public void setWorker_title(String worker_title) {
		this.worker_title = worker_title;
	}

	public String getAffected_from() {
		return affected_from;
	}

	public void setAffected_from(String affected_from) {
		this.affected_from = affected_from;
	}
}
