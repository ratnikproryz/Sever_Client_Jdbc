package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JButton;
import com.toedter.calendar.JDateChooser;

import Controller.DeleteDK;
import Controller.InsertDK;
import Controller.SearchDK;
import Controller.SearchKH;
import Controller.SelectDK;
import Controller.SelectKH;
import Controller.UpdateDK;
import Controller.insertKH;

public class DienKeView extends JFrame {

	private JPanel contentPane;
	private JPanel leftJPanel;
	private JTextField tfMaDK;
	private JTextField tfCS_cu;
	private JTextField tfCS_moi;
	private JTextField tfMaKH;
	private JDateChooser dateChooser,dateSearch ;
	private JTable table;
	private DefaultTableModel defaultTableModel;
	private Vector vData= new Vector();
	private Vector vTitle= new Vector();
	private JTextField tfSearch;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DienKeView frame = new DienKeView();
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
	public DienKeView() {
		setBounds(100, 100, 934, 603);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setVisible(true);
		
		JPanel leftPanel = new JPanel();
		leftPanel.setBounds(15, 56, 366, 475);
		contentPane.add(leftPanel);
		Border border= BorderFactory.createLineBorder(Color.black);
		TitledBorder titledBorder= BorderFactory.createTitledBorder(border, "Điện kế");
		leftPanel.setBorder(titledBorder);
		leftPanel.setLayout(null);
		
		JLabel lbMaDK = new JLabel("Mã Điện kế");
		lbMaDK.setBounds(15, 36, 98, 20);
		leftPanel.add(lbMaDK);
		
		JLabel lbMonth = new JLabel("Ngày tháng");
		lbMonth.setBounds(15, 72, 98, 20);
		leftPanel.add(lbMonth);
		
		JLabel lbCS_cu = new JLabel("Chỉ số cũ");
		lbCS_cu.setBounds(15, 108, 69, 20);
		leftPanel.add(lbCS_cu);
		
		JLabel lbCS_moi = new JLabel("Chỉ số mới");
		lbCS_moi.setBounds(15, 144, 98, 20);
		leftPanel.add(lbCS_moi);
		
		JLabel lbMaKH = new JLabel("Mã Khách hàng");
		lbMaKH.setBounds(15, 180, 113, 20);
		leftPanel.add(lbMaKH);
		
		tfMaDK = new JTextField();
		tfMaDK.setBounds(190, 33, 146, 26);
		leftPanel.add(tfMaDK);
		tfMaDK.setColumns(10);
		
		tfCS_cu = new JTextField();
		tfCS_cu.setBounds(190, 105, 146, 26);
		leftPanel.add(tfCS_cu);
		tfCS_cu.setColumns(10);
		
		tfCS_moi = new JTextField();
		tfCS_moi.setBounds(190, 141, 146, 26);
		leftPanel.add(tfCS_moi);
		tfCS_moi.setColumns(10);
		
		tfMaKH = new JTextField();
		tfMaKH.setBounds(190, 177, 146, 26);
		leftPanel.add(tfMaKH);
		tfMaKH.setColumns(10);
		
		JButton btInsert = new JButton("Insert");
		btInsert.setBounds(15, 251, 98, 29);
		leftPanel.add(btInsert);
		btInsert.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				insert();
				System.out.println("insert");
			}
		});
		
		JButton btUpdate = new JButton("Update");
		btUpdate.setBounds(141, 251, 98, 29);
		leftPanel.add(btUpdate);
		btUpdate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				update();
			}
		});
		
		JButton btDelete = new JButton("Delete");
		btDelete.setBounds(254, 251, 98, 29);
		leftPanel.add(btDelete);
		btDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(190, 66, 146, 26);
		leftPanel.add(dateChooser);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(396, 56, 501, 475);
		contentPane.add(scrollPane);
		
		TitledBorder titledBorder2 = BorderFactory.createTitledBorder(border, "Thông tin");
		scrollPane.setBorder(titledBorder2);
		
		reload();
		defaultTableModel= new DefaultTableModel(vData, vTitle);
		table = new JTable(defaultTableModel);
		table.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
			@Override
			public void mousePressed(MouseEvent e) {
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					getInfor();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		scrollPane.setViewportView(table);
		
		JButton btBack = new JButton("Back");
		btBack.setBounds(15, 16, 88, 29);
		btBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		contentPane.add(btBack);
		
		tfSearch = new JTextField();
		tfSearch.setBounds(396, 12, 253, 36);
		contentPane.add(tfSearch);
		tfSearch.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				search();
			}
		});
		btnSearch.setBounds(799, 16, 98, 32);
		contentPane.add(btnSearch);
		
		dateSearch = new JDateChooser();
		dateSearch.setBounds(664, 12, 127, 36);
		contentPane.add(dateSearch);
		
	}
	
	public void reload() {
		try {
			vData.clear();
			vTitle.clear();
			SelectDK selectDK = new SelectDK();
			selectDK.execute();
			
			vData= selectDK.getvData();
			vTitle=selectDK.getvTitle();
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	public void getInfor() throws ParseException {
		int selectedRow= table.getSelectedRow();
		Vector row= (Vector)vData.elementAt(selectedRow);
		tfMaDK.setText(row.elementAt(0).toString());
		String dateValue= row.elementAt(1).toString();
		java.util.Date date= new SimpleDateFormat("yyyy-MM-dd").parse(dateValue);
		dateChooser.setDate(date);
		tfCS_cu.setText(row.elementAt(2).toString());
		tfCS_moi.setText(row.elementAt(3).toString());
		tfMaKH.setText(row.elementAt(4).toString());
	}
	
	public void insert() {
		Vector<String> dataInsert = new Vector<String>();
		dataInsert.add(tfMaDK.getText());
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String date = sdf.format(dateChooser.getDate());
		System.out.println(date);
		dataInsert.add(date);
		dataInsert.add(tfCS_cu.getText());
		dataInsert.add(tfCS_moi.getText());
		dataInsert.add(tfMaKH.getText());
		
		InsertDK insertDK = new InsertDK(dataInsert);
		insertDK.execute();
		reload();
		reload();
		defaultTableModel= new DefaultTableModel(vData, vTitle);
		table.setModel(defaultTableModel);
	}
	
	public void update() {
		Vector<String> dataUpdate = new Vector<String>();
		dataUpdate.add(tfMaDK.getText());
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String date = sdf.format(dateChooser.getDate());
		System.out.println(date);
		dataUpdate.add(date);
		dataUpdate.add(tfCS_cu.getText());
		dataUpdate.add(tfCS_moi.getText());
		dataUpdate.add(tfMaKH.getText());
		dataUpdate.add(tfMaDK.getText());
		UpdateDK updateDK = new UpdateDK(dataUpdate);
		updateDK.execute();
		
		reload();
		reload();
		defaultTableModel= new DefaultTableModel(vData, vTitle);
		table.setModel(defaultTableModel);
	}
	public void delete() {
		DeleteDK deleteDK = new DeleteDK();
		deleteDK.execute(tfMaDK.getText());
		System.out.println(tfMaDK.getText());
		reload();
		reload();
		defaultTableModel= new DefaultTableModel(vData, vTitle);
		table.setModel(defaultTableModel);
	}
	public void search() {
		Vector<String> dataSearch = new Vector<String>();
		dataSearch.add(tfSearch.getText());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(dateSearch.getDate());
		System.out.println(date);
		dataSearch.add(date);
		
		SearchDK searchDK = new SearchDK(dataSearch);
		searchDK.execute();
		vData= searchDK.getvData();
		vTitle= searchDK.getvTitle();

		defaultTableModel= new DefaultTableModel(vData, vTitle);
		table.setModel(defaultTableModel);
	}
}
