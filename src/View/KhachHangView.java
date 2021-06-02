package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.metal.OceanTheme;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import Controller.DAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class KhachHangView extends JFrame {
	DAO dao= new DAO();
	Connection connection= dao.getConnection();
	private JPanel contentPane;
	private JPanel leftJPanel;
	private JTextField tfMaKH;
	private JTextField tfTenKH;
	private JTextField tfCMND;
	private JTextField tfDiaChi;
	private JComboBox cbMDSD;
	private JLabel SDT;
	private JTextField tfSDT;
	private JButton btInsert, btDelete, btSearch, btUpdate, btGetInfor;
	private JScrollPane scrTable;
	private JTable table;
	private DefaultTableModel tableModel;
	private Vector vData= new Vector();
	private Vector vTitle= new Vector();
	private JButton btDK, btHD;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KhachHangView frame = new KhachHangView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public KhachHangView() {
		super("Khách hàng");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 934, 603);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel leftJPanel = new JPanel();
		//insert infor of Khach hang
		leftJPanel.setBounds(15, 49, 368, 482);
		contentPane.add(leftJPanel);
		Border leftBorder= BorderFactory.createLineBorder(Color.black);
		TitledBorder leftTitled= BorderFactory.createTitledBorder(leftBorder,"Khách Hàng");
		leftJPanel.setBorder(leftTitled);
		leftJPanel.setLayout(null);
		
		JLabel lbMaKH = new JLabel("Mã Khách hàng");
		lbMaKH.setBounds(15, 43, 117, 20);
		leftJPanel.add(lbMaKH);
		
		JLabel lbTenKH = new JLabel("Tên Khách hàng");
		lbTenKH.setBounds(15, 87, 117, 20);
		leftJPanel.add(lbTenKH);
		
		JLabel lbCMND = new JLabel("CMND");
		lbCMND.setBounds(15, 123, 117, 20);
		leftJPanel.add(lbCMND);
		
		JLabel lbDiaChi = new JLabel("Địa chỉ");
		lbDiaChi.setBounds(15, 166, 117, 20);
		leftJPanel.add(lbDiaChi);
		
		JLabel lbMaMDSD = new JLabel("Mã MDSD");
		lbMaMDSD.setBounds(15, 245, 117, 20);
		leftJPanel.add(lbMaMDSD);
		
		tfMaKH = new JTextField();
		tfMaKH.setBounds(182, 40, 146, 26);
		leftJPanel.add(tfMaKH);
		tfMaKH.setColumns(10);
		
		tfTenKH = new JTextField();
		tfTenKH.setBounds(182, 82, 146, 26);
		leftJPanel.add(tfTenKH);
		tfTenKH.setColumns(10);
		
		tfCMND = new JTextField();
		tfCMND.setBounds(182, 120, 146, 26);
		leftJPanel.add(tfCMND);
		tfCMND.setColumns(10);
		
		tfDiaChi = new JTextField();
		tfDiaChi.setBounds(182, 163, 146, 26);
		leftJPanel.add(tfDiaChi);
		tfDiaChi.setColumns(10);
		
		cbMDSD = new JComboBox();
		cbMDSD.setBounds(182, 242, 146, 26);
		cbMDSD.addItem("Gia đình");
		cbMDSD.addItem("Kinh doanh");
		leftJPanel.add(cbMDSD);
		
		SDT = new JLabel("SĐT");
		SDT.setBounds(15, 202, 69, 20);
		leftJPanel.add(SDT);
		
		tfSDT = new JTextField();
		tfSDT.setBounds(182, 205, 146, 26);
		leftJPanel.add(tfSDT);
		tfSDT.setColumns(10);
		
		btInsert = new JButton("Insert");
		btInsert.setBounds(15, 317, 100, 29);
		leftJPanel.add(btInsert);
		
		btUpdate = new JButton("Update");
		btUpdate.setBounds(134, 317, 100, 29);
		leftJPanel.add(btUpdate);
		
		btDelete = new JButton("Delete");
		btDelete.setBounds(253, 317, 100, 29);
		leftJPanel.add(btDelete);
		
		btGetInfor = new JButton("GetInfor");
		btGetInfor.setBounds(61, 362, 100, 29);
		leftJPanel.add(btGetInfor);
		
		btSearch = new JButton("Search");
		btSearch.setBounds(182, 362, 100, 29);
		leftJPanel.add(btSearch);
		
		
		scrTable = new JScrollPane();
		//view infor of khach hang
		scrTable.setBounds(390, 49, 508, 482);
		contentPane.add(scrTable);
		Border rightBorder = BorderFactory.createLineBorder(Color.black);
		TitledBorder rightTitle= BorderFactory.createTitledBorder(rightBorder, "Thông tin");
		scrTable.setBorder(rightTitle);
		
//		reload();
		tableModel= new DefaultTableModel(vData,vTitle);
		table = new JTable(tableModel)	;
		scrTable.setViewportView(table);
		
		btHD = new JButton("Hoá Đơn");
		btHD.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent arg0) {
				 new HoaDonView();
			}
		});
		btHD.setBounds(783, 16, 115, 29);
		contentPane.add(btHD);
		
		btDK = new JButton("Điện Kế");
		btDK.setBounds(653, 16, 115, 29);
		btDK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new DienKeView();
			}
		});
		contentPane.add(btDK);
		
		
	}
	
	public void reload() {
		try {
			vData.clear();
			vTitle.clear();
			java.sql.Statement statement = connection.createStatement();
			String sql="select * from khachhang";
			ResultSet resultSet= statement.executeQuery(sql);
			ResultSetMetaData resultSetMetaData=resultSet.getMetaData();
			int column= resultSetMetaData.getColumnCount();
			vTitle= new Vector(column);
			for(int i=1; i<column;i++) {
				vTitle.add(resultSetMetaData.getColumnLabel(i));
			}
			vData=new Vector();
			while (resultSet.next()) {
				Vector row= new Vector(column);
				for(int i=1; i<column; i++) {
					row.add(resultSet.getString(i));
				}
				vData.add(row);
			}
			resultSet.close();
			statement.close();
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
}
