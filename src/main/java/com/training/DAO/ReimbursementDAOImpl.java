package com.training.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementDAOImpl implements ReimbursementDAO {

	Connection connection = PostgresConnection.getConnection();

	@Override
	public boolean addUser(User user) {
		PreparedStatement stmt;

		final String sql = "INSERT INTO p1_users (first_name, last_name, username, password, is_manager) VALUES(?, ?, ?, ?, ?);";
		int rows = 0;
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, user.getFirstName());
			stmt.setString(2, user.getLastName());
			stmt.setString(3, user.getUsername());
			stmt.setString(4, user.getPassword());
			stmt.setBoolean(5, user.isManager());
			rows = stmt.executeUpdate();
		} catch (SQLException e) {
			// duplicate username throws an exception
			return false;
		}
		return rows == 1;
	}

	@Override
	public boolean submitRequest(ReimbursementRequest rr) {
		PreparedStatement stmt;
		final String sql = "INSERT INTO p1_requests (userid, amount, category, details, status) VALUES(?, ?, ?, ?, ?);";
		int rows = 0;
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1,  rr.getUserId());
			stmt.setInt(2,  rr.getAmount());
			stmt.setString(3, rr.getCategory());
			stmt.setString(4,  rr.getDetails());
			stmt.setInt(5, rr.getStatus().ordinal());
			rows = stmt.executeUpdate();
		} catch (SQLException e) {
			return false;
		}
		return rows == 1;
	}

	@Override
	public User validate(Credentials credentials) {
		PreparedStatement stmt;

		final String sql = "SELECT * FROM p1_users WHERE username=? AND password=?";
		User user = null;
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, credentials.getUsername());
			stmt.setString(2, credentials.getPassword());
			ResultSet result = stmt.executeQuery();
			if (result.next()) {
				user = new User(result.getInt("userid"), result.getString("first_name"), result.getString("last_name"),
						result.getString("username"), result.getString("password"), result.getBoolean("is_manager"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return user;
	}

	@Override
	public List<ReimbursementRequest> getPendingRequests(User user) {
		PreparedStatement stmt;
		
		final String sql = "SELECT * FROM p1_requests WHERE userid=? AND status=0;";
		List<ReimbursementRequest> requests = new ArrayList<ReimbursementRequest>();
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, user.getUserId());
			ResultSet result = stmt.executeQuery();
			while (result.next()) {
				requests.add(new ReimbursementRequest(
						result.getInt("requestid"),
						result.getInt("userid"),
						result.getInt("amount"),
						result.getString("category"),
						result.getString("details"),
						Status.values()[result.getInt("status")]));
						
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return requests;
	}

	@Override
	public List<ReimbursementRequest> getResolvedRequests(User user) {
		PreparedStatement stmt;

		final String sql = "SELECT * FROM p1_requests WHERE userid = ? AND status != 0;";
		List<ReimbursementRequest> requests = new ArrayList<ReimbursementRequest>();
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1,  user.getUserId());
			ResultSet result = stmt.executeQuery();
			while (result.next()) {
				requests.add(new ReimbursementRequest(
						result.getInt("requestid"),
						result.getInt("userid"),
						result.getInt("amount"),
						result.getString("category"),
						result.getString("details"),
						Status.values()[result.getInt("status")]));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return requests;
	}

	@Override
	public boolean updateUser(User user) {
		final String sql = "UPDATE p1_users SET first_name=?, last_name=? WHERE userid=?;";
		int rows = 0;
		try {
			PreparedStatement stmt;
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, user.getFirstName());
			stmt.setString(2, user.getLastName());
			stmt.setInt(3,  user.getUserId());
			rows = stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
		return rows == 1;
	}

	@Override
	public boolean updatePassword(User user) {
		final String sql = "UPDATE p1_users SET password=? WHERE userid=?;";
		int rows = 0;
		try {
			PreparedStatement stmt;
			stmt = connection.prepareStatement(sql);
			stmt.setString(1,  user.getPassword());
			stmt.setInt(2, user.getUserId());
			rows = stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
		return rows == 1;
	}

	@Override
	public List<User> getEmployees() {
		final String sql = "SELECT * FROM p1_users WHERE is_manager=FALSE;";
		List<User> users = new ArrayList<User>();
		try {
			Statement stmt = connection.createStatement();
			ResultSet result = stmt.executeQuery(sql);
			while (result.next()) {
				users.add(new User(
						result.getInt("userid"),
						result.getString("first_name"),
						result.getString("last_name"),
						result.getString("username"),
						result.getString("password"),
						result.getBoolean("is_manager")));
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return users;
	}

	@Override
	public List<ReimbursementRequest> getAllPending() {
		final String sql = "SELECT * FROM p1_requests WHERE status=0";
		List<ReimbursementRequest> requests = new ArrayList<ReimbursementRequest>();
		try {
			Statement stmt = connection.createStatement();
			ResultSet result = stmt.executeQuery(sql);
			while (result.next()) {
				requests.add(new ReimbursementRequest(
						result.getInt("requestid"),
						result.getInt("userid"),
						result.getInt("amount"),
						result.getString("category"),
						result.getString("details"),
						Status.values()[result.getInt("status")]));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return requests;
	}

	@Override
	public boolean approveRequests(String[] values) {
		final String sql = "UPDATE p1_requests SET status=1 WHERE requestid=?;";
		int rows = 0;
		for (String value : values) {
			try {
				PreparedStatement stmt;
				stmt = connection.prepareStatement(sql);
				stmt.setInt(1, Integer.parseInt(value));
				rows += stmt.executeUpdate();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				return false;
			}
		}
		return rows == values.length;
	}

	@Override
	public boolean denyRequests(String[] values) {
		final String sql = "UPDATE p1_requests SET status=2 WHERE requestid=?;";
		int rows = 0;
		for (String value : values) {
			try {
				PreparedStatement stmt;
				stmt = connection.prepareStatement(sql);
				stmt.setInt(1, Integer.parseInt(value));
				rows += stmt.executeUpdate();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				return false;
			}
		}
		return rows == values.length;
	}

	@Override
	public List<ReimbursementRequest> requestsByUserid(int userid) {
		PreparedStatement stmt;
		
		final String sql = "SELECT * FROM p1_requests WHERE userid=?;";
		List<ReimbursementRequest> requests = new ArrayList<ReimbursementRequest>();
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, userid);
			ResultSet result = stmt.executeQuery();
			while (result.next()) {
				requests.add(new ReimbursementRequest(
						result.getInt("requestid"),
						result.getInt("userid"),
						result.getInt("amount"),
						result.getString("category"),
						result.getString("details"),
						Status.values()[result.getInt("status")]));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return requests;
	}

	@Override
	public List<ReimbursementRequest> getAllResolvedRequests() {
		final String sql = "SELECT * FROM p1_requests WHERE status != 0;";
		List<ReimbursementRequest> requests = new ArrayList<ReimbursementRequest>();
		try {
			Statement stmt = connection.createStatement();
			ResultSet result = stmt.executeQuery(sql);
			while (result.next()) {
				requests.add(new ReimbursementRequest(
						result.getInt("requestid"),
						result.getInt("userid"),
						result.getInt("amount"),
						result.getString("category"),
						result.getString("details"),
						Status.values()[result.getInt("status")]));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return requests;
	}
}
