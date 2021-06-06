package Controller;

import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Enumeration;
import java.util.Vector;

import javax.swing.JOptionPane;

public class SearchKH implements Serializable{
	private Vector vData= new Vector();
	private Vector vTitle= new Vector();
	public SearchKH() {
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
	 public void execute(String key) {
//		 String sql= "select * from khachhang where makh= 'kh102'";
		 String sql= "searchKH";
		 try {
			Client client = new Client(InetAddress.getLocalHost(), 6793);
			Socket socket= client.connectServer();
			DataOutputStream dos= new DataOutputStream(socket.getOutputStream());
			
			dos.writeUTF(sql);
			dos.flush();
			dos.writeUTF(key);
			dos.flush();

			ObjectInputStream objInputStream = new ObjectInputStream(socket.getInputStream());
			vTitle= (Vector) objInputStream.readObject();
			vData= (Vector) objInputStream.readObject();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		 
	 }
}
