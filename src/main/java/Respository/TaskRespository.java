package Respository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Config.JDBCConfig;
import model.Task;

public class TaskRespository {
	public static String getNameOfUserID(int id) {
		Connection conn = null;
		PreparedStatement prepare = null;
		try {
			conn = JDBCConfig.getConnection();
			prepare = conn.prepareStatement("select * from users where id = ?");
			prepare.setInt(1, id);
			ResultSet result = prepare.executeQuery();
			while (result.next()) {
				return result.getString("fullname");
			}

		} catch (SQLException e) {
			System.out.println("Error getNameOfUserID:" + e.getMessage());
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					System.out.println("Lỗi đóng kết nối getNameOfUserID:" + e.getMessage());
				}
			if (prepare != null)
				try {
					prepare.close();
				} catch (SQLException e) {
					System.out.println("Lỗi đóng kết nối getNameOfUserID:" + e.getMessage());
				}
		}
		return "";
	}

	public static String getNameOfProjectID(int id) {
		Connection conn = null;
		PreparedStatement prepare = null;
		try {
			conn = JDBCConfig.getConnection();
			prepare = conn.prepareStatement("select * from jobs where id = ?");
			prepare.setInt(1, id);
			ResultSet result = prepare.executeQuery();
			while (result.next()) {
				return result.getString("name");
			}

		} catch (SQLException e) {
			System.out.println("Error getNameOfProjectID:" + e.getMessage());
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					System.out.println("Lỗi đóng kết nối getNameOfProjectID:" + e.getMessage());
				}
			if (prepare != null)
				try {
					prepare.close();
				} catch (SQLException e) {
					System.out.println("Lỗi đóng kết nối getNameOfProjectID:" + e.getMessage());
				}
		}
		return "";
	}

	public static String getNameOfStatusID(int id) {
		Connection conn = null;
		PreparedStatement prepare = null;
		try {
			conn = JDBCConfig.getConnection();
			prepare = conn.prepareStatement("select * from status where id = ?");
			prepare.setInt(1, id);
			ResultSet result = prepare.executeQuery();
			while (result.next()) {
				return result.getString("name");
			}

		} catch (SQLException e) {
			System.out.println("Error getNameOfStatusID:" + e.getMessage());
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					System.out.println("Lỗi đóng kết nối getNameOfStatusID:" + e.getMessage());
				}
			if (prepare != null)
				try {
					prepare.close();
				} catch (SQLException e) {
					System.out.println("Lỗi đóng kết nối getNameOfStatusID:" + e.getMessage());
				}
		}
		return "";
	}

	public List<Task> getAllTasks() {
		List<Task> list = new ArrayList<Task>();
		Connection conn = null;
		PreparedStatement prepare = null;
		try {
			conn = JDBCConfig.getConnection();
			prepare = conn.prepareStatement("select * from tasks");
			ResultSet result = prepare.executeQuery();
			while (result.next()) {
				list.add(new Task(result.getInt("id"), result.getString("name"), result.getDate("start_date"),
						result.getDate("end_date"), result.getInt("user_id"), result.getInt("job_id"),
						result.getInt("status_id")));
			}

		} catch (SQLException e) {
			System.out.println("Error getAllTasks:" + e.getMessage());
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					System.out.println("Lỗi đóng kết nối getAllTasks():" + e.getMessage());
				}
			if (prepare != null)
				try {
					prepare.close();
				} catch (SQLException e) {
					System.out.println("Lỗi đóng kết nối getAllTasks():" + e.getMessage());
				}
		}
		return list;
	}
	
	public List<Task> getAllTasksByUserIDAndStatusID(int user_id,int status_id){
		List<Task> list = new ArrayList<Task>();
		Connection conn = null;
		PreparedStatement prepare = null;
		try {
			conn = JDBCConfig.getConnection();
			prepare = conn.prepareStatement("select * from tasks where user_id = ? and status_id = ?");
			prepare.setInt(1, user_id);
			prepare.setInt(2, status_id);
			ResultSet result = prepare.executeQuery();
			while (result.next()) {
				list.add(new Task(result.getInt("id"), result.getString("name"), result.getDate("start_date"),
						result.getDate("end_date"), result.getInt("user_id"), result.getInt("job_id"),
						result.getInt("status_id")));
			}

		} catch (SQLException e) {
			System.out.println("Error getAllTasksByUserIDAndStatusID:" + e.getMessage());
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					System.out.println("Lỗi đóng kết nối getAllTasksByUserIDAndStatusID():" + e.getMessage());
				}
			if (prepare != null)
				try {
					prepare.close();
				} catch (SQLException e) {
					System.out.println("Lỗi đóng kết nối getAllTasksByUserIDAndStatusID():" + e.getMessage());
				}
		}
		return list;
	}
	
	public List<Task> getAllTasksByUserID(int user_id){
		List<Task> list = new ArrayList<Task>();
		Connection conn = null;
		PreparedStatement prepare = null;
		try {
			conn = JDBCConfig.getConnection();
			prepare = conn.prepareStatement("select * from tasks where user_id = ?");
			prepare.setInt(1, user_id);
			ResultSet result = prepare.executeQuery();
			while (result.next()) {
				list.add(new Task(result.getInt("id"), result.getString("name"), result.getDate("start_date"),
						result.getDate("end_date"), result.getInt("user_id"), result.getInt("job_id"),
						result.getInt("status_id")));
			}

		} catch (SQLException e) {
			System.out.println("Error getAllTasksByUserID:" + e.getMessage());
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					System.out.println("Lỗi đóng kết nối getAllTasksByUserID():" + e.getMessage());
				}
			if (prepare != null)
				try {
					prepare.close();
				} catch (SQLException e) {
					System.out.println("Lỗi đóng kết nối getAllTasksByUserID():" + e.getMessage());
				}
		}
		return list;
	}
	
		//đếm số lượng task chưa được thực hiện:
		public int countTaskByUserIDAndStatusID(int user_id,int status_id) {
			Connection conn = null;
			PreparedStatement prepareStatement = null;
			try {
				conn = JDBCConfig.getConnection();
				String sql = "select  COUNT(*) as 'amount of task' from users as u inner join tasks as t on u.id = t.user_id inner join status "
						+ "as s on s.id = t.status_id where t.user_id = ? and t.status_id = ?";
				prepareStatement = conn.prepareStatement(sql);
				prepareStatement.setInt(1, user_id);
				prepareStatement.setInt(2, status_id);
				ResultSet result = prepareStatement.executeQuery();
				while(result.next()) {
					return result.getInt("amount of task");
				}
			}
			catch(Exception e) {
				System.out.println("Error countTaskByUserIDAndStatusID():"+e.getMessage());
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
		
		//đếm số lượng task bởi Project và Status chỉ định:
				public int countTaskByProjectAndStatus(int project_id,int status_id) {
					Connection conn = null;
					PreparedStatement prepareStatement = null;
					try {
						conn = JDBCConfig.getConnection();
						String sql = "select  COUNT(*) as 'amount of task' from  tasks as t where t.job_id = ? and t.status_id = ?";
						prepareStatement = conn.prepareStatement(sql);
						prepareStatement.setInt(1, project_id);
						prepareStatement.setInt(2, status_id);
						ResultSet result = prepareStatement.executeQuery();
						while(result.next()) {
							return result.getInt("amount of task");
						}
					}
					catch(Exception e) {
						System.out.println("Error countTaskByProjectAndStatus():"+e.getMessage());
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
	
	//lấy danh sách các task thuộc project do leader nào đó quản lý:
	public List<Task> getAllTasksByLeader(int leader_id) {
		List<Task> list = new ArrayList<Task>();
		Connection conn = null;
		PreparedStatement prepare = null;
		try {
			conn = JDBCConfig.getConnection();
			prepare = conn.prepareStatement("select t.* from tasks t inner join jobs j on t.job_id = j.id  where j.leader_id = ?");
			prepare.setInt(1, leader_id);
			ResultSet result = prepare.executeQuery();
			while (result.next()) {
				list.add(new Task(result.getInt("id"), result.getString("name"), result.getDate("start_date"),
						result.getDate("end_date"), result.getInt("user_id"), result.getInt("job_id"),
						result.getInt("status_id")));
			}

		} catch (SQLException e) {
			System.out.println("Error getAllTasksByLeader:" + e.getMessage());
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					System.out.println("Lỗi đóng kết nối getAllTasksByLeader():" + e.getMessage());
				}
			if (prepare != null)
				try {
					prepare.close();
				} catch (SQLException e) {
					System.out.println("Lỗi đóng kết nối getAllTasksByLeader():" + e.getMessage());
				}
		}
		return list;
	}
	
	public boolean insertNewTask(String name,Date start_date, Date end_date, int user_id,int job_id,int status_id) {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement prepareStatement = null;
		try {
			conn = JDBCConfig.getConnection();
			String sql = "insert into tasks(name,start_date,end_date,user_id,job_id,status_id) values (?,?,?,?,?,?)";
			prepareStatement = conn.prepareStatement(sql);
			prepareStatement.setString(1, name);
			prepareStatement.setDate(2,new java.sql.Date(start_date.getTime()));
			prepareStatement.setDate(3,new java.sql.Date(end_date.getTime()));
			prepareStatement.setInt(4,user_id);
			prepareStatement.setInt(5,job_id);
			prepareStatement.setInt(6,status_id);
			flag = (prepareStatement.executeUpdate() > 0) ? true:false;
		}
		catch(Exception e) {
			System.out.println("Error insertNewTask():"+e.getMessage());
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
	
	public boolean deleteTask(int id) {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement prepareStatement = null;
		try {
			conn = JDBCConfig.getConnection();
			String sql = "delete from tasks where id = ?";
			prepareStatement = conn.prepareStatement(sql);
			prepareStatement.setInt(1, id);
			flag = (prepareStatement.executeUpdate() > 0) ? true:false;
		}
		catch(Exception e) {
			System.out.println("Error deleteTask():"+e.getMessage());
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

	//hàm updateTask nhưng ko update user thực hiện:
	public boolean updateTask(int project_id, String taskname, Date startdate, Date enddate, int statusid,int taskid) {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement prepareStatement = null;
		try {
			conn = JDBCConfig.getConnection();
			String sql = "update tasks set job_id = ?,name = ?, start_date = ?, end_date = ?, status_id = ?  where id = ?";
			prepareStatement = conn.prepareStatement(sql);
			prepareStatement.setInt(1, project_id);
			prepareStatement.setString(2, taskname);
			prepareStatement.setDate(3, new java.sql.Date(startdate.getTime()));
			prepareStatement.setDate(4, new java.sql.Date(enddate.getTime()));
			prepareStatement.setInt(5, statusid);
			prepareStatement.setInt(6, taskid);
			flag = (prepareStatement.executeUpdate() > 0) ? true:false;
		}
		catch(Exception e) {
			System.out.println("Error updateTask():"+e.getMessage());
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
	
	//hàm update task mà có update user thực hiện:
	public boolean updateTask2(int project_id, String taskname,int user_id,Date startdate, Date enddate, int statusid,int taskid) {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement prepareStatement = null;
		try {
			conn = JDBCConfig.getConnection();
			String sql = "update tasks set job_id = ?,name = ?,user_id = ?, start_date = ?, end_date = ?, status_id = ?  where id = ?";
			prepareStatement = conn.prepareStatement(sql);
			prepareStatement.setInt(1, project_id);
			prepareStatement.setString(2, taskname);
			prepareStatement.setInt(3, user_id);
			prepareStatement.setDate(4, new java.sql.Date(startdate.getTime()));
			prepareStatement.setDate(5, new java.sql.Date(enddate.getTime()));
			prepareStatement.setInt(6, statusid);
			prepareStatement.setInt(7, taskid);
			flag = (prepareStatement.executeUpdate() > 0) ? true:false;
		}
		catch(Exception e) {
			System.out.println("Error updateTask2():"+e.getMessage());
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
	
	public boolean checkTaskNameExisted(String taskname,int project_id) {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement prepare = null;
		try {
			conn = JDBCConfig.getConnection();
			prepare = conn.prepareStatement("select * from tasks where name = ? and job_id = ?");
			prepare.setString(1, taskname);
			prepare.setInt(2, project_id);
			ResultSet result = prepare.executeQuery();
			while (result.next()) {
				flag = true;
				break;
			}
		} catch (SQLException e) {
			System.out.println("Error checkTaskNameExisted:" + e.getMessage());
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					System.out.println("Lỗi đóng kết nối checkTaskNameExisted:"+e.getMessage());
				}
			if (prepare != null)
				try {
					prepare.close();
				} catch (SQLException e) {
					System.out.println("Lỗi đóng kết nối checkTaskNameExisted:"+e.getMessage());
				}
		}
		return flag;
}

	//lấy toàn bộ các Task của toàn bộ User, thuộc Project và Status chỉ định:
	public List<Task> getAllTasksByProjectAndStatus(int project_id,int status_id){
		List<Task> list = new ArrayList<Task>();
		Connection conn = null;
		PreparedStatement prepare = null;
		try {
			conn = JDBCConfig.getConnection();
			prepare = conn.prepareStatement("select * from tasks t where t.job_id = ? and status_id = ?");
			prepare.setInt(1, project_id);
			prepare.setInt(2, status_id);
			ResultSet result = prepare.executeQuery();
			while (result.next()) {
				list.add(new Task(result.getInt("id"), result.getString("name"), result.getDate("start_date"),
						result.getDate("end_date"), result.getInt("user_id"), result.getInt("job_id"),
						result.getInt("status_id")));
			}

		} catch (SQLException e) {
			System.out.println("Error getAllTasksByProjectAndStatus:" + e.getMessage());
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					System.out.println("Lỗi đóng kết nối getAllTasksByProjectAndStatus():" + e.getMessage());
				}
			if (prepare != null)
				try {
					prepare.close();
				} catch (SQLException e) {
					System.out.println("Lỗi đóng kết nối getAllTasksByProjectAndStatus():" + e.getMessage());
				}
		}
		return list;
	}
	
}
