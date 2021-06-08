package Controller;

import java.awt.BufferCapabilities.FlipContents;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Vector;

public class InsertDK implements Serializable{
	private Vector<String> dataInsert = new Vector<String>();
	public InsertDK() {
		// TODO Auto-generated constructor stub
	}
	
	
	public InsertDK(Vector<String> dataInsert) {
		this.dataInsert = dataInsert;
	}


	public void execute() {
		try {
			String request= "insertDK";
			String sql ="insert into dienke values(?,?,?,?,?)";
			Client client = new Client(InetAddress.getLocalHost(), 6793);
			Socket socket = new Socket();
			socket = client.connectServer();
			
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			dos.writeUTF(request);
			dos.flush();
			dos.writeUTF(sql);
			dos.flush();
			ObjectOutputStream objOutStream = new ObjectOutputStream(socket.getOutputStream());
			objOutStream.writeObject(dataInsert);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
