package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import Controller.SelectHD;

import com.jgoodies.common.base.Strings;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import com.toedter.calendar.JCalendar;
import javax.swing.JTable;

public class HoaDonView extends JFrame {

	private JPanel contentPane;
	private JTextField tfSearch;
	private DefaultTableModel tableModel;
	private JTable table;
	private JLabel lbMaHD, lbMaKH, lbTenKH, lbMonth, lbCS_cu, lbCS_moi, lbLgDien, lbThue, lbTotal, lbStatus;
	private JButton btBack;
	
	private Vector vData= new Vector();
	private Vector vTitle= new Vector();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HoaDonView frame = new HoaDonView();
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
	public HoaDonView() {
		setBounds(100, 100, 934, 603);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setVisible(true);
		
		JPanel leftPanel = new JPanel();
		leftPanel.setBounds(15, 67, 324, 464);
		contentPane.add(leftPanel);
		leftPanel.setLayout(null);
		Border border = BorderFactory.createLineBorder(Color.black);
		TitledBorder title= BorderFactory.createTitledBorder(border,"Hoá đơn");
		leftPanel.setBorder(title);
		
		lbMaHD = new JLabel("Mã Hoá đơn:");
		lbMaHD.setBounds(15, 29, 115, 20);
		leftPanel.add(lbMaHD);
		
		lbMaKH = new JLabel("Mã Khách hàng:");
		lbMaKH.setBounds(15, 65, 115, 20);
		leftPanel.add(lbMaKH);
		
		lbMonth = new JLabel("Ngày tháng:");
		lbMonth.setBounds(15, 137, 115, 20);
		leftPanel.add(lbMonth);
		
		lbTenKH = new JLabel("Tên Khách hàng:");
		lbTenKH.setBounds(15, 101, 125, 20);
		leftPanel.add(lbTenKH);
		
		lbCS_cu = new JLabel("Chỉ số cũ:");
		lbCS_cu.setBounds(15, 173, 115, 20);
		leftPanel.add(lbCS_cu);
		
		lbCS_moi = new JLabel("Chỉ số mới:");
		lbCS_moi.setBounds(15, 209, 115, 20);
		leftPanel.add(lbCS_moi);
		
		lbLgDien = new JLabel("Lượng điện:");
		lbLgDien.setBounds(15, 245, 115, 20);
		leftPanel.add(lbLgDien);
		
		lbThue = new JLabel("Thuế");
		lbThue.setBounds(15, 281, 115, 20);
		leftPanel.add(lbThue);
		
		lbTotal = new JLabel("Tổng cộng:");
		lbTotal.setBounds(15, 317, 115, 20);
		leftPanel.add(lbTotal);
		
		lbStatus = new JLabel("Chưa thanh toán:");
		lbStatus.setBounds(15, 353, 125, 20);
		leftPanel.add(lbStatus);
		
		JLabel lbValueMaHD = new JLabel("null");
		lbValueMaHD.setBounds(163, 29, 106, 20);
		leftPanel.add(lbValueMaHD);
		
		JLabel lbValueMaKH = new JLabel("null");
		lbValueMaKH.setBounds(163, 65, 106, 20);
		leftPanel.add(lbValueMaKH);
		
		JLabel lbValueTenKH = new JLabel("null");
		lbValueTenKH.setBounds(163, 101, 106, 20);
		leftPanel.add(lbValueTenKH);
		
		JLabel lbValueMonth = new JLabel("null");
		lbValueMonth.setBounds(163, 137, 106, 20);
		leftPanel.add(lbValueMonth);
		
		JLabel lbValueCSC = new JLabel("null");
		lbValueCSC.setBounds(163, 173, 106, 20);
		leftPanel.add(lbValueCSC);
		
		JLabel lbValueCSM = new JLabel("null");
		lbValueCSM.setBounds(163, 209, 106, 20);
		leftPanel.add(lbValueCSM);
		
		JLabel lbValueLgDien = new JLabel("null");
		lbValueLgDien.setBounds(163, 245, 106, 20);
		leftPanel.add(lbValueLgDien);
		
		JLabel lbValueThue = new JLabel("null");
		lbValueThue.setBounds(163, 281, 106, 20);
		leftPanel.add(lbValueThue);
		
		JLabel lbValueTotal = new JLabel("null");
		lbValueTotal.setBounds(163, 317, 106, 20);
		leftPanel.add(lbValueTotal);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(354, 67, 543, 464);
		contentPane.add(scrollPane);
		
		reload();
		tableModel= new DefaultTableModel(vData, vTitle);
		table = new JTable(tableModel);
		scrollPane.setViewportView(table);
		
		tfSearch = new JTextField();
		tfSearch.setBounds(354, 25, 274, 38);
		contentPane.add(tfSearch);
		tfSearch.setColumns(10);
		
		JButton btSearch = new JButton("Search");
		btSearch.setBounds(757, 25, 140, 38);
		contentPane.add(btSearch);
		
		JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(632, 25, 119, 38);
		contentPane.add(dateChooser_1);
		
		btBack = new JButton("Back");
		btBack.setBounds(15, 30, 88, 29);
		btBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		contentPane.add(btBack);
	}
	
	public void reload() {
		try {
			vData.clear();
			vTitle.clear();
			SelectHD selectHD= new SelectHD();
			selectHD.execute();
			vData= selectHD.getvData();
			vTitle=selectHD.getvTitle();
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
}
