package ie.ait.factory.entities;

import java.util.Date;
import java.util.List;

public class OrderRequest {

	private int id;
	private Date date;
	private Employee employee;
	private Department department;
	private String status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<OrderRequestProduct> getOrderRequestProducts() {
		return orderRequestProducts;
	}

	public void setOrderRequestProducts(List<OrderRequestProduct> orderRequestProducts) {
		this.orderRequestProducts = orderRequestProducts;
	}

	private List<OrderRequestProduct> orderRequestProducts;

	@Override
	public String toString() {
		return "OrderRequest [id=" + id + ", date=" + date + "," + employee + "," + department + ", status="
				+ status + "," + orderRequestProducts + "]";
	}
}
