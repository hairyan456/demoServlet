package Respository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Config.JDBCConfig;
import model.UserModel;

public class UserRespository {
	public List<UserModel> findByEmailAndPassword(String email, String password) {
		List<UserModel> list = new ArrayList<UserModel>();
		Connection conn = null;
		PreparedStatement prepare = null;
		try {
			conn = JDBCConfig.getConnection();
			prepare = conn.prepareStatement("select * from users as u where email = ? and password = ?");
			prepare.setString(1, email);
			prepare.setString(2, password);
			// statement có 2 loại thực thi:
				// + executeQuery() : SELECT...
				// + executeUpdate(): INSERT,UPDATE,DELETE...
			ResultSet result = prepare.executeQuery();
			while (result.next()) {
				list.add(new UserModel(result.getInt("id"), result.getString("email"), result.getString("password"),
						result.getString("fullname"), result.getString("avatar"), result.getInt("role_id")));
			}
			
		} catch (SQLException e) {
			System.out.println("Error findByEmailAndPassword:" + e.getMessage());
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					System.out.println("Lỗi đóng kết nối findByEmailAndPassword:"+e.getMessage());
				}
			if (prepare != null)
				try {
					prepare.close();
				} catch (SQLException e) {
					System.out.println("Lỗi đóng kết nối findByEmailAndPassword:"+e.getMessage());
				}
		}
		return list;
	}
	
	public List<UserModel> getAllUsers() {
		List<UserModel> list = new ArrayList<UserModel>();
		Connection conn = null;
		PreparedStatement prepare = null;
		try {
			conn = JDBCConfig.getConnection();
			prepare = conn.prepareStatement("select * from users");
			ResultSet result = prepare.executeQuery();
			while (result.next()) {
				list.add(new UserModel(result.getInt("id"), result.getString("email"), result.getString("password"),
						result.getString("fullname"), result.getString("avatar"), result.getInt("role_id")));
			}
			
		} catch (SQLException e) {
			System.out.println("Error getAllUsers:" + e.getMessage());
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					System.out.println("Lỗi đóng kết nối getAllUsers:"+e.getMessage());
				}
			if (prepare != null)
				try {
					prepare.close();
				} catch (SQLException e) {
					System.out.println("Lỗi đóng kết nối getAllUsers:"+e.getMessage());
				}
		}
		return list;
}
	
	public UserModel getUserByID(int user_id) {
		Connection conn = null;
		PreparedStatement prepare = null;
		try {
			conn = JDBCConfig.getConnection();
			prepare = conn.prepareStatement("select * from users where id = ?");
			prepare.setInt(1, user_id);
			ResultSet result = prepare.executeQuery();
			while (result.next()) {
				return new UserModel(user_id,result.getString("email"),result.getString("password"),result.getString("fullname"),
						result.getString("avatar"),result.getInt("role_id"));
			}
			
		} catch (SQLException e) {
			System.out.println("Error getUserByID:" + e.getMessage());
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					System.out.println("Lỗi đóng kết nối getUserByID:"+e.getMessage());
				}
			if (prepare != null)
				try {
					prepare.close();
				} catch (SQLException e) {
					System.out.println("Lỗi đóng kết nối getUserByID:"+e.getMessage());
				}
		}
		return null;
	}
	
	public static String getUserNameByID(int user_id) {
		Connection conn = null;
		PreparedStatement prepare = null;
		try {
			conn = JDBCConfig.getConnection();
			prepare = conn.prepareStatement("select * from users where id = ?");
			prepare.setInt(1, user_id);
			ResultSet result = prepare.executeQuery();
			while (result.next()) {
				return result.getString("fullname");
			}
			
		} catch (SQLException e) {
			System.out.println("Error getUserNameByID:" + e.getMessage());
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					System.out.println("Lỗi đóng kết nối getUserNameByID:"+e.getMessage());
				}
			if (prepare != null)
				try {
					prepare.close();
				} catch (SQLException e) {
					System.out.println("Lỗi đóng kết nối getUsgetUserNameByIDerByID:"+e.getMessage());
				}
		}
		return null;
	}
	public int getUserIDFromUserName(String name) {
		Connection conn = null;
		PreparedStatement prepareStatement = null;
		try {
			conn = JDBCConfig.getConnection();
			String sql = "select * from users where fullname = ?;";
			prepareStatement = conn.prepareStatement(sql);
			prepareStatement.setString(1, name.trim());
			ResultSet result = prepareStatement.executeQuery();
			while(result.next()) {
				return result.getInt("id");
			}
		}
		catch(Exception e) {
			System.out.println("Error getUserIDFromUserName():"+e.getMessage());
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
	
	public boolean insertNewUser(String email,String password,String fullname,int role_id) {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement prepareStatement = null;
		try {
			conn = JDBCConfig.getConnection();
			String sql = "insert into users(email,password,fullname,role_id) values (?,?,?,?)";
			prepareStatement = conn.prepareStatement(sql);
			prepareStatement.setString(1, email);
			prepareStatement.setString(2, password);
			prepareStatement.setString(3, fullname);
			prepareStatement.setInt(4, role_id);
			flag = (prepareStatement.executeUpdate() > 0) ? true:false;
		}
		catch(Exception e) {
			System.out.println("Error insertNewUser():"+e.getMessage());
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
	
	public boolean deleteUser(int id) {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement prepareStatement = null;
		try {
			conn = JDBCConfig.getConnection();
			String sql = "delete from users where id = ?";
			prepareStatement = conn.prepareStatement(sql);
			prepareStatement.setInt(1, id);
			flag = (prepareStatement.executeUpdate() > 0) ? true:false;
		}
		catch(Exception e) {
			System.out.println("Error deleteUser():"+e.getMessage());
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
	
	public boolean updateUser(String fullname, String email, String newpassword, int role_id, String avatar, int id) {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement prepareStatement = null;
		try {
			conn = JDBCConfig.getConnection();
			String sql = "update users set fullname = ?,email = ?, password = ?, role_id = ?, avatar = ?  where id = ?";
			prepareStatement = conn.prepareStatement(sql);
			prepareStatement.setString(1, fullname);
			prepareStatement.setString(2, email);
			prepareStatement.setString(3, newpassword);
			prepareStatement.setInt(4, role_id);
			prepareStatement.setString(5, avatar);
			prepareStatement.setInt(6, id);
			flag = (prepareStatement.executeUpdate() > 0) ? true:false;
		}
		catch(Exception e) {
			System.out.println("Error updateUser():"+e.getMessage());
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
	
	public boolean checkPasswordExisted(int user_id,String currentPassword) {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement prepare = null;
		try {
			conn = JDBCConfig.getConnection();
			prepare = conn.prepareStatement("select * from users where id = ? and password = ?");
			prepare.setInt(1, user_id);
			prepare.setString(2, currentPassword);
			ResultSet result = prepare.executeQuery();
			while (result.next()) {
				flag = true;
				break;
			}
			
		} catch (SQLException e) {
			System.out.println("Error checkPasswordExisted:" + e.getMessage());
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					System.out.println("Lỗi đóng kết nối checkPasswordExisted:"+e.getMessage());
				}
			if (prepare != null)
				try {
					prepare.close();
				} catch (SQLException e) {
					System.out.println("Lỗi đóng kết nối checkPasswordExisted:"+e.getMessage());
				}
		}
		return flag;
	}
	
	public boolean checkEmailExisted(String email) {
			boolean flag = false;
			Connection conn = null;
			PreparedStatement prepare = null;
			try {
				conn = JDBCConfig.getConnection();
				prepare = conn.prepareStatement("select * from users where email = ?");
				prepare.setString(1, email);
				ResultSet result = prepare.executeQuery();
				while (result.next()) {
					flag = true;
					break;
				}
			} catch (SQLException e) {
				System.out.println("Error checkEmailExisted:" + e.getMessage());
			} finally {
				if (conn != null)
					try {
						conn.close();
					} catch (SQLException e) {
						System.out.println("Lỗi đóng kết nối checkEmailExisted:"+e.getMessage());
					}
				if (prepare != null)
					try {
						prepare.close();
					} catch (SQLException e) {
						System.out.println("Lỗi đóng kết nối checkEmailExisted:"+e.getMessage());
					}
			}
			return flag;
	}
	
	public boolean checkEmailExisted2(String email,int user_id) {
		if(getUserByID(user_id).getEmail().equalsIgnoreCase(email))
			return false;
		boolean flag = false;
		Connection conn = null;
		PreparedStatement prepare = null;
		try {
			conn = JDBCConfig.getConnection();
			prepare = conn.prepareStatement("select * from users where email = ?");
			prepare.setString(1, email);
			ResultSet result = prepare.executeQuery();
			while (result.next()) {
				flag = true;
				break;
			}
		} catch (SQLException e) {
			System.out.println("Error checkEmailExisted:" + e.getMessage());
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					System.out.println("Lỗi đóng kết nối checkEmailExisted:"+e.getMessage());
				}
			if (prepare != null)
				try {
					prepare.close();
				} catch (SQLException e) {
					System.out.println("Lỗi đóng kết nối checkEmailExisted:"+e.getMessage());
				}
		}
		return flag;
}

	//lấy những user nào mà thực hiện những task thuộc project chỉ định:
	public List<UserModel> getListUsersDoProject(int project_id) {
		List<UserModel> list = new ArrayList<UserModel>();
		Connection conn = null;
		PreparedStatement prepare = null;
		try {
			conn = JDBCConfig.getConnection();
			prepare = conn.prepareStatement("select distinct u.* from users u inner join tasks t on u.id  = t.user_id where t.job_id = ?");
			prepare.setInt(1, project_id);
			ResultSet result = prepare.executeQuery();
			while (result.next()) {
				list.add(new UserModel(result.getInt("id"), result.getString("email"), result.getString("password"),
						result.getString("fullname"), result.getString("avatar"), result.getInt("role_id")));
			}
			
		} catch (SQLException e) {
			System.out.println("Error getListUsersDoProject:" + e.getMessage());
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					System.out.println("Lỗi đóng kết nối getListUsersDoProject:"+e.getMessage());
				}
			if (prepare != null)
				try {
					prepare.close();
				} catch (SQLException e) {
					System.out.println("Lỗi đóng kết nối getListUsersDoProject:"+e.getMessage());
				}
		}
		return list;
	}
}
