package ie.ait.factory.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;

import ie.ait.factory.DatabaseUtils;
import ie.ait.factory.dao.OrderRequestDao;
import ie.ait.factory.entities.OrderRequest;

class OrderRequestDaoTest {

	@Test
	void testGetExportAll() throws SQLException {
		Connection cn = DatabaseUtils.getConnection();
		OrderRequestDao dao = new OrderRequestDao(cn);
		List<OrderRequest> list = dao.getExportAll();
		assertEquals(10, list.size());
		OrderRequest rt = list.get(0);
		assertEquals(180802, rt.getId());
	}

	@Test
	void testOrderRequestByDepartment() throws SQLException {
		Connection cn = DatabaseUtils.getConnection();
		OrderRequestDao dao = new OrderRequestDao(cn);
		List<OrderRequest> list = dao.getOrderRequestByDepartment("Sales");
		assertEquals(5, list.size());
		assertEquals("Sales", list.get(0).getDepartment().getName());

		List<OrderRequest> listIt = dao.getOrderRequestByDepartment("IT");
		assertEquals(0, listIt.size());

		List<OrderRequest> listEn = dao.getOrderRequestByDepartment("Engineering");
		assertEquals(3, listEn.size());
		assertEquals("Engineering", listEn.get(0).getDepartment().getName());

	}

	@Test
	void testOrderRequestByProductId() throws SQLException {
		Connection cn = DatabaseUtils.getConnection();
		OrderRequestDao dao = new OrderRequestDao(cn);
		List<OrderRequest> list = dao.getOrderRequestByProductId("1102");
		assertEquals(1, list.size());
		assertEquals(1102, list.get(0).getOrderRequestProducts().get(0).getProduct().getId());

	}

	@Test
	void testOrderRequestByRequestId() throws SQLException {
		Connection cn = DatabaseUtils.getConnection();
		OrderRequestDao dao = new OrderRequestDao(cn);
		List<OrderRequest> list = dao.getOrderRequestByRequestId("191002");
		assertEquals(1, list.size());
		assertEquals(191002, list.get(0).getId());
		assertEquals(1, list.get(0).getOrderRequestProducts().size());
	}

	@Test
	void testOrderRequestByStatus() throws SQLException {
		Connection cn = DatabaseUtils.getConnection();
		OrderRequestDao dao = new OrderRequestDao(cn);
		List<OrderRequest> listOK = dao.getOrderRequestByStatus("OK");
		assertEquals(7, listOK.size());
		assertEquals("OK", listOK.get(0).getStatus());
		List<OrderRequest> listNOK = dao.getOrderRequestByStatus("NOK");
		assertEquals(3, listNOK.size());
		assertEquals("NOK", listNOK.get(0).getStatus());
	}

	@Test
	void testTotalRequest() throws SQLException {
		Connection cn = DatabaseUtils.getConnection();
		OrderRequestDao dao = new OrderRequestDao(cn);
		int total = dao.getTotalRequest();
		assertEquals(11, dao.getTotalRequest());
	}
	@Ignore
	@Test
	void testDelete() throws SQLException {
		Connection cn = DatabaseUtils.getConnection();
		OrderRequestDao dao = new OrderRequestDao(cn);
		int delete = dao.deleteOrderRequestByRequestId(180701);
		assertEquals(1, delete);
	}
}
