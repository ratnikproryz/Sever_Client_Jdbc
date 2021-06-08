package Controller;

import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Enumeration;
import java.util.Vector;

public class SearchDK implements Serializable{
	private Vector<String> data = new Vector<String>();
	private Vector vData = new Vector();
	private Vector vTitle = new Vector();
	
	public SearchDK() {
		// TODO Auto-generated constructor stub
	}
	public SearchDK(Vector<String> data) {
		this.data = data;
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
		try {
			String request = "searchDK";
			Client client = new Client(InetAddress.getLocalHost(), 6793);
			Socket socket = new Socket();
			socket = client.connectServer();
			
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			dos.writeUTF(request);
			dos.flush();
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
			objectOutputStream.writeObject(data);
			
			ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
			vTitle = (Vector) objectInputStream.readObject();
			vData = (Vector) objectInputStream.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
}
