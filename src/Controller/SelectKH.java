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
	private Vector vData= new Vector();
	private Vector vTitle= new Vector();
	
	public SelectKH() {
		// TODO Auto-generated constructor stub
	}
	
	public Vector getvData() {
		return vData;
	}

	public void setvData(Vector vData) {
		this.vData = vData;
	}

	public Vector getvTitle() {
		return vTitle;
	}

	public void setvTitle(Vector vTitle) {
		this.vTitle = vTitle;
	}
	
	public void execute(){
		String sql= "select * from khachhang";
		try {
			Client client = new Client(InetAddress.getLocalHost(), 6793);
			Socket socket= client.connectServer();
			DataOutputStream dos= new DataOutputStream(socket.getOutputStream());
			dos.writeUTF(sql);
			dos.flush();
//			DataInputStream dis= new DataInputStream(socket.getInputStream());
			ObjectInputStream objInputStream = new ObjectInputStream(socket.getInputStream());
//			
			vTitle= (Vector) objInputStream.readObject();
			setvTitle(vTitle);
			
			vData= (Vector) objInputStream.readObject();
			setvData(vData);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
		
	
	
	
}
