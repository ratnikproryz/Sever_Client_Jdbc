package Controller;

import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Vector;

public class SearchHD implements Serializable{
	private Vector vData = new Vector();
	private Vector vTitle = new Vector();
	
	public SearchHD() {
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
		try {
			String request = "searchHD";
			Client client = new Client(InetAddress.getLocalHost(), 6793);
			Socket socket = new Socket();
			socket = client.connectServer();
			
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			dos.writeUTF(request);
			dos.flush();
			dos.writeUTF(key);
			dos.flush();
			
			ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
			vTitle = (Vector) objectInputStream.readObject();
			vData = (Vector) objectInputStream.readObject();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
