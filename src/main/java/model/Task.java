package model;

import java.text.SimpleDateFormat;
import java.util.Date;

import Respository.TaskRespository;

public class Task {
	private int id;
	private String name;
	private Date start_date, end_date;
	private int user_id, job_id, status_id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getJob_id() {
		return job_id;
	}

	public void setJob_id(int job_id) {
		this.job_id = job_id;
	}

	public int getStatus_id() {
		return status_id;
	}

	public void setStatus_id(int status_id) {
		this.status_id = status_id;
	}

	public Task(int id, String name, Date start_date, Date end_date, int user_id, int job_id, int status_id) {
		this.id = id;
		this.name = name;
		this.start_date = start_date;
		this.end_date = end_date;
		this.user_id = user_id;
		this.job_id = job_id;
		this.status_id = status_id;
	}

	public Task() {
		super();
	}

	public String getNameOfUserID() {
		return TaskRespository.getNameOfUserID(this.user_id);
	}

	public String getNameOfProjectID() {
		return TaskRespository.getNameOfProjectID(this.job_id);
	}
	
	public String getNameOfStatusID() {
		return TaskRespository.getNameOfStatusID(this.status_id);
	}
	
	public String formartStartDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(this.start_date);
	}
	
	public String formartEndDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(this.end_date);
	}
}
