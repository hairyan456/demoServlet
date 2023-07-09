package Respository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Config.JDBCConfig;
import model.Role;

public class RoleRespository {
	public List<Role> getAllRoles() {
		List<Role> list = new ArrayList<Role>();
		Connection conn = null;
		PreparedStatement prepare = null;
		try {
			conn = JDBCConfig.getConnection();
			prepare = conn.prepareStatement("select * from roles");
			ResultSet result = prepare.executeQuery();
			while (result.next()) {
				list.add(new Role(result.getInt("id"), result.getString("name"), result.getString("description")));
			}
			
		} catch (SQLException e) {
			System.out.println("Error getAllRoles:" + e.getMessage());
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					System.out.println("Lỗi đóng kết nối getAllRoles:"+e.getMessage());
				}
			if (prepare != null)
				try {
					prepare.close();
				} catch (SQLException e) {
					System.out.println("Lỗi đóng kết nối getAllRoles:"+e.getMessage());
				}
		}
		return list;
}

	public int getRoleIDFromRoleName(String roleName) {
		Connection conn = null;
		PreparedStatement prepareStatement = null;
		try {
			conn = JDBCConfig.getConnection();
			String sql = "select * from roles where name = ?;";
			prepareStatement = conn.prepareStatement(sql);
			prepareStatement.setString(1, roleName);
			ResultSet result = prepareStatement.executeQuery();
			while(result.next()) {
				return result.getInt("id");
			}
		}
		catch(Exception e) {
			System.out.println("Error getRoleIDFromRoleName():"+e.getMessage());
		}
		finally {
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(prepareStatement != null) {
				try {
					prepareStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return -1;
	}
	
	public boolean checkRoleNameExisted(String rolename) {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement prepare = null;
		try {
			conn = JDBCConfig.getConnection();
			prepare = conn.prepareStatement("select * from roles where name = ?");
			prepare.setString(1, rolename);
			ResultSet result = prepare.executeQuery();
			while (result.next()) {
				flag = true;
				break;
			}
		} catch (SQLException e) {
			System.out.println("Error checkRoleNameExisted:" + e.getMessage());
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					System.out.println("Lỗi đóng kết nối checkRoleNameExisted:"+e.getMessage());
				}
			if (prepare != null)
				try {
					prepare.close();
				} catch (SQLException e) {
					System.out.println("Lỗi đóng kết nối checkRoleNameExisted:"+e.getMessage());
				}
		}
		return flag;
	}
	
	public static String getRoleNameFromRoleID(int role_id) {
		Connection conn = null;
		PreparedStatement prepareStatement = null;
		try {
			conn = JDBCConfig.getConnection();
			prepareStatement = conn.prepareStatement("select * from roles where id = ?");
			prepareStatement.setInt(1, role_id);
			ResultSet result = prepareStatement.executeQuery();
			while(result.next()) {
				return result.getString("name");
			}
		}
		catch(Exception e) {
			System.out.println("Error getRoleNameFromRoleID():"+e.getMessage());
		}
		finally {
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(prepareStatement != null) {
				try {
					prepareStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	public boolean insertNewRole(String rolename,String description) {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement prepareStatement = null;
		try {
			conn = JDBCConfig.getConnection();
			String sql = "insert into roles(name,description) values (?,?)";
			prepareStatement = conn.prepareStatement(sql);
			prepareStatement.setString(1, rolename);
			prepareStatement.setString(2, description);
			flag = (prepareStatement.executeUpdate() > 0) ? true:false;
		}
		catch(Exception e) {
			System.out.println("Error insertNewRole():"+e.getMessage());
		}
		finally {
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(prepareStatement != null) {
				try {
					prepareStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return flag;
	}
	
	public boolean deleteRole(int id) {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement prepareStatement = null;
		try {
			conn = JDBCConfig.getConnection();
			String sql = "delete from roles where id = ?";
			prepareStatement = conn.prepareStatement(sql);
			prepareStatement.setInt(1, id);
			flag = (prepareStatement.executeUpdate() > 0) ? true:false;
		}
		catch(Exception e) {
			System.out.println("Error deleteRole():"+e.getMessage());
		}
		finally {
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(prepareStatement != null) {
				try {
					prepareStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return flag;
	}
	
	public boolean updateRole(String rolename,String description,int role_id) {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement prepareStatement = null;
		try {
			conn = JDBCConfig.getConnection();
			String sql = "update roles set name=?,description=? where id=?";
			prepareStatement = conn.prepareStatement(sql);
			prepareStatement.setString(1, rolename);
			prepareStatement.setString(2, description);
			prepareStatement.setInt(3, role_id);
			flag = (prepareStatement.executeUpdate() > 0) ? true:false;
		}
		catch(Exception e) {
			System.out.println("Error updateRole():"+e.getMessage());
		}
		finally {
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(prepareStatement != null) {
				try {
					prepareStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return flag;
	}
}
