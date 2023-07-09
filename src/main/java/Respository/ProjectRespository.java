package Respository;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Config.JDBCConfig;
import model.Project;

public class ProjectRespository {
	public List<Project> getAllProjects() {
		List<Project> list = new ArrayList<Project>();
		Connection conn = null;
		PreparedStatement prepare = null;
		try {
			conn = JDBCConfig.getConnection();
			prepare = conn.prepareStatement("select * from jobs");
			ResultSet result = prepare.executeQuery();
			while (result.next()) {
				// java.util.date có thể chứa cả java.sql.date (vì java.sql là con của java.util) nhưng ko ngược lại được.
				list.add(new Project(result.getInt("id"), result.getString("name"), result.getDate("start_date"),
						result.getDate("end_date"),result.getInt("leader_id")));
			}
		} catch (SQLException e) {
			System.out.println("Error getAllProjects:" + e.getMessage());
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					System.out.println("Lỗi đóng kết nối getAllProjects:" + e.getMessage());
				}
			if (prepare != null)
				try {
					prepare.close();
				} catch (SQLException e) {
					System.out.println("Lỗi đóng kết nối getAllProjects:" + e.getMessage());
				}
		}
		return list;
	}
	
	public List<Project> getAllProjectsByLeader(int leader_id) {
		List<Project> list = new ArrayList<Project>();
		Connection conn = null;
		PreparedStatement prepare = null;
		try {
			conn = JDBCConfig.getConnection();
			prepare = conn.prepareStatement("select * from jobs where leader_id = ?");
			prepare.setInt(1, leader_id);
			ResultSet result = prepare.executeQuery();
			while (result.next()) {
				// java.util.date có thể chứa cả java.sql.date (vì java.sql là con của java.util) nhưng ko ngược lại được.
				list.add(new Project(result.getInt("id"), result.getString("name"), result.getDate("start_date"),
						result.getDate("end_date"),result.getInt("leader_id")));
			}
		} catch (SQLException e) {
			System.out.println("Error getAllProjectsByLeader:" + e.getMessage());
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					System.out.println("Lỗi đóng kết nối getAllProjectsByLeader:" + e.getMessage());
				}
			if (prepare != null)
				try {
					prepare.close();
				} catch (SQLException e) {
					System.out.println("Lỗi đóng kết nối getAllProjectsByLeader:" + e.getMessage());
				}
		}
		return list;
	}
	
	public int getProjectIDFromProjectName(String name) {
		Connection conn = null;
		PreparedStatement prepareStatement = null;
		try {
			conn = JDBCConfig.getConnection();
			String sql = "select * from jobs where name = ?;";
			prepareStatement = conn.prepareStatement(sql);
			prepareStatement.setString(1, name);
			ResultSet result = prepareStatement.executeQuery();
			while(result.next()) {
				return result.getInt("id");
			}
		}
		catch(Exception e) {
			System.out.println("Error getProjectIDFromProjectName():"+e.getMessage());
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
	
	public boolean insertNewProject(String name,Date startDate,Date endDate,int leader_id) {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement prepareStatement = null;
		try {
			conn = JDBCConfig.getConnection();
			String sql = "insert into jobs(name,start_date,end_date,leader_id) values (?,?,?,?)";
			prepareStatement = conn.prepareStatement(sql);
			prepareStatement.setString(1, name);
			prepareStatement.setDate(2, new java.sql.Date(startDate.getTime()));
			prepareStatement.setDate(3, new java.sql.Date(endDate.getTime()));
			prepareStatement.setInt(4, leader_id);
			flag = (prepareStatement.executeUpdate() > 0) ? true:false;
		}
		catch(Exception e) {
			System.out.println("Error insertNewProject():"+e.getMessage());
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
	
	public boolean deleteProject(int id) {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement prepareStatement = null;
		try {
			conn = JDBCConfig.getConnection();
			String sql = "delete from jobs where id = ?";
			prepareStatement = conn.prepareStatement(sql);
			prepareStatement.setInt(1, id);
			flag = (prepareStatement.executeUpdate() > 0) ? true:false;
		}
		catch(Exception e) {
			System.out.println("Error deleteProject():"+e.getMessage());
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
	
	public boolean checkProjectNameExisted(String projectname) {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement prepare = null;
		try {
			conn = JDBCConfig.getConnection();
			prepare = conn.prepareStatement("select * from jobs where name = ?");
			prepare.setString(1, projectname);
			ResultSet result = prepare.executeQuery();
			while (result.next()) {
				flag = true;
				break;
			}
		} catch (SQLException e) {
			System.out.println("Error checkProjectNameExisted:" + e.getMessage());
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					System.out.println("Lỗi đóng kết nối checkProjectNameExisted:"+e.getMessage());
				}
			if (prepare != null)
				try {
					prepare.close();
				} catch (SQLException e) {
					System.out.println("Lỗi đóng kết nối checkProjectNameExisted:"+e.getMessage());
				}
		}
		return flag;
}
	
	public boolean updateProject(String projectname, Date startdate, Date enddate , int project_id,int leader_id) {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement prepareStatement = null;
		try {
			conn = JDBCConfig.getConnection();
			String sql = "update jobs set name=?,start_date=?,end_date=?,leader_id = ? where id=?";
			prepareStatement = conn.prepareStatement(sql);
			prepareStatement.setString(1, projectname);
			prepareStatement.setDate(2, new java.sql.Date(startdate.getTime()));
			prepareStatement.setDate(3, new java.sql.Date(enddate.getTime()));
			prepareStatement.setInt(4, leader_id);
			prepareStatement.setInt(5, project_id);
			flag = (prepareStatement.executeUpdate() > 0) ? true:false;
		}
		catch(Exception e) {
			System.out.println("Error updateProject():"+e.getMessage());
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
	
	//lấy danh sách Project mà user_id đang thực hiện các task thuộc 1 trong các project này
	public List<Project> getListProjectsByUserID(int user_id){
		List<Project> list = new ArrayList<Project>();
		Connection conn = null;
		PreparedStatement prepare = null;
		try {
			conn = JDBCConfig.getConnection();
			prepare = conn.prepareStatement("select DISTINCT  j.* from jobs j inner join tasks t on j.id  = t.job_id where t.user_id = ?");
			prepare.setInt(1, user_id);
			ResultSet result = prepare.executeQuery();
			while (result.next()) {
				// java.util.date có thể chứa cả java.sql.date (vì java.sql là con của java.util) nhưng ko ngược lại được.
				list.add(new Project(result.getInt("id"), result.getString("name"), result.getDate("start_date"),
						result.getDate("end_date"),result.getInt("leader_id")));
			}
		} catch (SQLException e) {
			System.out.println("Error getListProjectsByUserID:" + e.getMessage());
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					System.out.println("Lỗi đóng kết nối getListProjectsByUserID:" + e.getMessage());
				}
			if (prepare != null)
				try {
					prepare.close();
				} catch (SQLException e) {
					System.out.println("Lỗi đóng kết nối getListProjectsByUserID:" + e.getMessage());
				}
		}
		return list;
	}
}
