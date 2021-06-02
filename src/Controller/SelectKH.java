package Controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JOptionPane;

import View.KhachHangView;

public class SelectKH implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private KhachHangView khachHangView;
	
	public SelectKH() {
		// TODO Auto-generated constructor stub
	}
	
	public SelectKH(KhachHangView khachHangView) {
		this.khachHangView = khachHangView;
	}
	
	public static void main(String[] args) {
		try {
			SelectKH selectKH = new SelectKH();
			selectKH.execute();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void execute() throws IOException, SQLException {
		String sql= "select * from khachhang";
		try {
			Client client = new Client(InetAddress.getLocalHost(), 6793);
			Socket socket= client.connectServer();
			DataOutputStream dos= new DataOutputStream(socket.getOutputStream());
			dos.writeUTF(sql);
			dos.flush();
			
			DataInputStream dis= new DataInputStream(socket.getInputStream());
			System.out.println(dis.readUTF());
			ObjectInputStream objInputStream = new ObjectInputStream(socket.getInputStream());
			
			try {
				ResultSet resultSet= (ResultSet) objInputStream.readObject();
				if(resultSet ==null) {
					System.out.println("Null");
				}else {
					System.out.println("Not null");
				}
				
				ResultSetMetaData resultSetMetaData=resultSet.getMetaData();
				int column= resultSetMetaData.getColumnCount();
				Vector vTitle= new Vector(column);
				for(int i=1; i<column;i++) {
					vTitle.add(resultSetMetaData.getColumnLabel(i));
					System.out.print(resultSetMetaData.getColumnLabel(i) +"\t");
				}
				Vector vData=new Vector();
				while (resultSet.next()) {
					System.out.println();
					Vector row= new Vector(column);
					for(int i=1; i<column; i++) {
						row.add(resultSet.getString(i));
						System.out.println(resultSet.getString(i)+"\t");
					}
					vData.add(row);
					
				}
				
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
		
	
	
	
}
