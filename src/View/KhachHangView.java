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
import Controller.SearchKH;
import Controller.SelectKH;
import Controller.UpdateKH;
import Controller.insertKH;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Enumeration;
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
	private JButton btInsert, btDelete, btSearch, btUpdate;
	private JScrollPane scrTable;
	private JTable table;
	private DefaultTableModel tableModel;
	private Vector vData= new Vector();
	private Vector vTitle= new Vector();
	private JButton btDK, btHD;
	private JTextField tfSearch;

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
		btInsert.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				insert();
			}
		});
		
		
		btUpdate = new JButton("Update");
		btUpdate.setBounds(134, 317, 100, 29);
		leftJPanel.add(btUpdate);
		btUpdate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				update();
				reload();
				Enumeration enumeration = vData.elements();
				while (enumeration.hasMoreElements()) {
					String string =enumeration.nextElement().toString();
					System.out.println(string);
				}
//				tableModel= new DefaultTableModel(vData, vTitle);
//				table.setModel(tableModel);
			}
		});
		
		btDelete = new JButton("Delete");
		btDelete.setBounds(253, 317, 100, 29);
		leftJPanel.add(btDelete);
		
		scrTable = new JScrollPane();
		//view infor of khach hang
		scrTable.setBounds(390, 49, 508, 482);
		contentPane.add(scrTable);
		Border rightBorder = BorderFactory.createLineBorder(Color.black);
		TitledBorder rightTitle= BorderFactory.createTitledBorder(rightBorder, "Thông tin");
		scrTable.setBorder(rightTitle);
		
		reload();
		tableModel= new DefaultTableModel(vData,vTitle);
		table = new JTable(tableModel)	;
		table.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
			}
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
//				getInfor();
				if(arg0.getClickCount()==2) {
					System.out.println("2");
					getInfor();
				}
			}
		});
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
		
		tfSearch = new JTextField();
		tfSearch.setBounds(15, 17, 248, 26);
		contentPane.add(tfSearch);
		tfSearch.setColumns(10);
		
		btSearch = new JButton("Search");
		btSearch.setBounds(278, 16, 100, 29);
		contentPane.add(btSearch);
		btSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				search();
			tableModel.fireTableStructureChanged();
			}
		});
		
		
	}
	
	public void reload() {
		try {
			SelectKH selectKH= new SelectKH();
			selectKH.execute();
			vData= selectKH.getvData();
			vTitle=selectKH.getvTitle();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	private void getInfor() {
		//lay hang duoc chon
		int selectedRow= table.getSelectedRow();
		Vector row= (Vector)vData.elementAt(selectedRow);
		tfMaKH.setText(row.elementAt(0).toString());
		tfTenKH.setText(row.elementAt(1).toString());
		tfCMND.setText(row.elementAt(2).toString());
		tfDiaChi.setText(row.elementAt(3).toString());		
		tfSDT.setText(row.elementAt(4).toString());
		
	}
	public void search() {
		
		SearchKH searchKH = new SearchKH();
		searchKH.execute(tfSearch.getText());
		vTitle= searchKH.getvTitle();
		vData= searchKH.getvData();
		tableModel = new DefaultTableModel(vData, vTitle);
		table.setModel(tableModel);
		
	}
	public void update() {
		Vector<String> dataUpdate= new Vector<String>();
		dataUpdate.add(tfMaKH.getText());
		dataUpdate.add(tfTenKH.getText());
		dataUpdate.add(tfCMND.getText());
		dataUpdate.add(tfDiaChi.getText());
		dataUpdate.add(tfSDT.getText());
		if(cbMDSD.getSelectedItem().toString().equals("Kinh doanh")) {
			dataUpdate.add("KD");
		}
		else {
			dataUpdate.add("GD");
		}
		dataUpdate.add(tfMaKH.getText());
		
		UpdateKH updateKH = new UpdateKH(dataUpdate);
		updateKH.execute();
		reload();
		reload();
		tableModel= new DefaultTableModel(vData, vTitle);
		table.setModel(tableModel);
	}
	
	public void insert() {
		Vector<String> dataUpdate= new Vector<String>();
		dataUpdate.add(tfMaKH.getText());
		dataUpdate.add(tfTenKH.getText());
		dataUpdate.add(tfCMND.getText());
		dataUpdate.add(tfDiaChi.getText());
		dataUpdate.add(tfSDT.getText());
		if(cbMDSD.getSelectedItem().toString().equals("Kinh doanh")) {
			dataUpdate.add("KD");
		}
		else {
			dataUpdate.add("GD");
		}
		insertKH insertKH = new insertKH(dataUpdate);
		insertKH.execute();
		
		vData.add(dataUpdate);
		tableModel.fireTableDataChanged();
	}
}
