package ie.ait.factory.gui;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import ie.ait.factory.DatabaseUtils;
import ie.ait.factory.dao.OrderRequestDao;
import ie.ait.factory.entities.OrderRequest;

import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Color;
import javax.swing.JTable;

public class ApplicationGui {

	private static final String[] TABLE_COLUMNS = { "ID", "Date", "First Name", "Department", "Product/Quantity",
			"Status" };
	private static Connection DB_CONNECTION = DatabaseUtils.getConnection();
	private OrderRequestDao dao = new OrderRequestDao(DB_CONNECTION);

	private JFrame factorySystem;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;

	// Launch the application
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ApplicationGui window = new ApplicationGui();
					window.factorySystem.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Create the application
	public ApplicationGui() {
		initialize();
	}

	// Initialize the contents of the frame
	private void initialize() {
		// Automatic GUI generated code starts here
		factorySystem = new JFrame();
		factorySystem.setTitle("Factory System");
		factorySystem.setBounds(100, 100, 1027, 704);
		factorySystem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		factorySystem.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.out.println("Closing DB connection...");
				try {
					DB_CONNECTION.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});

		JComboBox comboBox = new JComboBox();
		comboBox.setForeground(Color.BLACK);
		comboBox.setFont(new Font("Arial", Font.PLAIN, 11));

		JLabel lblGetOrderRequests = new JLabel("Get Order Request by Department");
		lblGetOrderRequests.setForeground(Color.DARK_GRAY);
		lblGetOrderRequests.setFont(new Font("Arial Black", Font.BOLD, 12));

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setForeground(Color.DARK_GRAY);
		btnSubmit.setFont(new Font("Arial Narrow", Font.BOLD, 11));
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getOrderRequestByDepartment((String) comboBox.getSelectedItem());
			}
		});

		JSeparator separator = new JSeparator();
		// department to choose
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "-", "Sales", "IT", "Human Resources", "Management",
				"Engineering", "Technical Assistance", "Financial", "Production Control" }));

		JLabel label = new JLabel("");

		JLabel lblNewLabel = new JLabel("Export All Requests");// create button : export all
		lblNewLabel.setForeground(Color.DARK_GRAY);
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 12));

		JButton btnNewButton = new JButton("Submit");
		btnNewButton.setForeground(Color.DARK_GRAY);
		btnNewButton.setFont(new Font("Arial Narrow", Font.BOLD, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getAllRequests();
			}
		});

		JLabel lblNewLabel_1 = new JLabel("Get Order Request by Product Id");// create button: get order request by
																				// product id
		lblNewLabel_1.setForeground(Color.DARK_GRAY);
		lblNewLabel_1.setFont(new Font("Arial Black", Font.BOLD, 12));

		textField = new JTextField();
		textField.setColumns(10);

		JButton btnNewButton_1 = new JButton("Submit");
		btnNewButton_1.setForeground(Color.DARK_GRAY);
		btnNewButton_1.setFont(new Font("Arial Narrow", Font.BOLD, 11));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getOrderRequestByProductId(textField.getText());
			}
		});

		JSeparator separator_2 = new JSeparator();

		JLabel lblGetOrderRequest = new JLabel("Get Order Request by Request Id");// create button: get order request by
																					// request id
		lblGetOrderRequest.setForeground(Color.DARK_GRAY);
		lblGetOrderRequest.setFont(new Font("Arial Black", Font.BOLD, 12));

		textField_1 = new JTextField();
		textField_1.setColumns(10);

		JButton btnSubmit_1 = new JButton("Submit");
		btnSubmit_1.setForeground(Color.DARK_GRAY);
		btnSubmit_1.setFont(new Font("Arial Narrow", Font.BOLD, 11));
		btnSubmit_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getOrderRequestByRequestId(textField_1.getText());
			}
		});

		JLabel lblNewLabel_2 = new JLabel("Get Total Requests"); // create button: get total request
		lblNewLabel_2.setForeground(Color.DARK_GRAY);
		lblNewLabel_2.setFont(new Font("Arial Black", Font.BOLD, 12));

		JButton btnNewButton_2 = new JButton("Submit");
		btnNewButton_2.setForeground(Color.DARK_GRAY);
		btnNewButton_2.setFont(new Font("Arial Narrow", Font.BOLD, 11));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getTotalRequests();
			}
		});

		JLabel lblNewLabel_3 = new JLabel("Get Order Request by Status");// create button: get order request by status
		lblNewLabel_3.setForeground(Color.DARK_GRAY);
		lblNewLabel_3.setFont(new Font("Arial Black", Font.BOLD, 12));

		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setForeground(Color.BLACK);
		comboBox_1.setFont(new Font("Arial", Font.PLAIN, 11));
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] { "-", "OK", "NOK" }));

		JButton btnNewButton_3 = new JButton("Submit");
		btnNewButton_3.setForeground(Color.DARK_GRAY);
		btnNewButton_3.setFont(new Font("Arial Narrow", Font.BOLD, 11));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getOrderRequestByStatus((String) comboBox_1.getSelectedItem());
			}
		});

		JSeparator separator_1 = new JSeparator();

		JScrollPane scrollPane_1 = new JScrollPane();
		
		JButton btnDelete = new JButton("Delete");// create button: get order request by status
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(table.getColumnCount() >1 && table.getSelectedRow()>=0) {
					Object value = table.getModel().getValueAt(table.getSelectedRow(), 0);
					deleteOrderRequestByRequestId((int) value);//cast
					
					DefaultTableModel dm = (DefaultTableModel) table.getModel();
					dm.removeRow(table.getSelectedRow());
				}
			}
			
		});

		GroupLayout groupLayout = new GroupLayout(factorySystem.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 817, Short.MAX_VALUE)
							.addGap(49)
							.addComponent(btnDelete)
							.addGap(62))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label)
							.addContainerGap(997, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnNewButton))
							.addPreferredGap(ComponentPlacement.RELATED, 333, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnNewButton_1))
							.addGap(192))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(separator, GroupLayout.DEFAULT_SIZE, 985, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblGetOrderRequests)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnSubmit))
							.addPreferredGap(ComponentPlacement.RELATED, 283, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnSubmit_1)
								.addComponent(lblGetOrderRequest)
								.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(220))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(separator_1, GroupLayout.DEFAULT_SIZE, 985, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(separator_2, GroupLayout.DEFAULT_SIZE, 985, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnNewButton_3))
									.addGap(384))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel_3)
									.addGap(300)))
							.addPreferredGap(ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_2)
								.addComponent(btnNewButton_2))
							.addGap(318))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(label)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_1)
								.addComponent(lblNewLabel))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnNewButton))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton_1)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblGetOrderRequests)
						.addComponent(lblGetOrderRequest))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnSubmit)
							.addGap(25))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnSubmit_1)
							.addGap(18)))
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(lblNewLabel_2))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton_3))
						.addComponent(btnNewButton_2))
					.addGap(27)
					.addComponent(separator_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
							.addGap(45))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnDelete)
							.addContainerGap())))
		);

		DefaultTableModel tableModel = new DefaultTableModel(TABLE_COLUMNS, 0);
		table = new JTable(tableModel);

		scrollPane_1.setViewportView(table);
		factorySystem.getContentPane().setLayout(groupLayout);

		JMenuBar menuBar = new JMenuBar();
		factorySystem.setJMenuBar(menuBar);

		JMenu mnPurchasing = new JMenu("Purchasing > Order Request");
		menuBar.add(mnPurchasing);
		// Automatic GUI generated code ends here
	}

	private void getOrderRequestByDepartment(String department) {
		List<OrderRequest> orderRequestdp = dao.getOrderRequestByDepartment(department);
		populateTable(orderRequestdp);
	}

	private void getAllRequests() {
		List<OrderRequest> list = dao.getExportAll();
		populateTable(list);
	}

	private void getOrderRequestByProductId(String id) {
		List<OrderRequest> orderRequestProductId = dao.getOrderRequestByProductId(id);
		populateTable(orderRequestProductId);
	}

	private void getOrderRequestByRequestId(String id) {
		List<OrderRequest> orderRequestByRequestId = dao.getOrderRequestByRequestId(id);
		populateTable(orderRequestByRequestId);
	}

	private void getTotalRequests() {
		int total = dao.getTotalRequest();
		deleteAllRows();
		Object[] data = { total };
		DefaultTableModel dm = (DefaultTableModel) table.getModel();
		dm.setColumnIdentifiers(new String[] { "Total" });
		dm.addRow(data);
	}

	private void getOrderRequestByStatus(String st) {
		List<OrderRequest> orderRequestStatus = dao.getOrderRequestByStatus(st);
		populateTable(orderRequestStatus);
	}

	private void deleteOrderRequestByRequestId(int requestId) {
		int rowsModified = dao.deleteOrderRequestByRequestId(requestId);
		System.out.println(rowsModified);

	}

	private void populateTable(List<OrderRequest> list) {
		deleteAllRows();
		DefaultTableModel dm = (DefaultTableModel) table.getModel();
		dm.setColumnIdentifiers(TABLE_COLUMNS);
		for (int i = 0; i < list.size(); i++) {
			OrderRequest orderRequest = list.get(i);
			Object[] data = { orderRequest.getId(), orderRequest.getDate(), orderRequest.getEmployee().getFirstName(),
					orderRequest.getDepartment().getName(), orderRequest.getOrderRequestProducts(),
					orderRequest.getStatus() };
			dm.addRow(data);
		}
	}

	private void deleteAllRows() {
		DefaultTableModel dm = (DefaultTableModel) table.getModel();
		for (int i = dm.getRowCount() - 1; i >= 0; i--) {
			dm.removeRow(i);
		}
	}
}
