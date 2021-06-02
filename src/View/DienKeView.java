package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JButton;
import com.toedter.calendar.JDateChooser;

public class DienKeView extends JFrame {

	private JPanel contentPane;
	private JPanel leftJPanel;
	private JTextField tfMaDK;
	private JTextField tfCS_cu;
	private JTextField tfCS_moi;
	private JTextField tfMaKH;
	private JTable table;
	private DefaultTableModel defaultTableModel;
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
		
		JButton btUpdate = new JButton("Update");
		btUpdate.setBounds(141, 251, 98, 29);
		leftPanel.add(btUpdate);
		
		JButton btDelete = new JButton("Delete");
		btDelete.setBounds(254, 251, 98, 29);
		leftPanel.add(btDelete);
		
		JButton btnGetinfor = new JButton("GetInfor");
		btnGetinfor.setBounds(67, 296, 98, 29);
		leftPanel.add(btnGetinfor);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(201, 296, 98, 29);
		leftPanel.add(btnSearch);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(190, 66, 146, 26);
		leftPanel.add(dateChooser);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(396, 56, 501, 475);
		contentPane.add(scrollPane);
		
		TitledBorder titledBorder2 = BorderFactory.createTitledBorder(border, "Thông tin");
		scrollPane.setBorder(titledBorder2);
		
		defaultTableModel= new DefaultTableModel();
		table = new JTable(defaultTableModel);
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
		
	}
}
