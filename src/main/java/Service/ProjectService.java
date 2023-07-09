package Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Respository.ProjectRespository;
import Respository.TaskRespository;
import Respository.UserRespository;
import model.Project;
import model.Task;
import model.UserModel;

public class ProjectService {
	private ProjectRespository projectRespository = new ProjectRespository();
	private UserRespository userRespository = new UserRespository();
	private TaskRespository taskRespository = new TaskRespository();

	public List<Project> getListProjects() {
		List<Project> list = projectRespository.getAllProjects();
		return (list.size() > 0) ? list : null;
	}

	public List<Project> getListProjectsOfLeader(int leader_id) {
		List<Project> list = new ArrayList<Project>();
		for (Project project : projectRespository.getAllProjects()) {
			if (project.getLeader_id() == leader_id)
				list.add(project);
		}
		return list;
	}

	public boolean insertNewProject(String name, Date startDate, Date endDate, String leaderName) {
		return projectRespository.insertNewProject(name, startDate, endDate,
				userRespository.getUserIDFromUserName(leaderName));
	}

	// dành cho chính Leader khi thêm 1 dự án
	public boolean insertNewProject2(String name, Date startDate, Date endDate, int leader_id) {
		return projectRespository.insertNewProject(name, startDate, endDate, leader_id);
	}

	public boolean deleteProject(int id) {
		return projectRespository.deleteProject(id);
	}

	public boolean checkProjectNameExisted(String projectname) {
		return projectRespository.checkProjectNameExisted(projectname);
	}

	public boolean updateProject(String projectname, Date startdate, Date enddate, int project_id, String leaderName) {
		return projectRespository.updateProject(projectname, startdate, enddate, project_id,
				userRespository.getUserIDFromUserName(leaderName));
	}

	public boolean updateProject2(String projectname, Date startdate, Date enddate, int project_id, int leader_id) {
		return projectRespository.updateProject(projectname, startdate, enddate, project_id, leader_id);
	}

	public static String getLeaderNameByID(int leader_id) {
		return UserRespository.getUserNameByID(leader_id);
	}

	public List<UserModel> getListLeaders() {
		List<UserModel> listLeaders = new ArrayList<UserModel>();
		for (UserModel user : userRespository.getAllUsers()) {
			if (user.getRoleNameFromRoleID().equalsIgnoreCase("leader")) {
				listLeaders.add(user);
			}
		}
		return listLeaders;
	}

	public List<UserModel> getUserByID(int user_id) {
		List<UserModel> list = new ArrayList<UserModel>();
		list.add(userRespository.getUserByID(user_id));
		return list;
	}

	// lấy những user nào mà thuộc project chỉ định:
	public List<UserModel> getListUsersDoProject(int project_id) {
		return userRespository.getListUsersDoProject(project_id);
	}

	// lấy toàn bộ các Task của toàn bộ User, thuộc Project và Status chỉ định:
	public List<Task> getAllTasksByProjectAndStatus(int project_id,int status_id){
		return taskRespository.getAllTasksByProjectAndStatus(project_id, status_id);
	}
	
	//đếm số lượng task bởi Project và Status chỉ định:
	public int countTaskByProjectAndStatus(int project_id,int status_id) {
		return taskRespository.countTaskByProjectAndStatus(project_id, status_id);
	}
}
