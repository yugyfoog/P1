package com.training.DAO;

import java.util.List;

public interface ReimbursementDAO {

	boolean addUser(User user);
	
	User validate(Credentials credentials);
	
	boolean submitRequest(ReimbursementRequest reimbursementRequest);
	
	List<ReimbursementRequest> getPendingRequests(User user);
	
	List<ReimbursementRequest> getResolvedRequests(User user);

	boolean updateUser(User user);

	boolean updatePassword(User user);
	
	List<User> getEmployees();
	
	List<ReimbursementRequest> getAllPending();
	
	boolean approveRequests(String[] values);
	
	boolean denyRequests(String[] values);
	
	List<ReimbursementRequest> requestsByUserid(int userid);

	List<ReimbursementRequest> getAllResolvedRequests();
}
