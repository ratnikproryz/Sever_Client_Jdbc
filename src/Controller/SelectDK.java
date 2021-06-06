package Controller;

import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Vector;

import javax.swing.JOptionPane;

public class SelectDK implements Serializable{
	private static final long serialVersionUID = 1L;
	private Vector vData= new Vector();
	private Vector vTitle= new Vector();
	
	public SelectDK() {
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
	
	public void execute() {
		String sql= "select * from dienke";
		try {
			//tao client gui den server
			Client client = new Client(InetAddress.getLocalHost(), 6793);
			Socket socket= client.connectServer();
			DataOutputStream dos= new DataOutputStream(socket.getOutputStream());
			dos.writeUTF(sql);
			dos.flush();
			
			//nhan cac vector tu server
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
