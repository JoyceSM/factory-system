package ie.ait.factory.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ie.ait.factory.entities.Department;
import ie.ait.factory.entities.Employee;
import ie.ait.factory.entities.OrderRequest;
import ie.ait.factory.entities.OrderRequestProduct;
import ie.ait.factory.entities.Product;

public class OrderRequestDao {

	private Connection cn;
	private String query = "SELECT " + "rt.requestId AS 'Request ID', rt.requestDate AS 'Date', "
			+ "em.firstName AS 'First Name' , em.lastName AS 'Last Name', "
			+ "dp.departmentId AS 'Department ID', dp.name AS 'Department', "
			+ "rp.quantity AS 'Quantity', rp.productId AS 'Product ID', pt.description AS 'Description', rt.status AS 'Status' "
			+ "FROM orderRequest rt " + "INNER JOIN department dp ON rt.departmentId = dp.departmentId "
			+ "INNER JOIN employee em ON rt.employeeId = em.employeeId "
			+ "INNER JOIN orderRequestProduct rp ON rt.requestId = rp.requestId "
			+ "INNER JOIN product pt ON rp.productId = pt.productId ";

	public OrderRequestDao(Connection cn) {
		this.cn = cn;
	}

	public List<OrderRequest> getExportAll() {
		try {
			PreparedStatement stmt = cn.prepareStatement(query);
			return queryData(stmt);
		} catch (SQLException e) { // checked exception
			throw new RuntimeException(e); // unchecked exception
		}
	}

	public List<OrderRequest> getOrderRequestByDepartment(String name) {
		try {
			PreparedStatement stmt = cn.prepareStatement(query + "WHERE dp.name = ?;");
			stmt.setString(1, name);
			return queryData(stmt);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<OrderRequest> getOrderRequestByProductId(String id) {
		try {
			PreparedStatement stmt = cn.prepareStatement(query + "WHERE pt.productId = ?;");
			stmt.setString(1, id);
			return queryData(stmt);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<OrderRequest> getOrderRequestByRequestId(String id) {
		try {
			PreparedStatement stmt = cn.prepareStatement(query + " WHERE rp.RequestId = ?;");
			stmt.setString(1, id);
			return queryData(stmt);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<OrderRequest> getOrderRequestByStatus(String st) {
		try {
			PreparedStatement stmt = cn.prepareStatement(query + "WHERE rt.status = ?;");
			stmt.setString(1, st);
			return queryData(stmt);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public int getTotalRequest() {
		int totalRequest = 0;
		try {
			PreparedStatement stmt = cn.prepareStatement("SELECT COUNT(*) AS 'Total Requests' FROM orderRequest;");
			ResultSet rs = stmt.executeQuery();
			rs.next();
			totalRequest = rs.getInt("Total Requests");
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return totalRequest;
	}

	private List<OrderRequest> queryData(PreparedStatement stmt) throws SQLException {
		ResultSet rs = stmt.executeQuery();
		Map<Integer, OrderRequest> map = new HashMap<Integer, OrderRequest>();

		while (rs.next()) {
			OrderRequest rt = extract(rs);
			if (!map.containsKey(rt.getId())) { // is this request new?
				map.put(rt.getId(), rt);
			} else { // otherwise, aggregate the products
				OrderRequest value = map.get(rt.getId());
				value.getOrderRequestProducts().addAll(rt.getOrderRequestProducts()); // add all the products to the
																						// existing request
			}
		}
		rs.close();
		stmt.close();
		List<OrderRequest> list = new ArrayList<OrderRequest>(map.values());
		return list;
	}

	private OrderRequest extract(ResultSet rs) throws SQLException {
		int requestId = rs.getInt("Request ID");
		Date date = rs.getDate("Date");
		String firstName = rs.getString("First Name");
		String lastName = rs.getString("Last Name");
		String departmentId = rs.getString("Department ID");
		String departmentName = rs.getString("Department");
		int quantity = rs.getInt("Quantity");
		int productId = rs.getInt("Product ID");
		String description = rs.getString("Description");
		String status = rs.getString("Status");
		Employee emp = new Employee();
		emp.setFirstName(firstName);
		emp.setLastName(lastName);
		Department dp = new Department();
		dp.setId(departmentId);
		dp.setName(departmentName);
		Product pt = new Product();
		pt.setId(productId);
		pt.setDescription(description);
		OrderRequestProduct rp = new OrderRequestProduct();
		rp.setProduct(pt);
		rp.setQuantity(quantity);
		OrderRequest rt = new OrderRequest();
		rt.setDate(date);
		rt.setId(requestId);
		rt.setEmployee(emp);
		rt.setDepartment(dp);
		rt.setStatus(status);
		List<OrderRequestProduct> orpList = new ArrayList<OrderRequestProduct>();
		orpList.add(rp);
		rt.setOrderRequestProducts(orpList);
		return rt;
	}
	
	public int deleteOrderRequestByRequestId(int requestId) {
		
		try {
			PreparedStatement stmt = cn.prepareStatement("DELETE FROM orderRequest WHERE requestId = ?;");
			stmt.setInt(1, requestId);
			int rowsModified = stmt.executeUpdate();
			return rowsModified;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;

	}

}// end of class
