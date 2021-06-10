package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.nio.channels.AcceptPendingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import Controller.GetBill;
import Controller.SearchHD;
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
	
	private JLabel lbValueTemp;
	private JLabel lbValueLoaiDien;
	private JLabel lbValueDonGia;
	private JLabel lbValueTotal;
	private JLabel lbValueThue;
	private JLabel lbValueLgDien;
	private JLabel lbValueMonth;
	private JLabel lbValueCSC;
	private JLabel lbValueCSM;
	private JLabel lbValueTenKH;
	private JLabel lbValueMaKH;
	private JLabel lbValueMaHD;
	
	private Vector vData= new Vector();
	private Vector vTitle= new Vector();
	private JButton btPay;
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
		lbMaKH.setBounds(15, 54, 115, 20);
		leftPanel.add(lbMaKH);
		
		lbMonth = new JLabel("Ngày tháng:");
		lbMonth.setBounds(15, 101, 115, 20);
		leftPanel.add(lbMonth);
		
		lbTenKH = new JLabel("Tên Khách hàng:");
		lbTenKH.setBounds(15, 79, 125, 20);
		leftPanel.add(lbTenKH);
		
		lbCS_cu = new JLabel("Chỉ số cũ:");
		lbCS_cu.setBounds(15, 133, 115, 20);
		leftPanel.add(lbCS_cu);
		
		lbCS_moi = new JLabel("Chỉ số mới:");
		lbCS_moi.setBounds(15, 159, 115, 20);
		leftPanel.add(lbCS_moi);
		
		lbLgDien = new JLabel("Lượng điện:");
		lbLgDien.setBounds(15, 185, 115, 20);
		leftPanel.add(lbLgDien);
		
		lbThue = new JLabel("Thuế:");
		lbThue.setBounds(15, 293, 115, 20);
		leftPanel.add(lbThue);
		
		lbTotal = new JLabel("Tổng cộng:");
		lbTotal.setBounds(15, 329, 115, 20);
		leftPanel.add(lbTotal);
		
		lbStatus = new JLabel("Chưa thanh toán:");
		lbStatus.setBounds(15, 357, 125, 20);
		leftPanel.add(lbStatus);
		
		lbValueMaHD = new JLabel("null");
		lbValueMaHD.setBounds(163, 29, 106, 20);
		leftPanel.add(lbValueMaHD);
		
		lbValueMaKH = new JLabel("null");
		lbValueMaKH.setBounds(163, 54, 106, 20);
		leftPanel.add(lbValueMaKH);
		
		lbValueTenKH = new JLabel("null");
		lbValueTenKH.setBounds(163, 79, 106, 20);
		leftPanel.add(lbValueTenKH);
		
		lbValueMonth = new JLabel("null");
		lbValueMonth.setBounds(163, 101, 106, 20);
		leftPanel.add(lbValueMonth);
		
		lbValueCSC = new JLabel("null");
		lbValueCSC.setBounds(163, 133, 106, 20);
		leftPanel.add(lbValueCSC);
		
		lbValueCSM = new JLabel("null");
		lbValueCSM.setBounds(163, 159, 106, 20);
		leftPanel.add(lbValueCSM);
		
		lbValueLgDien = new JLabel("null");
		lbValueLgDien.setBounds(163, 185, 106, 20);
		leftPanel.add(lbValueLgDien);
		
		lbValueThue = new JLabel("null");
		lbValueThue.setBounds(163, 293, 106, 20);
		leftPanel.add(lbValueThue);
		
		lbValueTotal = new JLabel("null");
		lbValueTotal.setBounds(163, 329, 106, 20);
		leftPanel.add(lbValueTotal);
		
		btPay = new JButton("Thanh toán");
		btPay.setBounds(103, 393, 115, 29);
		btPay.setEnabled(false);
		leftPanel.add(btPay);
		
		JLabel lbDonGia = new JLabel("Đơn giá:");
		lbDonGia.setBounds(15, 217, 115, 20);
		leftPanel.add(lbDonGia);
		
		JLabel lbLoaiDien = new JLabel("Loại điện:");
		lbLoaiDien.setBounds(15, 245, 115, 20);
		leftPanel.add(lbLoaiDien);
		
		JLabel lbTemp = new JLabel("Tạm tính:");
		lbTemp.setBounds(15, 268, 115, 20);
		leftPanel.add(lbTemp);
		
		lbValueDonGia = new JLabel("null");
		lbValueDonGia.setBounds(163, 217, 106, 20);
		leftPanel.add(lbValueDonGia);
		
		lbValueLoaiDien = new JLabel("null");
		lbValueLoaiDien.setBounds(163, 245, 106, 20);
		leftPanel.add(lbValueLoaiDien);
		
		lbValueTemp = new JLabel("null");
		lbValueTemp.setBounds(163, 268, 106, 20);
		leftPanel.add(lbValueTemp);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(354, 67, 543, 464);
		contentPane.add(scrollPane);
		
		reload();
		tableModel= new DefaultTableModel(vData, vTitle);
		table = new JTable(tableModel);
		scrollPane.setViewportView(table);
		table.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
//				click double to get bill
				if(e.getClickCount() == 2) {
					getBill();
				}
			}
		});
		
		
		tfSearch = new JTextField();
		tfSearch.setBounds(354, 25, 274, 38);
		contentPane.add(tfSearch);
		tfSearch.setColumns(10);
		
		JButton btSearch = new JButton("Search");
		btSearch.setBounds(757, 25, 140, 38);
		contentPane.add(btSearch);
		btSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				seaarch();
			}
		});
		
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
	
	public void seaarch() {
		SearchHD searchHD = new SearchHD();
		searchHD.execute(tfSearch.getText());
		vData= searchHD.getvData();
		vTitle= searchHD.getvTitle();
		tableModel = new DefaultTableModel(vData, vTitle);
		table.setModel(tableModel);
	}
	public void getBill() {
		try {
			int select = table.getSelectedRow();
			Vector rowSelected = new Vector();
			rowSelected = (Vector) vData.elementAt(select);
			String madk= rowSelected.elementAt(5).toString();
			String makh= rowSelected.elementAt(1).toString();
			String month= rowSelected.elementAt(2).toString();
			ArrayList<String> dataGetBill = new ArrayList<String>();
			dataGetBill.add(madk);
			dataGetBill.add(makh);
			dataGetBill.add(month);
			
			GetBill getBill = new GetBill(dataGetBill);
			getBill.execute();
			//lay du lieu cap nhat vao view
			dataGetBill= getBill.getDataGetBill();
			System.out.println(dataGetBill.toString());
			
			calculator(dataGetBill);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void calculator(ArrayList<String> data){ {
		lbValueMaHD.setText(data.get(0));
		lbValueMaKH.setText(data.get(1));
		lbValueTenKH.setText(data.get(2));
		lbValueMonth.setText(data.get(3));
		lbValueCSC.setText(data.get(4));
		lbValueCSM.setText(data.get(5));
		lbValueDonGia.setText(data.get(6) +" VND");
		lbValueLoaiDien.setText(data.get(7));
		int check=Integer.parseInt(data.get(8));
		if(check==0) {
//			kiem tra hoa don da thanh toan hay chua
			lbStatus.setText("Chưa thanh toán!");
			btPay.setEnabled(true);
		}
		else {
			lbStatus.setText("Đã Thanh toán!");
			btPay.setEnabled(false);
		}
		
		int cs_cu= Integer.parseInt(data.get(4));
		int cs_moi= Integer.parseInt(data.get(5));
		float dongia= Float.parseFloat(data.get(6));
		int luongdien= cs_moi - cs_cu;
		lbValueLgDien.setText(luongdien+"");
		float temp= luongdien*dongia;
		float thue= 0.1f*temp;
		lbValueTemp.setText(temp+" VND");
		lbValueThue.setText((thue) +" VND");
		float total = temp + thue;
		lbValueTotal.setText(total +" VND");
	}
		
	}
}
