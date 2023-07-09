package Service;

import java.util.List;

import Respository.RoleRespository;
import model.Role;

public class RoleService {
private RoleRespository roleRespository = new RoleRespository();
	
	public List<Role> getListRoles() {
		List<Role> list = roleRespository.getAllRoles();
		return (list.size()>0)?list:null;
	}

	public boolean insertNewRole(String rolename,String description) {
		return roleRespository.insertNewRole(rolename, description);
	}
	
	public boolean deleteRole(int id) {
		return roleRespository.deleteRole(id);
	}
	
	public boolean checkRoleNameExisted(String rolename) {
		return roleRespository.checkRoleNameExisted(rolename);
	}
	
	public boolean updateRole(String rolename,String description, int  role_id) {
		return roleRespository.updateRole(rolename, description,role_id);
	}
}
