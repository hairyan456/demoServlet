package Service;

import java.util.Date;
import java.util.List;

import Respository.ProjectRespository;
import Respository.RoleRespository;
import Respository.StatusRespository;
import Respository.TaskRespository;
import Respository.UserRespository;
import model.Project;
import model.Role;
import model.Status;
import model.Task;
import model.UserModel;

public class UserService {
	//trong UserService có thể sử dụng RoleRespository hay bất cứ Class Respository nào khác!  Nhưng trong UserController thì ko được khở tạo RoleService
	private UserRespository userRespository = new UserRespository();
	private RoleRespository roleRespository = new RoleRespository();
	private StatusRespository statusRespository = new StatusRespository();
	private TaskRespository taskRespository = new TaskRespository();
	private ProjectRespository projectRespository = new ProjectRespository();
	
	public List<UserModel> getListUsers() {
		List<UserModel> list = userRespository.getAllUsers();
		return (list.size()>0)?list:null;
	}

	public List<Project> getListProjects(){
		List<Project> list = projectRespository.getAllProjects();
		return (list.size()>0)?list:null;
	}
	
	public List<Status> getListStatus(){
		List<Status> list = statusRespository.getAllStatus();
		return (list.size()>0)?list:null;
	}
	
	public UserModel getUserByID(int user_id) {
		return userRespository.getUserByID(user_id);
	}
	
	public List<Role> getListRoles() {
		List<Role> list = roleRespository.getAllRoles();
		return (list.size()>0)?list:null;
	}
	
	public boolean insertNewUser(String email,String password,String fullname,int role_id) {
		return userRespository.insertNewUser(email, password, fullname,role_id);
	}

	public int getRoleIDFromRoleName(String roleName) {
		return roleRespository.getRoleIDFromRoleName(roleName);
	}
	
	public boolean deleteUser(int id) {
		return userRespository.deleteUser(id);
	}
	
	public boolean checkPasswordExisted(int user_id,String currentPassword) {
		return userRespository.checkPasswordExisted(user_id, currentPassword);
	}
	
	public boolean checkEmailExisted(String email) {
		return userRespository.checkEmailExisted(email);
	}
	
	public boolean checkEmailExisted2(String email,int user_id) {
		return userRespository.checkEmailExisted2(email,user_id);
	}
	public boolean updateUser(String fullname, String email, String newpassword, int role_id, String avatar, int id) {
		return userRespository.updateUser(fullname, email, newpassword, role_id, avatar, id);
	}
	
	public List<Project> getListProjectsByUserID(int user_id){
		return projectRespository.getListProjectsByUserID(user_id);
	}
	
	public int countNotYetImplementedTaskByUserID(int user_id) {
		return taskRespository.countTaskByUserIDAndStatusID(user_id,1);
	}
	
	public int countInProgressTaskByUserID(int user_id) {
		return taskRespository.countTaskByUserIDAndStatusID(user_id,2);
	}
	
	public int countBeenImplementedTaskByUserID(int user_id) {
		return taskRespository.countTaskByUserIDAndStatusID(user_id,3);
	}
	
	public List<Task> getAllTasksByUserIDAndStatusID(int user_id,int status_id){
		return taskRespository.getAllTasksByUserIDAndStatusID(user_id, status_id);
	}
	
	public List<Task> getAllTasksByUserID(int user_id){
		return taskRespository.getAllTasksByUserID(user_id);
	}
	
	public Task getTaskByID(int task_id) {
		for(Task task : taskRespository.getAllTasks()) {
			if(task.getId() == task_id)
				return task;
		}
		return null;
	}
	
	public int getProjectIDFromProjectName(String projectName) {
		return projectRespository.getProjectIDFromProjectName(projectName);
	}
	
	public int getStatusIDFromStatusName(String statusId) {
		return statusRespository.getStatusIDFromStatusName(statusId);
	}
	
	public boolean updateTask(int project_id, String taskname, Date startdate, Date enddate, int statusid,int taskid) {
		return taskRespository.updateTask(project_id, taskname, startdate, enddate, statusid, taskid);
	}
	
	public boolean checkTaskNameExisted(String taskname,String projectname) {
		return taskRespository.checkTaskNameExisted(taskname, projectRespository.getProjectIDFromProjectName(projectname));
	}
	
}
