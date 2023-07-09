package Respository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Config.JDBCConfig;
import model.Status;

public class StatusRespository {
	public List<Status> getAllStatus() {
		List<Status> list = new ArrayList<Status>();
		Connection conn = null;
		PreparedStatement prepare = null;
		try {
			conn = JDBCConfig.getConnection();
			prepare = conn.prepareStatement("select * from status");
			ResultSet result = prepare.executeQuery();
			while (result.next()) {
				list.add(new Status(result.getInt("id"),result.getString("name")));
			}
		} catch (SQLException e) {
			System.out.println("Error getAllStatus:" + e.getMessage());
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					System.out.println("Lỗi đóng kết nối getAllStatus:" + e.getMessage());
				}
			if (prepare != null)
				try {
					prepare.close();
				} catch (SQLException e) {
					System.out.println("Lỗi đóng kết nối getAllStatus:" + e.getMessage());
				}
		}
		return list;
	}
	
	public int getStatusIDFromStatusName(String name) {
		Connection conn = null;
		PreparedStatement prepareStatement = null;
		try {
			conn = JDBCConfig.getConnection();
			String sql = "select * from status where name = ?";
			prepareStatement = conn.prepareStatement(sql);
			prepareStatement.setString(1, name);
			ResultSet result = prepareStatement.executeQuery();
			while(result.next()) {
				return result.getInt("id");
			}
		}
		catch(Exception e) {
			System.out.println("Error getStatusIDFromStatusName():"+e.getMessage());
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
	
}
