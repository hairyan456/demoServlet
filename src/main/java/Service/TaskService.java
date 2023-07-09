package Service;

import java.util.Date;
import java.util.List;

import Respository.ProjectRespository;
import Respository.StatusRespository;
import Respository.TaskRespository;
import Respository.UserRespository;
import model.Project;
import model.Task;
import model.Status;
import model.UserModel;

public class TaskService {
	private TaskRespository taskRespository = new TaskRespository();
	private ProjectRespository projectRespository = new ProjectRespository();
	private UserRespository userRespository = new UserRespository();
	private StatusRespository statusRespository = new StatusRespository();

	public List<Task> getListTasks() {
		List<Task> list = taskRespository.getAllTasks();
		return (list.size() > 0) ? list : null;
	}

	//xuất các task thuộc dự án mà leader đó đảm nhiệm
	public List<Task> getAllTasksByLeader(int leader_id) {
		List<Task> list = taskRespository.getAllTasksByLeader(leader_id);
		return (list.size() > 0) ? list : null;
	}
	
	public List<Project> getListProjects() {
		List<Project> list = projectRespository.getAllProjects();
		return (list.size() > 0) ? list : null;
	}
	
	//xuất các  dự án mà leader đó đảm nhiệm
	public List<Project> getAllProjectsByLeader(int leader_id){
		List<Project> list = projectRespository.getAllProjectsByLeader(leader_id);
		return (list.size() > 0) ? list : null;
	}

	public List<UserModel> getListUsers() {
		List<UserModel> list = userRespository.getAllUsers();
		return (list.size() > 0) ? list : null;
	}

	public List<Status> getListStatus() {
		List<Status> list = statusRespository.getAllStatus();
		return (list.size() > 0) ? list : null;
	}
	
	public boolean insertNewTask(String name,Date start_date, Date end_date, String username,String jobname,String statusname) {
		return taskRespository.insertNewTask(name, start_date, end_date, userRespository.getUserIDFromUserName(username),
				projectRespository.getProjectIDFromProjectName(jobname), statusRespository.getStatusIDFromStatusName(statusname));
	}
	
	public boolean deleteTask(int id) {
		return taskRespository.deleteTask(id);
	}
	
	public boolean updateTask2(String projectname, String taskname,String user_fullname,Date startdate, Date enddate, String status,int taskid) {
		return taskRespository.updateTask2(projectRespository.getProjectIDFromProjectName(projectname), taskname, userRespository.getUserIDFromUserName(user_fullname), startdate, enddate, statusRespository.getStatusIDFromStatusName(status),taskid );
	}
	
	public boolean checkTaskNameExisted(String taskname,String projectname) {
		return taskRespository.checkTaskNameExisted(taskname,projectRespository.getProjectIDFromProjectName(projectname));
	}

}
