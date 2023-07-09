package Service;

import java.util.List;

import Respository.RoleRespository;
import Respository.UserRespository;
import model.UserModel;

public class LoginService {
	private UserRespository userRespository = new UserRespository();
	private RoleRespository roleRespository = new RoleRespository();
	
	public  boolean checkLogin(String email, String password) {
		List<UserModel> list = userRespository.findByEmailAndPassword(email, password);
		return list.size()>0;
	}
	
	public UserModel getUserByEmailAndPassword(String email,String password) {
		return (userRespository.findByEmailAndPassword(email, password).get(0));
	}
	
	public String getRoleNameFromRoleID(int role_id) {
		return roleRespository.getRoleNameFromRoleID(role_id);
	}
}
