package model;

import java.util.Date;

import Service.ProjectService;

public class Project {
	private int id;
	private String name;
	private Date start_date, end_date;
	private int leader_id;
	
	public int getLeader_id() {
		return leader_id;
	}
	public void setLeader_id(int leader_id) {
		this.leader_id = leader_id;
	}
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
	public Project(int id, String name, Date start_date, Date end_date,int leader_id) {
		this.id = id;
		this.name = name;
		this.start_date = start_date;
		this.end_date = end_date;
		this.leader_id = leader_id;
	}
	
	public Project() {
		super();
	}
	
	public String getLeaderName() {
		return ProjectService.getLeaderNameByID(this.leader_id);
	}
}
